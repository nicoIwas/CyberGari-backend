package main.storagesizelog.vo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StorageSizeLogMapper {
    @Mapping(source = "storageSizeLogId.reportTimestamp", target = "reportTimestamp")
    StorageSizeLogVO toVo(StorageSizeLog entity);
}
