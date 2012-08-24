/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.model.form;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class RegisteredParticipant implements Serializable
{
    public static final String ActionRegister = "Register";
    public static final String ActionUpdate = "Update";
    
    private Participant participant;
    private List<Comment> comments;
    private List<ParticipantSeat> seats;
    private String action;

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public List<Comment> getComments() {
        if (comments == null) {
            comments = new ArrayList();
        }
        comments.add(0,new Comment());
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<ParticipantSeat> getSeats() {
        return seats;
    }

    public void setSeats(List<ParticipantSeat> seats) {
        this.seats = seats;
    }
}