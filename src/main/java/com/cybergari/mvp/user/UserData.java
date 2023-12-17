package com.cybergari.mvp.user;

import com.cybergari.mvp.user.configuration.UserConfig;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class UserData {

    @Id
    private String id;
    @Embedded
    private UserConfig userConfig;

    public UserData(String id) {
        this.id = id;
        this.userConfig = new UserConfig();
    }

    public UserData() {

    }

    public UserData(String id, UserConfig userConfig) {
        this.id = id;
        this.userConfig = userConfig;
    }
}
