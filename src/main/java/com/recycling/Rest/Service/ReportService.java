package com.recycling.Rest.Service;

import com.recycling.Rest.Dao.ReportSQLDao;
import com.recycling.production.Report;
import com.recycling.production.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ReportService {

    @Autowired
    private ReportSQLDao reportSQLDao;

    public Collection<Report> getAllReports(){
        return reportSQLDao.getAllReports();
    }
    public Collection<Report> getActiveReports(){
        return reportSQLDao.getActiveReports();
    }
//    public Collection<Report> getReportsForStation(Station station){
//        return reportSQLDao.getReportsForStation(station);
//    }
    public Collection<Report> getReportsForStation(String station){
        return reportSQLDao.getReportsForStation(station);
    }
    public Report getReportById(int id){
        return reportSQLDao.getReportById(id);
    }
    public void deleteReportById(int id){
        reportSQLDao.deleteReportById(id);
    }
    public void updateReport(Report updatedReport){
        reportSQLDao.updateReport(updatedReport);
    }
    public void addReport(Report newReport){
        reportSQLDao.addReport(newReport);
    }

}
