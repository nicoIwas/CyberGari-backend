package main.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private String userId;
    private String userName;
    private boolean isNewUser;
}
