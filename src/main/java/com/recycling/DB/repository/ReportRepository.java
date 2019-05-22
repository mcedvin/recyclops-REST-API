package com.recycling.DB.repository;

import com.recycling.production.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository  extends JpaRepository<Report, Integer> {
}
