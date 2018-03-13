package fi.academy.forumdemo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Alue {
//    @Id @GeneratedValue
//    private Integer id;

    @Id
    private String nimi;

    @OneToMany (mappedBy = "alue")
    private List<Viesti> viestit;

    public Alue() {
    }

    public Alue(String nimi) {
        this.nimi = nimi;
    }


    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public List<Viesti> getViestit() {
        return viestit;
    }

    public void setViestit(List<Viesti> viestit) {
        this.viestit = viestit;
    }
}
