package main.controller;

import main.report.ReportService;
import main.report.vo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    private ReportService service;
    @GetMapping("/reports/{userId}")
    @CrossOrigin
    public Report generateReport(@PathVariable final String userId) {
        return service.generateReportForUser(userId);
    }
// a gente tem como testar?

}

