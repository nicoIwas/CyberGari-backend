package main.user;

import lombok.extern.slf4j.Slf4j;
import main.controller.response.LoginResponse;
import main.infra.client.GoogleAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
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
            final UserData newUser = new UserData(response.getUserId());
            userDataRepository.save(newUser);
        }

        return response;
    }
}
