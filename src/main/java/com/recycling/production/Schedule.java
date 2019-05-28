package com.recycling.production;

import javax.persistence.*;
import java.util.Date;


@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
abstract public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
//    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    public Schedule(Date date){
        this.date = date;
    }
    public Schedule(){

    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
