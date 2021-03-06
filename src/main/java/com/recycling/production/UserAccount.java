package com.recycling.production;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity(name = "UserAccount")
@Table(name = "UserAccount")
public class UserAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "Password")
    private String password;
    @Column(name = "profilePicture")
    private byte[] profilePicture;

    @JoinColumn(name = "challenge")
    @ManyToMany
    private Collection<ChallengeAccepted> completedChallenges;

    @JoinColumn(name = "challenge")
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ChallengeAccepted> currentChallenges;

    public UserAccount() {

    }

    public UserAccount(int id, String password, byte[] profilePicture) {
        this.id = id;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<ChallengeAccepted> getCompletedChallenges() {
        return completedChallenges;
    }

    public void setCompletedChallenges(Collection<ChallengeAccepted> completedChallenges) {
        this.completedChallenges = completedChallenges;
    }

    public Collection<ChallengeAccepted> getCurrentChallenges() {
        return currentChallenges;
    }

    public void setCurrentChallenges(Collection<ChallengeAccepted> currentChallenges) {
        this.currentChallenges = currentChallenges;
    }

    public void acceptChallenge(ChallengeAccepted ca) {
        currentChallenges.add(ca);
    }

    //TODO: complete challenge
    public void completeChallenge(ChallengeAccepted ca) {
        completedChallenges.add(ca);
        currentChallenges.remove(ca);
    }
    public boolean hasAcceptedChallenge(Challenge c){
        for(ChallengeAccepted ca : currentChallenges)
            if(c.getName().equalsIgnoreCase(ca.getChallenge().getName()))
                return true;
        return false;
    }
    public boolean verifyChallenges(){
        for(ChallengeAccepted ca : currentChallenges){
            if(ca.getDate().after(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(ca.getChallenge().getDuration())))){
                currentChallenges.remove(ca);
                return true;
        }}
        return false;
    }

}
