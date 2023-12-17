package com.cybergari.mvp.controller;

import com.cybergari.mvp.filemanager.local.LocalFileManager;
import com.cybergari.mvp.storagesizelog.StorageSizeLogService;


import com.cybergari.mvp.storagesizelog.vo.StorageSizeLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class StorageSizeLogController {
    private static final int WINDOW_SIZE = 30;

    @Autowired
    StorageSizeLogService service;

    @Autowired
    LocalFileManager fileService;

    @CrossOrigin
    @GetMapping("/storage-size-log/{userId}")
    public List<StorageSizeLogVO> getStorageSizeLogs(@PathVariable final String userId) {
        final var now = Instant.now();

        return service.getLogs(userId, now.minus(WINDOW_SIZE, ChronoUnit.DAYS), now);
    }

    @CrossOrigin
    @PostMapping("/decompress/{userId}")
    public List<String> uncompressFiles(@RequestBody final List<String> toDecompress, @PathVariable final String userId) {
        return toDecompress.stream().filter(fileId -> !fileService.uncompressFile(fileId)).toList();
    }
}
