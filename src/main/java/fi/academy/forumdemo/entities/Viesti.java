package fi.academy.forumdemo.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Viesti {
    @Id
    @GeneratedValue
    private Integer viesti_id;
    private String otsikko;
    private String kirjoittaja;
    private String teksti;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime aika;

    @ManyToOne
    private Viesti parent;

    @OneToMany(mappedBy = "parent")
    private List<Viesti> lapset;

    @ManyToOne
    @JoinColumn(name = "alue")
    private Alue alue;

    public Viesti() {
    }

    public Viesti(String kirjoittaja, String teksti) {
        this.kirjoittaja = kirjoittaja;
        this.teksti = teksti;
    }

    public Viesti(String otsikko, String kirjoittaja, String teksti) {
        this.otsikko = otsikko;
        this.kirjoittaja = kirjoittaja;
        this.teksti = teksti;
    }

    public Integer getViesti_id() {
        return viesti_id;
    }

    public void setViesti_id(Integer viesti_id) {
        this.viesti_id = viesti_id;
    }

    public Alue getAlue() {
        return alue;
    }

    public void setAlue(Alue alue) {
        this.alue = alue;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }

    public String getTeksti() {
        return teksti;
    }

    public void setTeksti(String teksti) {
        this.teksti = teksti;
    }

    public LocalDateTime getAika() {
        return aika;
    }

    public void setAika(LocalDateTime aika) {
        this.aika = aika;
    }

    public Viesti getParent() {
        return parent;
    }

    public void setParent(Viesti parent) {
        this.parent = parent;
    }

    public List<Viesti> getLapset() {
        return lapset;
    }

    public void setLapset(List<Viesti> lapset) {
        this.lapset = lapset;
    }
}
