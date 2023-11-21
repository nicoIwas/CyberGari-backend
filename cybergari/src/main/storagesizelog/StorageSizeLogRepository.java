package main.storagesizelog;

import main.storagesizelog.vo.StorageSizeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageSizeLogRepository extends JpaRepository<StorageSizeLog, String> {
}
