package main.storagesizelog;

import main.storagesizelog.vo.StorageSizeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class StorageSizeLogService {
    @Autowired
    StorageSizeLogRepository repository;

    public void save(final double preOptimizationSize, final double postOptimizationSize, final String userId) {
        repository.save(new StorageSizeLog(
                userId,
                Instant.now(),
                preOptimizationSize,
                postOptimizationSize
        ));
    }
}
