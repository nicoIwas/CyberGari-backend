package com.cybergari.mvp.storagesizelog.vo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageSizeLog {
    @EmbeddedId
    private StorageSizeLogId storageSizeLogId;
    private double preOptimizationSize;
    private double postOptimizationSize;

    public StorageSizeLog(final String userId, final Instant reportTimestamp, final double preOptimizationSize,
                          final double postOptimizationSize) {
        this.storageSizeLogId = new StorageSizeLogId(userId, reportTimestamp);
        this.preOptimizationSize = preOptimizationSize;
        this.postOptimizationSize = postOptimizationSize;
    }
}
