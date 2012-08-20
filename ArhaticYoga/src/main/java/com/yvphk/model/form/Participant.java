/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.

    Responsible: byummadisingh
*/

package com.yvphk.model.form;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.io.Serializable;

@Entity
@Table(name="PHK_PARTICIPANT")
public class Participant implements Serializable
{
	@Id
	@Column(name="PARTICIPANTID")
	@GeneratedValue
	private Integer participantid;
	
	@Column(name="NAME")
	private String name;

	@Column(name="EMAIL")
	private String email;
	
	@Column(name="MOBILE")
	private String mobile;
	
	@Column(name="FOUNDATION")
	private String foundation;
    
	@Column(name="LEVEL")
	private String level;

    @Column(name="FOODCOUPON")
    private boolean foodcoupon;

    @Column(name="EVENTKIT")
    private boolean eventkit;

    @Column(name="AMOUNTPAID")
    private Integer amountpaid;

    @Column(name="DUEAMOUNT")
    private Integer dueamount;

	@Column(name="PREPAREDBY")
	private String preparedby;
	
	@Column(name="TIMECREATED")
	private Date timecreated;
	
	@Column(name="TIMEUPDATED")
	private Date timeupdated;
	
	@Column(name="ACTIVE")
	private boolean active;

    @OneToMany(mappedBy="participant")
    private Set<Comment> comments;

    @OneToMany(mappedBy="participant")
    private Set<ParticipantSeat> seats;

    public Integer getParticipantid() {
        return participantid;
    }

    public void setParticipantid(Integer participantid) {
        this.participantid = participantid;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isFoodcoupon() {
        return foodcoupon;
    }

    public void setFoodcoupon(boolean foodcoupon) {
        this.foodcoupon = foodcoupon;
    }

    public boolean isEventkit() {
        return eventkit;
    }

    public void setEventkit(boolean eventkit) {
        this.eventkit = eventkit;
    }

    public Integer getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(Integer amountpaid) {
        this.amountpaid = amountpaid;
    }

    public Integer getDueamount() {
        return dueamount;
    }

    public void setDueamount(Integer dueamount) {
        this.dueamount = dueamount;
    }

    public String getPreparedby() {
        return preparedby;
    }

    public void setPreparedby(String preparedby) {
        this.preparedby = preparedby;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<ParticipantSeat> getSeats() {
        return seats;
    }

    public void setSeats(Set<ParticipantSeat> seats) {
        this.seats = seats;
    }
}