package com.cybergari.mvp;

import com.cybergari.mvp.client.GoogleAuthClient;
import com.cybergari.mvp.controller.response.LoginResponse;
import com.cybergari.mvp.fixtures.LoginResponseFixture;
import com.cybergari.mvp.fixtures.UserDataFixture;
import com.cybergari.mvp.user.UserData;
import com.cybergari.mvp.user.UserDataMapper;
import com.cybergari.mvp.user.UserDataRepository;
import com.cybergari.mvp.user.UserService;
import com.cybergari.mvp.user.configuration.UserConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    public UserService userService;
    @Autowired
    public UserDataRepository userDataRepository;
    @MockBean
    public GoogleAuthClient client;
    @Autowired
    public UserDataMapper mapper;

    @BeforeEach
    void init() {
        userDataRepository.deleteAll();
    }

    @Test
    public void givenAValidUserId_WhenFindingById_ShouldReturnTheCorrectUser() {
        userDataRepository.save(UserDataFixture.load());
        final UserData user = userService.findUserById("ID");
        Assertions.assertTrue(Objects.nonNull(user));
        Assertions.assertEquals("ID", user.getId());
    }

    @Test
    public void givenAValidUserData_WhenUpdatingUser_ShouldSetTheFieldsCorrectly() {
        userDataRepository.save(UserDataFixture.load());
        final UserData expectedUser = givenAModifiedUserData();
        final UserData user = userService.updateUser(expectedUser);
        Assertions.assertTrue(Objects.nonNull(user));
        Assertions.assertEquals(expectedUser.getId(), user.getId());
        Assertions.assertEquals(0,
                thenAllFieldsShouldBeUpdated(expectedUser.getUserConfig(), user.getUserConfig())
        );
    }

    @Test
    public void givenAValidUserCode_WhenLogInANewUser_ShouldPersistOnDatabase() {
        when(client.getLoginInformation(any())).thenReturn(LoginResponseFixture.load());
        final LoginResponse userInfo = userService.loginUser("code");
        Assertions.assertTrue(userInfo.isNewUser());
        final Optional<UserData> user = userDataRepository.findById(userInfo.getUserId());
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("ID", user.get().getId());
    }

    @Test
    public void givenAValidUserCode_WhenLogInAnExistingUser_ShouldNotPersistOnDatabase() {
        when(client.getLoginInformation(any())).thenReturn(LoginResponseFixture.load());
        userDataRepository.save(UserDataFixture.load());
        final LoginResponse userInfo = userService.loginUser("code");
        Assertions.assertTrue(Objects.nonNull(userInfo));
        Assertions.assertEquals("ID", userInfo.getUserId());
        Assertions.assertFalse(userInfo.isNewUser());
    }

    private UserData givenAModifiedUserData() {
        final UserData userToUpdate = UserDataFixture.load();
        userToUpdate.getUserConfig().setNumVisualizations(true);
        userToUpdate.getUserConfig().setAutExclusion(true);
        userToUpdate.getUserConfig().setLastSeen(true);
        return userToUpdate;
    }

    public int thenAllFieldsShouldBeUpdated(final UserConfig expectedUser, final UserConfig actualUser) {
        return Comparator.comparing(UserConfig::isLastSeen)
                .thenComparing(UserConfig::isAutExclusion)
                .thenComparing(UserConfig::isNumVisualizations)
                .compare(expectedUser, actualUser);
    }
}
