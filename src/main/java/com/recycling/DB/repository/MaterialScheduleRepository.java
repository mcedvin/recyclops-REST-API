package com.recycling.DB.repository;

import com.recycling.production.MaterialSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialScheduleRepository  extends JpaRepository<MaterialSchedule, Integer> {
}
