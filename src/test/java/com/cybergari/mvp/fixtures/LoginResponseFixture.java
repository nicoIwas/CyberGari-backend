package com.cybergari.mvp.fixtures;

import com.cybergari.mvp.controller.response.LoginResponse;

public class LoginResponseFixture {

    public static LoginResponse load() {
        return LoginResponse.builder()
                .userId("ID")
                .userName("name")
                .build();
    }
}
