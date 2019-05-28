package com.recycling.production;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
    private Collection<Challenge> completedChallenges;

    @JoinColumn(name = "challenge")
        @ManyToMany
        private Collection<ChallengeAccepted> currentChallenges;
    public UserAccount() {

    }

    public UserAccount(int id, String password, byte[] profilePicture) {
        this.id = id;
        this.password = password;
        this.profilePicture = profilePicture;
    }
    public void setId(int id){
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

    public Collection<Challenge> getCompletedChallenges() {
        return completedChallenges;
    }

    public void setCompletedChallenges(Collection<Challenge> completedChallenges) {
        this.completedChallenges = completedChallenges;
    }

    public Collection<ChallengeAccepted> getCurrentChallenges() {
        return currentChallenges;
    }

    public void setCurrentChallenges(Collection<ChallengeAccepted> currentChallenges) {
        this.currentChallenges = currentChallenges;
    }
    public void acceptChallenge(ChallengeAccepted ca){
        currentChallenges.add(ca);
    }
    //TODO: complete challenge
    public void completeChallenge(Challenge c){
        completedChallenges.add(c);
        currentChallenges.remove(c);
    }
    //TODO: schedule som tar bort från currentChallenges om den inte avklarad inom duration time

}
