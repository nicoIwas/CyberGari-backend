package main.controller;

import main.controller.response.FileResponse;
import main.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilesController {

    @Autowired
    FileService fileService;

    @CrossOrigin
    @GetMapping("/files/")
    public List<FileResponse> getAllFiles() {
        return fileService.getAllFiles();
    }

    @CrossOrigin
    @GetMapping("/files/compressed")
    public List<FileResponse> getCompressedFiles() {
        return fileService.getCompressedFiles();
    }

//    @PutMapping("/files/uncompress")
//    @CrossOrigin
//    public List<String> uncompressFiles() {
//
//    }
}
