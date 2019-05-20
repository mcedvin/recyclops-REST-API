package com.recycling.recycling.production;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "materialSchedule")
public class MaterialSchedule extends Schedule{

    private Material material;

    public MaterialSchedule(Date date, Material material){
        super(date);
        this.material = material;
    }
    public MaterialSchedule(){

    }
}
