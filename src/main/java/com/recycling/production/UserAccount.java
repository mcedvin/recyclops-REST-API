package com.recycling.production;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserAccount")
public class UserAccount implements Serializable {
    @Column(name = "Id")
    @Id
    private int id;
    @Column(name = "Password")
    private String password; //TODO: måste krypteras på något sätt
    @Column(name = "profilePicture")
    private byte[] profilePicture;
    public UserAccount(){

    }

    public UserAccount(int id, String password, byte[] profilePicture) {
        this.id = id;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
