package com.cybergari.mvp.storagesizelog.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageSizeLogId implements Serializable {
    private String userId;
    private Instant reportTimestamp;
}
