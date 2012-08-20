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
    private Participant participant;
    private List<Comment> comments = new ArrayList();

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public List<Comment> getComments() {
        if (comments.isEmpty()) {
            comments.add(new Comment());
        }
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}