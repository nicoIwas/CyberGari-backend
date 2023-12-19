package com.cybergari.mvp.fixtures;

import com.cybergari.mvp.storagesizelog.vo.StorageSizeLog;
import com.cybergari.mvp.storagesizelog.vo.StorageSizeLogId;

import java.time.Instant;

public class StorageSizeLogFixture {

    public static StorageSizeLog load() {
        return new StorageSizeLog(
                new StorageSizeLogId(
                        "ID",
                        Instant.now()
                ),
                2.5,
                2.0
        );
    }
}
