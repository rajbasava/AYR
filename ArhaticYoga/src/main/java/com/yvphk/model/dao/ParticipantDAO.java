/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.model.dao;

import com.yvphk.model.form.Participant;
import com.yvphk.model.form.Comment;
import com.yvphk.model.form.RegisteredParticipant;
import com.yvphk.model.form.ParticipantCriteria;

import java.util.List;

public interface ParticipantDAO
{
    public void addParticipant (RegisteredParticipant registeredParticipant);
    public void addComments (Participant participant, Comment comment);
    public Participant getParticipant (Integer userId);
    public List<Participant> listParticipants(ParticipantCriteria participantCriteria);
}
