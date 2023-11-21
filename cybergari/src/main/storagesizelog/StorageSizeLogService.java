package main.storagesizelog;

import main.storagesizelog.vo.StorageSizeLog;
import main.storagesizelog.vo.StorageSizeLogMapper;
import main.storagesizelog.vo.StorageSizeLogVO;
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
        return repository.findAll().stream().map(log -> mapper.toVo(log)).collect(Collectors.toList());
    }
}