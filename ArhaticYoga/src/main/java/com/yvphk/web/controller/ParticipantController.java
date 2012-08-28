/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import com.yvphk.service.ParticipantService;
import com.yvphk.common.Util;
import com.yvphk.common.ParticipantLevel;
import com.yvphk.model.form.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

@Controller
public class ParticipantController
{
    @Autowired
    private ParticipantService participantService;

    @RequestMapping("/register")
    public String newParticipant(Map<String, Object> map)
    {
        RegisteredParticipant registeredParticipant = new RegisteredParticipant();
        registeredParticipant.setAction(RegisteredParticipant.ActionRegister);
        map.put("registeredParticipant", registeredParticipant);
        map.put("allParticipantLevels", ParticipantLevel.allParticipantLevels());
        return "register";
    }

    @RequestMapping("/search")
    public String search(Map<String, Object> map)
    {
        map.put("participantCriteria", new ParticipantCriteria());
        map.put("allParticipantLevels", ParticipantLevel.allParticipantLevels());
        return "search";
    }

    @RequestMapping("/list")
    public String searchParticipant(Map<String, Object> map,
                             ParticipantCriteria participantCriteria)
    {
        map.put("participantCriteria", participantCriteria);
        if (participantCriteria != null) {
            map.put("participantList", participantService.listParticipants(participantCriteria));
            map.put("allParticipantLevels", ParticipantLevel.allParticipantLevels());
        }
        return "search";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addParticipant(RegisteredParticipant registeredParticipant,
                                 Map<String, Object> map,
                                 HttpServletRequest request)
    {
        Login login = (Login) request.getSession().getAttribute(Login.ClassName);
        String action = registeredParticipant.getAction();

        if (RegisteredParticipant.ActionRegister.equals(action)) {
            registeredParticipant.getParticipant().setPreparedby(login.getEmail());
        }

        List<Comment> commentList = registeredParticipant.getComments();
        for (Comment comment: commentList) {
            if (!Util.nullOrEmptyOrBlank(comment.getComments())) {
                comment.setPreparedby(login.getEmail());
            }
        }

        Participant participant = participantService.registerParticipant(registeredParticipant);

        if (RegisteredParticipant.ActionUpdate.equals(action)) {
            return "redirect:/search.htm";
        }
        
        registeredParticipant = populateRegisteredParticipant(String.valueOf(participant.getParticipantId()));
        map.put("registeredParticipant", registeredParticipant);
        return "summary"; 
    }

    @RequestMapping("/update")
    public String updateParticipant (HttpServletRequest request, Map<String, Object> map)
    {
        String strParticipantId = request.getParameter("participantId");
        RegisteredParticipant registeredParticipant = populateRegisteredParticipant (strParticipantId);
        if (registeredParticipant != null) {
            map.put("registeredParticipant", registeredParticipant);
            map.put("allParticipantLevels", ParticipantLevel.allParticipantLevels());
            return "register";
        }
        return "null";
    }

    private RegisteredParticipant populateRegisteredParticipant (String strParticipantId)
    {
        if (!Util.nullOrEmptyOrBlank(strParticipantId)) {
            Integer participantId = Integer.parseInt(strParticipantId);
            Participant participant = participantService.getParticipant(participantId);
            RegisteredParticipant registeredParticipant = new RegisteredParticipant();
            registeredParticipant.setParticipant(participant);
            registeredParticipant.setComments(new ArrayList(participant.getComments()));
            registeredParticipant.setSeats(new ArrayList(participant.getSeats()));
            registeredParticipant.setAction(RegisteredParticipant.ActionUpdate);
            return registeredParticipant;
        }

        return null;
    }

    @RequestMapping(value = "/batchEntry", method = RequestMethod.GET)
    public String batchEntry ()
    {
    	return "batchEntry";
    }

    @RequestMapping(value = "/batchEntry", method = RequestMethod.POST)
    public String processBatchEntry (HttpServletRequest request, Map<String, Object> map)
    {
        Login login = (Login) request.getSession().getAttribute(Login.ClassName);
        String preparedBy = login.getEmail();

        List<RegisteredParticipant> participantList = new ArrayList<RegisteredParticipant>();

        String parentNode = "/ParticipantsData";
        final String LAST_ELEMENT_IN_RECORD = "Level";

        Method method = null;
        String nodeName = "", nodeValue = "";

        XPath xPath = XPathFactory.newInstance().newXPath();
        try {
            NodeList nodes = (NodeList) xPath.evaluate(
                    parentNode,
                    new InputSource(new StringReader(request.getParameter("content"))),
                    XPathConstants.NODESET
            );
            for (int i = 0; i < nodes.getLength(); i++) {
                Element iNodes = (Element) nodes.item(i);
                NodeList node = (NodeList) xPath.evaluate("Participant/*", iNodes, XPathConstants.NODESET);
                Participant participant = Participant.class.newInstance();
                RegisteredParticipant registeredParticipant = new RegisteredParticipant();
                registeredParticipant.setAction(RegisteredParticipant.ActionRegister);
                for (int k = 0; k < node.getLength(); k++) {
                    Element element = (Element) node.item(k);
                    nodeName = element.getNodeName();
                    nodeValue = element.getFirstChild().getNodeValue();
                    if (nodeName.equalsIgnoreCase("Amount") ||
                            nodeName.equalsIgnoreCase("AmountPaid") ||
                            nodeName.equalsIgnoreCase("DueAmount")) {
                        method = Participant.class.getDeclaredMethod("set" + nodeName, Integer.class);
                        method.invoke(participant, Integer.parseInt(nodeValue));
                    }
                    else if (nodeName.equalsIgnoreCase("Comments")) {
                        Comment comment = new Comment();
                        comment.setComments(nodeValue);
                        comment.setPreparedby(preparedBy);
                        List<Comment> commentsList = new ArrayList<Comment>();
                        commentsList.add(comment);
                        registeredParticipant.setComments(commentsList);
                    }
                    else if (nodeName.equalsIgnoreCase("Seat")) {
                        ParticipantSeat seat = new ParticipantSeat();
                        seat.setSeat(Integer.parseInt(nodeValue));
                        List<ParticipantSeat> psList = new ArrayList<ParticipantSeat>();
                        psList.add(seat);
                        registeredParticipant.setSeats(psList);
                    }
                    else if (nodeName.equalsIgnoreCase("Foodcoupon") ||
                            nodeName.equalsIgnoreCase("Eventkit")) {
                        method = Participant.class.getDeclaredMethod("set" + nodeName, boolean.class);
                        method.invoke(participant, Boolean.parseBoolean(nodeValue));
                    }
                    else {
                        method = Participant.class.getDeclaredMethod("set" + nodeName, String.class);
                        method.invoke(participant, nodeValue);
                    }

                    if (nodeName.equalsIgnoreCase(LAST_ELEMENT_IN_RECORD)) {
                        participant.setPreparedby(preparedBy);
                        registeredParticipant.setParticipant(participant);
                        participantList.add(registeredParticipant);
                        // creating new objects for next iteration.
                        participant = Participant.class.newInstance();
                        registeredParticipant = RegisteredParticipant.class.newInstance();
                        registeredParticipant.setAction(RegisteredParticipant.ActionRegister);
                    }
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (XPathExpressionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        participantService.processBatchEntry(participantList);
        map.put("batchResults", "Batch upload successful. Imported "+participantList.size()+" records.");
        return "batchResults";
    }
}