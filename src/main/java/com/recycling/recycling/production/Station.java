package com.recycling.recycling.production;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "station")
public class Station implements Serializable {
//    @GeneratedValue
    @Id
    @Column(name = "stationName", length = 100)
    private String stationName;
    @Column(name = "lastEmpty")
    private Date lastEmpty;
    @OneToOne()
    @JoinColumns(value = {@JoinColumn(name = "stationPositionX"),
            @JoinColumn(name = "stationPositionY")})
    private Position pos;
    @ManyToMany
    @JoinColumn(name = "availableMaterials")
    private Collection<Material> availableMaterials = new ArrayList<Material>();

    public Station(String stationName, Position pos) {
        this.stationName = stationName;
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

    public Collection<Material> getAvailableMaterials() {
        return availableMaterials;
    }

    public void setAvailableMaterials(Material m) {
        availableMaterials.add(m);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Date getLastEmpty() {
        return lastEmpty;
    }

    public void setLastEmpty() {
        lastEmpty = new Date();
    }
}
