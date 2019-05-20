package com.recycling.Test.Service;

import com.recycling.Test.Controller.ReportController;
import com.recycling.Test.Dao.ReportSQLDao;
import com.recycling.recycling.production.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ReportService {

    @Autowired
    ReportSQLDao reportSQLDao;

    public Collection<Report> getAllReports(){
        return reportSQLDao.getAllReports();
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
