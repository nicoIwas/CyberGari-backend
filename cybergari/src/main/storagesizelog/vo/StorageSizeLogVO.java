package main.storagesizelog.vo;

import java.time.Instant;

public record StorageSizeLogVO(Instant reportTimestamp, double preOptimizationSize, double postOptimizationSize) {
}
