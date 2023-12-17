package com.cybergari.mvp.client;


import com.cybergari.mvp.controller.response.LoginResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleAuthClient {

    public LoginResponse getLoginInformation(final String code) {
        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        GoogleAuthWrapper result = template.getForObject(
                "https://www.googleapis.com/oauth2/v3/userinfo?access_token={user}", GoogleAuthWrapper.class, code);
        return LoginResponse.builder()
                .userId(result.getSub())
                .userName(result.getName())
                .build();
    }
}
