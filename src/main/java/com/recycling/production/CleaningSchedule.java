package com.recycling.production;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cleaningSchedule")
public class CleaningSchedule extends Schedule implements Serializable {

    public CleaningSchedule (Date date){
        super(date);
    }
    public CleaningSchedule(){

    }
}

//för att POST:a cleaningSchedule:
//       "cleaningSchedule": {
//               "date": "2019-05-25T05:00:00.000Z"
//               }
