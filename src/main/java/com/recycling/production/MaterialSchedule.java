package com.recycling.production;

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
    public Material getMaterial(){
        return material;
    }
    public void setMaterial(Material material){
        this.material = material;
    }
}
