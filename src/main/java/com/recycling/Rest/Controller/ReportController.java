package com.recycling.Rest.Controller;

import com.recycling.Rest.Service.ReportService;
import com.recycling.production.Report;
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

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void addReport(@RequestBody final Report report) {
        reportService.addReport(report);
    }
}
