/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yvphk.model.form.Login;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle (HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception
    {
        String uri = request.getRequestURI();
        if (!uri.endsWith("index.htm") &&
                !uri.endsWith("login.htm") &&
                !uri.endsWith("logout.htm")) {
            Login userData = (Login) request.getSession().getAttribute(Login.class.getName());
            if (userData == null) {
                response.sendRedirect("index.htm");
                return false;
            }
        }
        return true;
    }
}
