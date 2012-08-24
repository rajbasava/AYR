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
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import com.yvphk.model.form.*;
import com.yvphk.common.Util;

import java.util.List;
import java.util.Date;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO
{
    @Autowired
    private SessionFactory sessionFactory;

    public Participant addParticipant (RegisteredParticipant registeredParticipant)
    {
        Participant participant = registeredParticipant.getParticipant();

        if (RegisteredParticipant.ActionRegister.equals(registeredParticipant.getAction())) {
            participant.setActive(true);

            String level = registeredParticipant.getParticipant().getLevel();
            if (Util.nullOrEmptyOrBlank(registeredParticipant.getParticipant().getLevel())) {
                return null;
            }
            participant.setTimecreated(new Date());
            sessionFactory.getCurrentSession().save(participant);

            List<ParticipantSeat> seats = registeredParticipant.getSeats();
            for (ParticipantSeat participantSeat : seats) {
                if (participantSeat.getSeat() == null) {
                    Integer greatestSeat = getGreatestSeat(level);
                    Integer greatestSeatNo = new Integer(1);
                    if (greatestSeat != null) {
                        greatestSeatNo = greatestSeat.intValue() + 1;
                    }
                    participantSeat.setSeat(greatestSeatNo);
                }
                participantSeat.setLevel(level);
                participantSeat.setParticipant(participant);
                sessionFactory.getCurrentSession().save(participantSeat);
            }
        }
        else if (RegisteredParticipant.ActionUpdate.equals(registeredParticipant.getAction())) {
            participant.setTimeupdated(new Date());
            sessionFactory.getCurrentSession().update(participant);
        }

        for (Comment comment: registeredParticipant.getComments()) {
            comment.setParticipant(participant);
            comment.setTimecreated(new Date());
            if (!Util.nullOrEmptyOrBlank(comment.getComments())) {
                sessionFactory.getCurrentSession().save(comment);
            }
        }

        return participant;

    }

    public void addComments (Participant participant, Comment comment)
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

        if (participantCriteria.getSeat() != null) {
            criteria.createCriteria("seats","seats");
            criteria.add(Restrictions.eq("seats.seat", participantCriteria.getSeat()));
        }

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

    public Integer getGreatestSeat (String level)
    {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(ParticipantSeat.class);
        criteria.setProjection(Projections.max("seat"));

        if (!Util.nullOrEmptyOrBlank(level)) {
            criteria.add(Restrictions.like("level",level));
        }

        List seats = criteria.list();
        if (!seats.isEmpty()) {
                return (Integer)seats.get(0);
        }
        else {
            return null;
        }
    }
}