package com.recycling.Rest.Controller;

import com.recycling.DB.repository.StationRepository;
import com.recycling.DB.repository.UserAccountsRepository;
import com.recycling.Rest.Service.ReportService;
import com.recycling.production.Report;
import com.recycling.production.Station;
import com.recycling.production.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void addReport(@RequestBody final Report report) {
        if(report.getStation()!=null)
            for(Station station : stationRepository.findAll())
                if(station.getStationName().equals(report.getStation().getStationName()))
                    report.setStation(station);
        if(report.getUserAccount()!=null)
            for(UserAccount userAccount : userAccountsRepository.findAll())
                if(userAccount.getId() == report.getUserAccount().getId())
                    report.setUserAccount(userAccount);

        reportService.addReport(report);
    }
}
