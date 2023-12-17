package com.cybergari.mvp.storagesizelog;

import com.cybergari.mvp.storagesizelog.vo.StorageSizeLog;
import com.cybergari.mvp.storagesizelog.vo.StorageSizeLogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface StorageSizeLogRepository extends JpaRepository<StorageSizeLog, StorageSizeLogId> {
    @Query("""
        FROM StorageSizeLog ssl
        WHERE ssl.storageSizeLogId.userId = :userId AND
        ssl.storageSizeLogId.reportTimestamp >= :startWindow AND
        ssl.storageSizeLogId.reportTimestamp <= :endWindow
    """)
    List<StorageSizeLog> findByUserIdAndTimestamp(String userId, Instant startWindow, Instant endWindow);
}
