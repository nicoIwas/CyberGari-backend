package main.storagesizelog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
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
