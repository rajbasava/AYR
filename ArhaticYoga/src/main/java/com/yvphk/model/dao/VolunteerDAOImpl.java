/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.yvphk.model.form.Volunteer;
import com.yvphk.model.form.LoggedInVolunteer;
import com.yvphk.model.form.Login;
import com.yvphk.common.Util;

import java.util.List;
import java.util.Date;


@Repository
public class VolunteerDAOImpl implements VolunteerDAO
{
    @Autowired
    private SessionFactory sessionFactory;

    public void addVolunteer (Volunteer volunteer)
    {
        sessionFactory.getCurrentSession().save(volunteer);
    }

    public List<Volunteer> listVolunteer ()
    {
        return sessionFactory.getCurrentSession().createQuery("from Volunteer")
				.list();
    }

    public void removeVolunteer (Integer id)
    {
        Volunteer volunteer = (Volunteer) sessionFactory.getCurrentSession().load(
                Volunteer.class, id);
        if (null != volunteer) {
            sessionFactory.getCurrentSession().delete(volunteer);
        }
    }

    public boolean processLogin (Login login)
    {
        if (Util.nullOrEmptyOrBlank(login.getEmail())) {
            return false;
        }

        Volunteer volunteer = getVolunteerByEmail(login.getEmail());

        if (volunteer != null &&
                volunteer.getEmail().equals(login.getEmail()) &&
                volunteer.getPassword().equals(login.getPassword())) {
            LoggedInVolunteer loggedInVolunteer = volunteer.getLogin();
            if (loggedInVolunteer == null) {
                loggedInVolunteer = new LoggedInVolunteer();
                loggedInVolunteer.setVolunteer(volunteer);
                loggedInVolunteer.setCounter(login.getCounter());
                loggedInVolunteer.setLoggedin(new Date());
                sessionFactory.getCurrentSession().save(loggedInVolunteer);
                return true;
            }
            else {
                loggedInVolunteer.setCounter(login.getCounter());
                loggedInVolunteer.setLoggedin(new Date());
                loggedInVolunteer.setLoggedout(null);
                sessionFactory.getCurrentSession().update(loggedInVolunteer);
                return true;
            }
        }

        return false;
    }

    public void processLogout (Login login)
    {
        if (login != null && !Util.nullOrEmptyOrBlank(login.getEmail())) {
            Volunteer volunteer = getVolunteerByEmail(login.getEmail());
            LoggedInVolunteer loggedInVolunteer = volunteer.getLogin();
            loggedInVolunteer.setLoggedout(new Date());
            sessionFactory.getCurrentSession().update(loggedInVolunteer);
        }
    }

    private Volunteer getVolunteerByEmail (String email) 
    {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Volunteer.class);

        criteria.add(Restrictions.eq("email",email));
        List<Volunteer> volunteers = criteria.list();

        if (volunteers == null ||
                volunteers.isEmpty()) {
            return null;
        }

        return volunteers.get(0);
    }

}