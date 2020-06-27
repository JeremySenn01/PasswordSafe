package ch.bbw.PasswordSafe.model;
import javax.validation.constraints.*;
import java.util.UUID;

public class Entry {
    private UUID id;
    private String url;
    @NotNull(message = "username can't be empty")
    private String username;
    @NotNull(message = "username can't be empty")
    @Size(min = 5, max = 20, message = "must be between 5 and 20 characters")
    private String password;
    private String notes;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email is invalid")
    private String email;

    public Entry(UUID id,String url, String username, String password, String notes, String email) {
        this.id = id;
        this.url = url;
        this.username = username;
        this.password = password;
        this.notes = notes;
        this.email = email;
    }
    
    public Entry() {
    	this.id = UUID.randomUUID();
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
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
