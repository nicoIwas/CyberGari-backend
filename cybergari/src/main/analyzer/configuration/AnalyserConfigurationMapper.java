package main.analyzer.configuration;

import main.user.configuration.UserConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnalyserConfigurationMapper {
    AnalyserConfiguration toAnalyserConfiguration(UserConfig userConfig);
}
