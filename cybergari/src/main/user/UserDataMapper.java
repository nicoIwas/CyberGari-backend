package main.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserDataMapper {

    void update(@MappingTarget UserData entity, UserData updateEntity);
}
