package main.controller;

import main.controller.response.FileResponse;
import main.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @PutMapping("/files/uncompress")
    public List<String> uncompressFiles(@RequestBody final List<String> filesToUncompress) {
        return fileService.uncompressFiles(filesToUncompress);
    }
}
