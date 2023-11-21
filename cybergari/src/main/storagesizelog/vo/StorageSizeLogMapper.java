package main.storagesizelog.vo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorageSizeLogMapper {
    StorageSizeLogVO toVo(StorageSizeLog entity);
}
