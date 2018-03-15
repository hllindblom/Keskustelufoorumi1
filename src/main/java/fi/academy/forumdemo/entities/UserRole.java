package fi.academy.forumdemo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserRole {

    @Id
    private String role;

    @OneToMany(mappedBy = "rooli")
    List<User> kayttajat;

    public UserRole() {
    }

    public UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getKayttajat() {
        return kayttajat;
    }

    public void setKayttajat(List<User> kayttajat) {
        this.kayttajat = kayttajat;
    }
}
