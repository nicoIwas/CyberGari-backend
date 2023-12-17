package com.cybergari.mvp.fixtures;

import com.cybergari.mvp.user.UserData;
import com.cybergari.mvp.user.configuration.UserConfig;

public class UserDataFixture {

    public static UserData load() {
        return new UserData(
                "ID",
                new UserConfig()
        );
    }
}
