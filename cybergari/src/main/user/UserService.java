package main.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserDataRepository userDataRepository;
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
}
