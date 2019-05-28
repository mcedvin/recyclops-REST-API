package com.recycling.Rest.Controller;

import com.recycling.DB.repository.CleaningScheduleRepository;
import com.recycling.DB.repository.MaterialScheduleRepository;
import com.recycling.DB.repository.StationRepository;
import com.recycling.DB.repository.UserAccountsRepository;
import com.recycling.Rest.Service.ReportService;
import com.recycling.production.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private UserAccountsRepository userAccountsRepository;
    @Autowired
    private CleaningScheduleRepository cleaningScheduleRepository;
    @Autowired
    private MaterialScheduleRepository materialScheduleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Report> getAllReports() {
        return reportService.getAllReports();
    }

    //TODO: returnera en modifierad report med bara schedule after currentDate
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public Collection<Report> getActiveReports(){
        return reportService.getActiveReports();
    }
//
//    @RequestMapping(value = "/{mail}", method = RequestMethod.GET)
//    public User getUserByEmail(@PathVariable("mail") String mail) {
//        return userService.getUserByEmail(mail);
//    }


    //TODO: returnera alla aktuella rapporter för en station (argument station, returnerar en Collection Reports)
    @RequestMapping(value = "/{stationName}", method = RequestMethod.GET)
    public Collection<Report> getReportsForStation(@PathVariable("stationName") String stationName){
        return reportService.getReportsForStation(stationName);
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void addReport(@RequestBody final Report report) {
        if(report.getStation()!=null)
            for(Station station : stationRepository.findAll())
                if(station.getStationName().equals(report.getStation().getStationName()))
                    report.setStation(station);
        if(report.getUserAccount()!=null)
            for(UserAccount userAccount : userAccountsRepository.findAll()){
                if(userAccount.getId() == report.getUserAccount().getId())
                    report.setUserAccount(userAccount);
            }
//        if(report.getCleaningSchedule()!=null)
//            cleaningScheduleRepository.save(report.getCleaningSchedule());
//        if(report != null && !report.getMaterialSchedules().isEmpty())
//            for(MaterialSchedule ms : report.getMaterialSchedules())
//                materialScheduleRepository.save(ms);
        report.setCleaningSchedule(report.getStation().getCleaningSchedule());
        report.setMaterialSchedules(new ArrayList<MaterialSchedule>(report.getStation().getMaterialSchedules()));
        reportService.addReport(report);
    }
    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public void updateReport(@RequestBody final Report report) {
        if(report.getStation()!=null)
            for(Station station : stationRepository.findAll())
                if(station.getStationName().equals(report.getStation().getStationName()))
                    report.setStation(station);
        if(report.getUserAccount()!=null)
            for(UserAccount userAccount : userAccountsRepository.findAll()){
                if(userAccount.getId() == report.getUserAccount().getId())
                    report.setUserAccount(userAccount);
            }
//        if(report.getCleaningSchedule()!=null)
//            cleaningScheduleRepository.save(report.getCleaningSchedule());
//        if(report != null && !report.getMaterialSchedules().isEmpty())
//            for(MaterialSchedule ms : report.getMaterialSchedules())
//                materialScheduleRepository.save(ms);
        report.setCleaningSchedule(report.getStation().getCleaningSchedule());
        report.setMaterialSchedules(new ArrayList<MaterialSchedule>(report.getStation().getMaterialSchedules()));

        reportService.updateReport(report);
    }
}
