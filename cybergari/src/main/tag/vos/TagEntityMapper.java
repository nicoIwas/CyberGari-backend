package main.tag.vos;

import main.storagesizelog.vo.StorageSizeLog;
import main.storagesizelog.vo.StorageSizeLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagEntityMapper {
    @Mapping(source = "tagEntityId.name", target = "name")
    @Mapping(source = "priority", target = "tagPriority")
    @Mapping(source = "color", target = "tagColor")
    Tag toDomain(TagEntity entity);
}
