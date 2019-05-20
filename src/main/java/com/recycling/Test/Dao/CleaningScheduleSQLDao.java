package com.recycling.Test.Dao;

import com.recycling.DB.repository.CleaningScheduleRepository;
import com.recycling.recycling.production.CleaningSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public class CleaningScheduleSQLDao {

    @Autowired
    private CleaningScheduleRepository cleaningScheduleRepository;

    public Collection<CleaningSchedule> getAllCleaningSchedules(){
        return cleaningScheduleRepository.findAll();
    }
    public void addCleaningSchedule(CleaningSchedule cleaningSchedule){
        cleaningScheduleRepository.save(cleaningSchedule);
    }
}
