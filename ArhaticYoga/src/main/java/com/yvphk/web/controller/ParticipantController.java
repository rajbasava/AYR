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

import java.util.Map;

@Controller
public class ParticipantController
{
    @Autowired
    private ParticipantService participantService;

    @RequestMapping("/register")
    public String newParticipant(Map<String, Object> map)
    {
        map.put("RegisteredParticipant", new RegisteredParticipant());
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
        map.put("participantCriteria", new ParticipantCriteria());
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
        
        return "redirect:/welcome.jsp";
    }

}