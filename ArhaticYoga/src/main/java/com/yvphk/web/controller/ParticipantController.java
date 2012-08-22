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
import com.yvphk.service.ParticipantService;
import com.yvphk.model.form.RegisteredParticipant;
import com.yvphk.model.form.ParticipantCriteria;
import com.yvphk.model.form.Participant;
import com.yvphk.common.Util;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.ArrayList;

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
        return "register";
    }

    @RequestMapping("/search")
    public String search(Map<String, Object> map)
    {
        map.put("participantCriteria", new ParticipantCriteria());
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
                             BindingResult result)
    {
        participantService.registerParticipant(registeredParticipant);
        String action = registeredParticipant.getAction();

        if (RegisteredParticipant.ActionUpdate.equals(action)) {
            return "redirect:/search.htm";
        }

        return "redirect:/welcome.htm"; 
    }

    @RequestMapping("/update")
    public String updateParticipant (HttpServletRequest request, Map<String, Object> map)
    {
        String strParticipantId = request.getParameter("participantId");
        if (!Util.nullOrEmptyOrBlank(strParticipantId)) {
            Integer participantId = Integer.parseInt(strParticipantId);
            Participant participant = participantService.getParticipant(participantId);
            RegisteredParticipant registeredParticipant = new RegisteredParticipant();
            registeredParticipant.setParticipant(participant);
            registeredParticipant.setComments(new ArrayList(participant.getComments()));
            registeredParticipant.setAction(RegisteredParticipant.ActionUpdate);
            map.put("registeredParticipant", registeredParticipant);
            return "register";
        }
        return "null";
    }

}