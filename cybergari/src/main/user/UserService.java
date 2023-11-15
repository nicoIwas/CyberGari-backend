package main.user;

import main.controller.response.LoginResponse;
import main.infra.client.GoogleAuthClient;
import main.user.configuration.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserDataRepository userDataRepository;
    @Autowired
    public GoogleAuthClient client;
    @Autowired
    public UserDataMapper mapper;

    public UserData findUserById(final String id) {
        return userDataRepository.findById(id).orElseThrow();
    }

    public UserData updateUser(final UserData userData) {
        final UserData oldUser = findUserById(userData.getId());
        mapper.update(oldUser, userData);
        return userDataRepository.save(oldUser);
    }

    public LoginResponse loginUser(final String code) {
        final LoginResponse response = client.getLoginInformation(code);
        response.setNewUser(false);

        final Optional<UserData> persistedUser = userDataRepository.findById(response.getUserId());
        if(persistedUser.isEmpty()) {
            response.setNewUser(true);
            final UserData newUser = UserData.builder()
                    .id(response.getUserId())
                    .userConfig(new UserConfig())
                    .build();
            userDataRepository.save(newUser);
        }

        return response;
    }
}
