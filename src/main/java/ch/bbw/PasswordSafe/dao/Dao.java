package ch.bbw.PasswordSafe.dao;

import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.model.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class Dao {

    private List<User> users = Arrays.asList(
            new User(1, "lilJay", "123456", Arrays.asList(
                    new Entry(1, "someUrl", "jeremySenn01", "password", null, null),
                    new Entry(2, "someUrl1", "jeremySenn01", "hehehee", null, null))),
            new User(2, "bigJay", "654321", Arrays.asList(
                    new Entry(3, "testUrl", "jjswagger", "pwlol", null, null),
                    new Entry(4, "testUrl1", "joe", "sdkfj", null, null))));

    public Optional<User> getUserByCredentials(String hashedPw, String username) {
        return this.users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(hashedPw))
                .findFirst();
    }

    public Entry addEntry(Entry entry, int userId) {
        return null;
    }

    public void deleteEntry(int entryId) {

    }

    public Entry updateEntry(Entry entry) {
        return null;
    }
}
