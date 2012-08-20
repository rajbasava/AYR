/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import java.util.Map;

import com.yvphk.model.form.Volunteer;
import com.yvphk.model.form.Login;
import com.yvphk.service.VolunteerService;
import com.yvphk.common.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class VolunteerController
{
    @Autowired
    private VolunteerService volunteerService;

    @RequestMapping("/volunteer")
    public String listVolunteer (Map<String, Object> map)
    {
        map.put("volunteer", new Volunteer());
        map.put("volunteerList", volunteerService.listVolunteer());
        return "volunteer";
    }

    @RequestMapping(value="/addVolunteer", method = RequestMethod.POST)
    public String newVolunteer (@ModelAttribute("volunteer")
	Volunteer volunteer, BindingResult result)
    {
        volunteerService.addVolunteer(volunteer);
        return "redirect:/volunteer.htm";
    }

    @RequestMapping("/delete")
    public String removeVolunteer (HttpServletRequest request)
    {
        String strVolunteerId = request.getParameter("volunteerId");
        if (!Util.nullOrEmptyOrBlank(strVolunteerId)) {
            Integer volunteerId = Integer.parseInt(strVolunteerId);
            volunteerService.removeVolunteer(volunteerId);
        }
        return "redirect:/volunteer.htm";
    }

    @RequestMapping("/index")
    public String login (Map<String, Object> map)
    {
        map.put("login", new Login());
        return "login";
    }

    @RequestMapping("/login")
    public String processlogin (@ModelAttribute("login")
    Login login, BindingResult result, HttpSession session)
    {
        boolean isValid = volunteerService.processLogin(login);
        if (isValid) {
            session.setAttribute(login.getClass().getName(), login);
            return "redirect:/welcome.htm";
        }
        else {
            return "redirect:/index.htm";
        }
    }

    @RequestMapping("/logout")
    public String processlogout (HttpServletRequest request , HttpSession session)
    {
        Login login = (Login) session.getAttribute(Login.class.getName());
        volunteerService.processLogout(login);
        session.invalidate();
        return "redirect:/index.htm";
    }

    @RequestMapping("/welcome")
    public String welcome (HttpServletRequest request)
    {
        return "welcome";
    }
}