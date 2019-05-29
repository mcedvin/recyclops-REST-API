package com.recycling.production;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "station")
public class Station implements Serializable {
//    @GeneratedValue
    @Id
    @Column(name = "stationName", length = 100)
    private String stationName;

    @Column(name = "postNumber")
    private String postNumber;

    @Column(name = "area")
    private String area;
    @OneToOne()
    @JoinColumns(value = {@JoinColumn(name = "stationPositionX"),
            @JoinColumn(name = "stationPositionY")})
    private Position pos;
    @ManyToMany
    @JoinColumn(name = "availableMaterials")
    private Collection<Material> availableMaterials = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "cleaningSchedule")
    private CleaningSchedule cleaningSchedule;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "materialSchedule")
    private Collection<MaterialSchedule> materialSchedules = new ArrayList<>();

    public Station(String stationName, String postNumber, Position pos) {
        this.stationName = stationName;
        this.postNumber = postNumber;
        this.pos = pos;
    }

    public Station() {

    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    public String getPostNumber() {
        return postNumber;
    }
    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }
    public String getarea() { return area; }
    public void setarea(String area) { this.area = area; }
    public Collection<Material> getAvailableMaterials() {
        return availableMaterials;
    }
    public void setAvailableMaterials(Collection<Material> availableMaterials){this.availableMaterials = availableMaterials;}

    public void addAvailableMaterial(Material m) {
        availableMaterials.add(m);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public CleaningSchedule getCleaningSchedule() {
        return cleaningSchedule;
    }

    public void setCleaningSchedule(CleaningSchedule cleaningSchedule) {
        this.cleaningSchedule = cleaningSchedule;
    }

    public Collection<MaterialSchedule> getMaterialSchedules() {
        return materialSchedules;
    }

    public void setMaterialSchedules(Collection<MaterialSchedule> materialSchedules) {
        this.materialSchedules = materialSchedules;
    }
}


//    {
//            "stationName": "Ålgrytevägen 90",
//            "pos": {
//            "x": 59.29917,
//            "y": 17.94139
//
//            },
//            "availableMaterials": [
//            {"materialType": "Glas"},
//            {"materialType": "Kartong"},
//            {"materialType": "Metall"},
//            {"materialType": "Blandplast"},
//            {"materialType": "Tidningar"}],
//            "cleaningSchedule": {"date": "2019-05-21T00:00:00+00:00"},
//            "materialSchedules": []
//            }
//