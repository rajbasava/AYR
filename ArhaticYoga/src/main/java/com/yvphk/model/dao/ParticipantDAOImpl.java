/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.yvphk.model.form.Participant;
import com.yvphk.model.form.Comment;
import com.yvphk.model.form.RegisteredParticipant;
import com.yvphk.model.form.ParticipantCriteria;
import com.yvphk.common.Util;

import java.util.List;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO
{
    @Autowired
    private SessionFactory sessionFactory;

    public void addParticipant(RegisteredParticipant registeredParticipant)
    {
        Participant participant = registeredParticipant.getParticipant();
        participant.setActive(true);
        sessionFactory.getCurrentSession().save(participant);
        for (Comment comment: registeredParticipant.getComments()) {
            comment.setParticipant(participant);
            sessionFactory.getCurrentSession().save(comment);
        }
    }

    public void addComments(Participant participant, Comment comment)
    {
        
    }

    public Participant getParticipant (Integer participantId)
    {
        Session session = sessionFactory.openSession();
        Participant participant = (Participant)session.load(Participant.class, participantId);
        return participant;
    }

    public List<Participant> listParticipants(ParticipantCriteria participantCriteria)
    {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Participant.class);

        if (!Util.nullOrEmptyOrBlank(participantCriteria.getName())) {
            criteria.add(Restrictions.like("name","%"+participantCriteria.getName()+"%"));
        }

        if (!Util.nullOrEmptyOrBlank(participantCriteria.getEmail())) {
            criteria.add(Restrictions.like("email","%"+participantCriteria.getEmail()+"%"));
        }

        if (!Util.nullOrEmptyOrBlank(participantCriteria.getFoundation())) {
            criteria.add(Restrictions.like("foundation","%"+participantCriteria.getFoundation()+"%"));
        }

        if (!Util.nullOrEmptyOrBlank(participantCriteria.getMobile())) {
            criteria.add(Restrictions.like("mobile","%"+participantCriteria.getMobile()+"%"));
        }

        if (!Util.nullOrEmptyOrBlank(participantCriteria.getLevel())) {
            criteria.add(Restrictions.eq("level",participantCriteria.getLevel()));
        }

        return criteria.list();
    }
}