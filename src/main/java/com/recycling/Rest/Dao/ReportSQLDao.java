package com.recycling.Rest.Dao;

import com.recycling.DB.repository.ReportRepository;
import com.recycling.Rest.MailHandler;
import com.recycling.production.MaterialSchedule;
import com.recycling.production.Report;
import com.recycling.production.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

@Repository
public class ReportSQLDao {

    @Autowired
    private ReportRepository reportRepository;

    public Collection<Report> getAllReports() {
        return reportRepository.findAll();
    }
    public Collection<Report> getActiveReports() {
        Collection<Report> activeReports = new LinkedList<>();
        Date currentDate = new Date();
        for (Report r : reportRepository.findAll()) {
            if (!currentDate.after(r.getFinalEndDate())) {
                for (MaterialSchedule ms : r.getMaterialSchedules())
                    if (currentDate.after(ms.getDate()))
                        r.getMaterialSchedules().remove(ms);
                if (r.getCleaningSchedule()!=null && currentDate.after(r.getCleaningSchedule().getDate()))
                    r.setCleaningSchedule(null);
                if(!r.getMaterialSchedules().isEmpty() || r.getCleaningSchedule() != null)
                    activeReports.add(r);
            }
        }
        return activeReports;
    }

    public Collection<Report> getReportsForStation(Station station){
        Collection<Report> reportsforStation = new LinkedList<>();
        for(Report r : getActiveReports()){
            if(r.getStation().getStationName().equalsIgnoreCase(station.getStationName())){
                reportsforStation.add(r);
            }
        }
        return reportsforStation;
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
            if (s.getId() == id)
                reportRepository.delete(s);
        }
    }

    public void updateReport(Report updatedReport) {
//        for (Report r : reportRepository.findAll()) {
//            if (r.getStation().getStationName().equals(updatedReport.getId)) {
//                r.setStation(updatedReport.getStation());
//                r.setUserAccount(updatedReport.getUserAccount());
//                r.setFinalEndDate(updatedReport.getFinalEndDate());
//                return;
//            }
//        }
        reportRepository.save(updatedReport);
    }
    public void addReport(Report newReport) {
        reportRepository.save((newReport));
        new MailHandler().sendMail(newReport);
    }
}