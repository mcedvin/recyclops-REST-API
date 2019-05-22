package com.recycling.Rest.Dao;

import com.recycling.DB.repository.MaterialScheduleRepository;
import com.recycling.production.MaterialSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class MaterialScheduleSQLDao {

    @Autowired
    private MaterialScheduleRepository MaterialScheduleRepository;

    public Collection<MaterialSchedule> getAllMaterialSchedules(){
        return MaterialScheduleRepository.findAll();
    }
    public void addMaterialSchedule(MaterialSchedule MaterialSchedule){
        MaterialScheduleRepository.save(MaterialSchedule);
    }
}
