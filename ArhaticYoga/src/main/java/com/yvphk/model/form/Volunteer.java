/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.model.form;

import com.yvphk.common.Util;
import com.yvphk.common.VolunteerPermission;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

@Entity
@Table(name = "PHK_VOLUNTEER")
public class Volunteer implements Serializable
{
    @Id
    @Column(name = "VOLUNTEERID")
    @GeneratedValue
    private Integer volunteerId;

    @Column(name="NAME")
    private String name;

    @Column(name="EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name="MOBILE")
    private String mobile;

    @Column(name = "PERMISSION")
    private String permission;

    @Column(name = "ACTIVITY")
    private String activity;

    @Column(name = "PREPAREDBY")
    private String preparedBy;

    @Column(name = "TIMECREATED")
    private Date timecreated;

    @Column(name = "TIMEUPDATED")
    private Date timeupdated;

    @OneToOne(mappedBy="volunteer")
    private LoggedInVolunteer loggedInVolunteer;


    public Integer getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Integer volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPermission() {
        return permission;
    }

    public String getPermissionName() {
        if (Util.nullOrEmptyOrBlank(getPermission())) {
            return null;
        }
        return VolunteerPermission.getName(getPermission());
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public Date getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(Date timecreated) {
        this.timecreated = timecreated;
    }

    public Date getTimeupdated() {
        return timeupdated;
    }

    public void setTimeupdated(Date timeupdated) {
        this.timeupdated = timeupdated;
    }

    public LoggedInVolunteer getLogin() {
        return loggedInVolunteer;
    }

    public void setLogin(LoggedInVolunteer loggedInVolunteer) {
        this.loggedInVolunteer = loggedInVolunteer;
    }
}