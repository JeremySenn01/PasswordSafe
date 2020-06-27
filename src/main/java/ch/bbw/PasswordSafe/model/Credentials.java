package ch.bbw.PasswordSafe.model;

import javax.validation.constraints.NotNull;

public class Credentials {
    @NotNull(message = "Username can't be empty")
    private String username;
    @NotNull(message = "Password can't be empty")
    private String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Credentials() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
