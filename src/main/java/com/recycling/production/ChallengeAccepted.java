package com.recycling.production;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ChallengeAccepted")
public class ChallengeAccepted {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "challenge")
    private Challenge challenge;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    public ChallengeAccepted(Challenge challenge, Date date) {
        this.challenge = challenge;
        this.date = date;
    }

    public ChallengeAccepted() {

    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

//TODO: om dateformated är fel, lägg till: @Temporal(TemporalType.DATE)
