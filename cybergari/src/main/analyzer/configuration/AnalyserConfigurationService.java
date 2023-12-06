package main.analyzer.configuration;

import main.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyserConfigurationService {
    @Autowired
    public UserService userService;
    @Autowired
    public AnalyserConfigurationMapper mapper;

    public AnalyserConfiguration findConfigurationsForUser(final String userId) {
        try {
            final var userData = userService.findUserById(userId);
            return mapper.toAnalyserConfiguration(userData.getUserConfig());
        } catch (final Exception e) {
            return getAllAccesses();
        }
    }

    // TODO: only for testing
    private AnalyserConfiguration getAllAccesses() {
        return new AnalyserConfiguration(true, true, true, true, true);
    }
}
