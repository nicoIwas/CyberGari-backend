package com.cybergari.mvp.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleAuthWrapper {

    private String sub;
    private String name;
    private String givenName;
    private String familyName;
    private String picture;
    private String email;
    private String emailVerified;
    private String locale;
    private String hd;
}
