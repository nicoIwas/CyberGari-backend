package main.storagesizelog.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
public class StorageSizeLogVO {
    final Instant reportTimestamp;
    final double preOptimizationSize;
    final double postOptimizationSize;
}
