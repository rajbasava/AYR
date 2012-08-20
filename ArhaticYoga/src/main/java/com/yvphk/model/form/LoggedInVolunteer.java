/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.model.form;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="PHK_VOLLOGIN")
public class LoggedInVolunteer implements Serializable
{
    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="VOLUNTEERID")
    private Volunteer volunteer;

    @Column(name="COUNTER")
    private String counter;

    @Column(name = "LOGGEDIN")
    private Date loggedin;

    @Column(name = "LOGGEDOUT")
    private Date loggedout;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public Date getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(Date loggedin) {
        this.loggedin = loggedin;
    }

    public Date getLoggedout() {
        return loggedout;
    }

    public void setLoggedout(Date loggedout) {
        this.loggedout = loggedout;
    }
}