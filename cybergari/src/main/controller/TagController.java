package main.controller;

import main.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

    @Autowired
    private ReportService service;


}
