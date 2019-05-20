package com.recycling.DB.repository;

import com.recycling.recycling.production.CleaningSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CleaningScheduleRepository  extends JpaRepository<CleaningSchedule, Integer> {
}
