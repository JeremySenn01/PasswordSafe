package ch.bbw.PasswordSafe.model;

public class Entry {
    private String url;
    private String username;
    private String password;
    private String notes;
    private String email;

    public Entry(String url, String username, String password, String notes, String email) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.notes = notes;
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
