package com.recycling.production;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "cleaningSchedule")
public class CleaningSchedule extends Schedule{

    public CleaningSchedule (Date date){
        super(date);
    }
    public CleaningSchedule(){

    }
}
