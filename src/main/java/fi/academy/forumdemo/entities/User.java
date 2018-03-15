package fi.academy.forumdemo.entities;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;
    private String email;
    private String description;
    private int active;

    @ManyToOne
    private UserRole rooli;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String email, String description) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.description = description;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, UserRole rooli) {
        this.username = username;
        this.password = password;
        this.rooli = rooli;
    }

    public User(String username, String password, int active, UserRole rooli) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.rooli = rooli;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserRole getRooli() {
        return rooli;
    }

    public void setRooli(UserRole rooli) {
        this.rooli = rooli;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", rooli=" + rooli +
                '}';
    }
}
