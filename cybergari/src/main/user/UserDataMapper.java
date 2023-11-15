package main.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserDataMapper {

    void update(@MappingTarget UserData entity, UserData updateEntity);
}
