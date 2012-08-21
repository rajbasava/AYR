/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.service;

import com.yvphk.model.form.Participant;
import com.yvphk.model.form.ParticipantCriteria;
import com.yvphk.model.form.RegisteredParticipant;

import java.util.List;

public interface ParticipantService
{
    public void registerParticipant(RegisteredParticipant registeredParticipant);
    public Participant getParticipant(Integer participantId);
    public List<Participant> listParticipants(ParticipantCriteria participantCriteria);
}
