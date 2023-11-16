package main.controller;

import main.report.ReportService;
import main.report.vo.Report;
import main.tag.TagService;
import main.tag.vos.FileTagUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService service;
    @PutMapping("/tags/{userId}")
    public void updateFileTags(@RequestBody final List<FileTagUpdateVO> request, @PathVariable final String userId) {
        service.updateFileTags(request, userId);
    }

}
