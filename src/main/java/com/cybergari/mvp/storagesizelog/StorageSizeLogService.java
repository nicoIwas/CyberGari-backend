package com.cybergari.mvp.storagesizelog;

import com.cybergari.mvp.storagesizelog.vo.StorageSizeLog;
import com.cybergari.mvp.storagesizelog.vo.StorageSizeLogMapper;
import com.cybergari.mvp.storagesizelog.vo.StorageSizeLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageSizeLogService {
    @Autowired
    StorageSizeLogRepository repository;
    @Autowired
    StorageSizeLogMapper mapper;

    public void save(final double preOptimizationSize, final double postOptimizationSize, final String userId) {
        repository.save(new StorageSizeLog(
                userId,
                Instant.now(),
                preOptimizationSize,
                postOptimizationSize
        ));
    }

    public List<StorageSizeLogVO> getLogs(final String userId, final Instant startWindow, final Instant endWindow) {
        return repository.findByUserIdAndTimestamp(userId, startWindow, endWindow).stream()
                .map(log -> mapper.toVo(log)).collect(Collectors.toList());
    }
}
