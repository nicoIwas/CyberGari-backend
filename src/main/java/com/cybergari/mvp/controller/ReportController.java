package com.cybergari.mvp.controller;


import com.cybergari.mvp.report.ReportService;
import com.cybergari.mvp.report.vo.Report;
import com.cybergari.mvp.report.vo.ReportConfirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {
    @Autowired
    private ReportService service;
    @GetMapping("/reports/{userId}")
    @CrossOrigin
    public Report generateReport(@PathVariable final String userId) {
        return service.generateReportForUser(userId);
    }

    @PutMapping("/reports")
    @CrossOrigin
    public List<String> executeReport(@RequestBody final ReportConfirmation request) {
        return service.executeReport(request);
    }
}

