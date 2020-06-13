package ch.bbw.PasswordSafe.model;

import java.util.List;

public class User {

    private String username;
    private String password;
    private List<Entry> entries;

    public User(String username, String password, List<Entry> entries) {
        this.username = username;
        this.password = password;
        this.entries = entries;
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

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }
}
