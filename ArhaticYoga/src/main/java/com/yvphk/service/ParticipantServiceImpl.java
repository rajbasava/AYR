/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.yvphk.model.form.Participant;
import com.yvphk.model.form.ParticipantCriteria;
import com.yvphk.model.form.RegisteredParticipant;
import com.yvphk.model.dao.ParticipantDAO;
import com.yvphk.model.dao.VolunteerDAO;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService
{
    @Autowired
    private ParticipantDAO participantDAO;

    @Transactional
    public void registerParticipant(RegisteredParticipant registeredParticipant)
    {
        participantDAO.addParticipant(registeredParticipant);
    }

    @Transactional
    public List<Participant> listParticipants(ParticipantCriteria participantCriteria) {

        return participantDAO.listParticipants(participantCriteria);
    }
}