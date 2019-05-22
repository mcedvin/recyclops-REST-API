package com.recycling.production;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "material")
public class Material implements Serializable {
    @Id
    @Column(name = "materialType", length = 100)
    private String materialType;

    public Material(String materialType) {
        this.materialType = materialType;
    }
    public Material(){

    }

    public String getMaterialType() {
        return materialType;

    }
}
