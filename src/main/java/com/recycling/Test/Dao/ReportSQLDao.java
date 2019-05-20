package com.recycling.Test.Dao;

import com.recycling.DB.repository.ReportRepository;
import com.recycling.recycling.production.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ReportSQLDao {

    @Autowired
    private ReportRepository reportRepository;

    public Collection<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report getReportById(int id){
        for(Report s : reportRepository.findAll()){
            if(s.getId()==id)
                return s;
        }
        return null;
    }
    public void deleteReportById(int id){
        for (Report s : getAllReports()) {
            if (s.getId() != null && s.getId().equals(id))
                reportRepository.delete(s);
        }
    }

    public void updateReport(Report updatedReport) {
        for (Report r : reportRepository.findAll()) {
            if (r.getId() == updatedReport.getId()) {
                r.setStation(updatedReport.getStation());
                r.setUserAccount(updatedReport.getUserAccount());
                r.setFinalEndDate(updatedReport.getFinalEndDate());
                return;
            }
        }
        reportRepository.save(updatedReport);
    }
    public void addReport(Report newReport) {
        reportRepository.save((newReport));
    }
}