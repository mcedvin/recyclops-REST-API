package com.recycling.recycling.production;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "station")
public class Station implements Serializable {
    @GeneratedValue
    @ManyToMany
    @JoinColumn(nullable = true, name = "availableMaterials")
    private Collection<Material> availableMaterials = new ArrayList<Material>();
    @Id
    @Column(name = "stationName", length = 100)
    private String stationName;
    @Column(name = "lastEmpty")
    private Date lastEmpty;
    @OneToOne(optional = true)
    @JoinColumns(value = {@JoinColumn(nullable = true, name = "stationPositionX"),
            @JoinColumn(nullable = true, name = "stationPositionY")})
    private Position pos;

    public Station(String stationName, Position pos) {
        this.stationName = stationName;
        this.pos = pos;
    }

    public Station() {

    }

    public String getStationName() {
        return stationName;
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

    public String toString() {
        return stationName + " på position: " + pos;
    }
}
