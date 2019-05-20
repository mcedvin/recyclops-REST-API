package com.recycling.recycling.production;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "position")
@IdClass(PositionID.class)
public class Position implements Serializable {
    @Id
    @Column(name = "x")
    private double x;
    @Id
    @Column(name = "y")
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position() {

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position){
            return x==((Position) obj).x && y == ((Position) obj).y;
        }
        return false;
    }

    public String toString() {
        return "" + x + " " + y;
    }
}
