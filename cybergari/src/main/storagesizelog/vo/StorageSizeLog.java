package main.storagesizelog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageSizeLog {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "report_timestamp ")
    private Instant reportTimestamp;

    @Column(name = "pre_optimization_size")
    private double preOptimizationSize;

    @Column(name = "post_optimization_size")
    private double postOptimizationSize;
}
