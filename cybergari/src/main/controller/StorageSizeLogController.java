package main.controller;

import main.storagesizelog.StorageSizeLogService;
import main.storagesizelog.vo.StorageSizeLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class StorageSizeLogController {
    private static final int WINDOW_SIZE = 30;

    @Autowired
    StorageSizeLogService service;

    @GetMapping("/storage-size-log/{userId}")
    public List<StorageSizeLogVO> getStorageSizeLogs(@PathVariable final String userId) {
        final var now = Instant.now();

        return service.getLogs(userId, now.minus(WINDOW_SIZE, ChronoUnit.DAYS), now);
    }
}
