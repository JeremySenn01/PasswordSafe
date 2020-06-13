package ch.bbw.PasswordSafe.dao;

import ch.bbw.PasswordSafe.model.Credentials;
import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.model.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class Dao {

    private List<User> users = Arrays.asList(
            new User("lilJay", "123456", Arrays.asList(
                    new Entry("someUrl", "jeremySenn01", "password", null, null),
                    new Entry("someUrl1", "jeremySenn01", "hehehee", null, null))),
            new User("bigJay", "654321", Arrays.asList(
                    new Entry("testUrl", "jjswagger", "pwlol", null, null),
                    new Entry("testUrl1", "joe", "sdkfj", null, null))));

    public Optional<User> getUser(Credentials credentials) {
        //TODO: Hash password
        String pwHash = null;
        return this.users.stream()
                .filter(user -> user.getUsername().equals(credentials.getUsername()) && user.getPassword().equals(pwHash))
                .findFirst();
    }
}
