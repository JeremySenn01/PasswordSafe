package ch.bbw.PasswordSafe.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.model.User;

@Repository
public class Dao {

	
	
    private List<User> users = Arrays.asList(
            new User(1, "lilJay", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"),
            new User(2, "bigJay", "481f6cc0511143ccdd7e2d1b1b94faf0a700a8b49cd13922a70b5ae28acaa8c5"));

    public Optional<User> getUserByCredentials(String hashedPw, String username) {
//        return this.users.stream()
//                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(hashedPw))
//                .findFirst();
    	for(User user : this.users) {
    		if (user.getUsername().equals(username) && user.getPassword().equals(hashedPw)) {
    			return Optional.of(user);
    		}
    	}
    	return Optional.empty();
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
