package main.user;

import lombok.Data;
import main.user.configuration.UserConfig;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

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
}
