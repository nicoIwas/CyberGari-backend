package com.cybergari.mvp;

import com.cybergari.mvp.storagesizelog.StorageSizeLogRepository;
import com.cybergari.mvp.storagesizelog.StorageSizeLogService;
import com.cybergari.mvp.storagesizelog.vo.StorageSizeLog;
import com.cybergari.mvp.storagesizelog.vo.StorageSizeLogMapper;
import com.cybergari.mvp.storagesizelog.vo.StorageSizeLogVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

@SpringBootTest
public class StorageSizeLogServiceTest {

    @Autowired
    StorageSizeLogService storageSizeLogService;
    @Autowired
    StorageSizeLogRepository repository;
    @Autowired
    StorageSizeLogMapper mapper;

    @BeforeEach
    void init() {
        repository.deleteAll();
    }

    @Test
    public void givenValidTimestampsAndUserId_WhenSavingANewLog_ShouldPersistTheCorrectValues() {
        storageSizeLogService.save(2.1, 2, "ID");
        final List<StorageSizeLog> logList = repository.findAll();
        Assertions.assertEquals(1, logList.size());
        Assertions.assertEquals(2.1, logList.get(0).getPreOptimizationSize());
        Assertions.assertEquals(2, logList.get(0).getPostOptimizationSize());
    }

    @Test
    public void givenValidUserIdAndTimeWindow_WhenGettingAllLogs_ShouldReturnOnlyTheCorrectOnes() {
        givenLogsFromDifferentUsers();

        final List<StorageSizeLogVO> logList = storageSizeLogService.getLogs(
                "ID",
                Instant.now().minusSeconds(1000),
                Instant.now().plusSeconds(1000)
        );

        Assertions.assertEquals(3, logList.size());
    }

    private void givenLogsFromDifferentUsers() {
        storageSizeLogService.save(2.1, 2, "ID");
        storageSizeLogService.save(3.1, 2, "ID");
        storageSizeLogService.save(4.1, 2, "ID");
        storageSizeLogService.save(1.1, 2, "ANOTHER_ID");
        storageSizeLogService.save(6.1, 2, "ANOTHER_ID");
    }
}
