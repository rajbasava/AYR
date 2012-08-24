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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.yvphk.service.ParticipantService;
import com.yvphk.model.form.*;
import com.yvphk.common.Util;
import com.yvphk.common.ParticipantLevel;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

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
                             ParticipantCriteria participantCriteria,
                             BindingResult result)
    {
        map.put("participantCriteria", participantCriteria);
        if (participantCriteria != null) {
            map.put("participantList", participantService.listParticipants(participantCriteria));
        }
        return "search";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addParticipant(RegisteredParticipant registeredParticipant,
                                 Map<String, Object> map,
                                 BindingResult result,
                                 HttpServletRequest request)
    {
        Login login = (Login) request.getSession().getAttribute(Login.ClassName);
        String action = registeredParticipant.getAction();

        if (RegisteredParticipant.ActionRegister.equals(action)) {
            registeredParticipant.getParticipant().setPreparedby(login.getEmail());
        }

        List<Comment>  commentList = registeredParticipant.getComments();
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

}