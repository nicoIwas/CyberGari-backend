package main.storagesizelog;

import main.storagesizelog.vo.StorageSizeLog;
import main.storagesizelog.vo.StorageSizeLogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageSizeLogRepository extends JpaRepository<StorageSizeLog, StorageSizeLogId> {
}
