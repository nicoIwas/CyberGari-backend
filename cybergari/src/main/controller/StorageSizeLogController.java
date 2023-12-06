package main.controller;

import main.filemanager.local.LocalFileManager;
import main.storagesizelog.StorageSizeLogService;
import main.storagesizelog.vo.StorageSizeLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StorageSizeLogController {
    private static final int WINDOW_SIZE = 30;

    @Autowired
    StorageSizeLogService service;

    @Autowired
    LocalFileManager fileService;

    @GetMapping("/storage-size-log/{userId}")
    public List<StorageSizeLogVO> getStorageSizeLogs(@PathVariable final String userId) {
        final var now = Instant.now();

        return service.getLogs(userId, now.minus(WINDOW_SIZE, ChronoUnit.DAYS), now);
    }

    @PostMapping("/decompress/{userId}")
    public List<String> uncompressFiles(@RequestBody final List<String> toUncompress, @PathVariable final String userId){

        List<String> stillCompressed = new ArrayList<>();

        for (String fileId : toUncompress) {
            if(!fileService.uncompressFile(fileId)){
                stillCompressed.add(fileId);
            }
        }
        return stillCompressed;
    }
}
