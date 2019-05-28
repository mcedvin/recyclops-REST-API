package com.recycling.production;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

//TODO: skicka mail till sthlm stad?
@Entity
@Table(name = "report")
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "station")
    private Station station;
    @Column(name = "finalEndDate")
    private Date finalEndDate;
    //TODO: sätta relation fungerar ej här. FIXA
    //pga User UserAccount är 1..1?
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserAccount")
    private UserAccount userAccount;
    @JoinColumn(name = "materialEndDates")
    @ManyToMany
    private Collection<MaterialSchedule> materialSchedules;
    @ManyToOne
    @JoinColumn(name = "cleaningEndDate")
    private CleaningSchedule cleaningSchedule;

    public Report() {

    }

    public Report(Station station, UserAccount userAccount, Date finalEndDate, Collection<MaterialSchedule> materialSchedules, CleaningSchedule cleaningSchedule) {
        this.station = station;
        this.userAccount = userAccount;
        this.finalEndDate = finalEndDate;
        this.materialSchedules = materialSchedules;
        this.cleaningSchedule = cleaningSchedule;
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

    public Collection<MaterialSchedule> getMaterialSchedules() {
        return materialSchedules;
    }

    public void setMaterialSchedules(Collection<MaterialSchedule> materialSchedules) {
        this.materialSchedules = materialSchedules;
    }

    public CleaningSchedule getCleaningSchedule() {
        return cleaningSchedule;
    }

    public void setCleaningSchedule(CleaningSchedule cleaningSchedule) {
        this.cleaningSchedule = cleaningSchedule;
    }

    public String toString(){
        return getStation().getStationName();
    }

}
