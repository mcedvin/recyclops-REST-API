package com.recycling.production;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "materialSchedule")
public class MaterialSchedule extends Schedule implements Serializable {

    @Column(name = "material")
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
