package com.recycling.production;

import javax.persistence.*;
import java.util.Date;

//TODO: ska läggas i nån collection i Station

@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
abstract public class Schedule {
    @Id
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
