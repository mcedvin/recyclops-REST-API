package com.recycling.production;

import javax.persistence.*;

@Entity
@Table(name = "challenge")
public class Challenge {
    @Id
    @Column(name = "challengeName", length = 100)
    private String name;
    @Column(name = "challengeDescription")
    private String description;
    @Column(name = "duration")
    private int duration;
    @Lob
    @Column(name = "image")
    private byte[] image;


//    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category")
    private int challengeCategory;
//    private ChallengeCategory challengeCategory;

    public Challenge(String name, String description, int duration, byte[] image, int challengeCategory) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.image = image;
        this.challengeCategory = challengeCategory;
    }

    public Challenge() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getChallengeCategory() {
        return challengeCategory;
    }

    public void setChallengeCategory(int challengeCategory) {
        this.challengeCategory = challengeCategory;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


//    public ChallengeCategory getChallengeCategory() {
//        return challengeCategory;
//    }

//    public enum ChallengeCategory {
//        //havet naturen djuren luften
//        HAVET,
//        NATUREN,
//        DJUREN,
//        LUFTEN
//    }
}

