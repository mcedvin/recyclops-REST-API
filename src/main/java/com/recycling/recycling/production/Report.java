package com.recycling.recycling.production;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @JoinColumn(name = "station")
    private Station station;
    @JoinColumn(name = "UserAccount")
    private UserAccount userAccount;
    @Column(name = "finalEndDate")
    private Date finalEndDate;

    public Report(Station station, UserAccount userAccount, Date finalEndDate) {
        this.station = station;
        this.userAccount = userAccount;
        this.finalEndDate = finalEndDate;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Date getFinalEndDate() {
        return finalEndDate;
    }

    public void setFinalEndDate(Date finalEndDate) {
        this.finalEndDate = finalEndDate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
