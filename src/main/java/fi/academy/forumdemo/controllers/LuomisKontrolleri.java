package fi.academy.forumdemo.controllers;

import fi.academy.forumdemo.entities.Alue;
import fi.academy.forumdemo.entities.Viesti;
import fi.academy.forumdemo.repositories.AlueRepository;
import fi.academy.forumdemo.repositories.ViestiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LuomisKontrolleri {
    private ViestiRepository vr;
    private AlueRepository ar;

    @Autowired
    public LuomisKontrolleri(ViestiRepository vr, AlueRepository ar) {
        this.vr = vr;
        this.ar = ar;
    }


    // Luodaan uusi keskustelulanka
    @RequestMapping("/uusilanka")
    public String luoUusiViestiKetjuLomake(@RequestParam(name = "alue") @Valid String alue, Model model) {
        Optional<Alue> optAlue = ar.findById(alue);
        if (optAlue.isPresent()) {
            Alue hae = optAlue.get();
            Viesti uusiViesti = new Viesti();
            uusiViesti.setAlue(hae);
            model.addAttribute("uusiViesti", uusiViesti);
            return "uusilanka";

        }
        throw new RuntimeException("VIRHE");

    }

    @PostMapping("/viestiketjut")
    public String uudenViestinLomakeKasittelija(Viesti uusiViesti, Model model) {
        vr.save(uusiViesti);
        return "redirect:naytaViestiketju?id=" + uusiViesti.getViesti_id();
    }


    @GetMapping("/vastaa")
    public String vastaa(@RequestParam(name = "id") int id, Model model) {
        Optional<Viesti> optViesti = vr.findById(id);
        if (optViesti.isPresent()) {
            Viesti mihinVastataan = optViesti.get();
            Viesti uusiViesti = new Viesti();
            uusiViesti.setParent(mihinVastataan); //asetetaan parent-kentän arvo
            uusiViesti.setAlue(mihinVastataan.getAlue()); //asetetaan alueeksi parentin alue
            model.addAttribute("uusiViesti", uusiViesti);
            return "kirjoitaVastaus";
        }
        throw new RuntimeException("VIRHE");
    }

    @PostMapping("/luoUusiVastaus")
    public String tallennavastaus(Viesti viesti, Model model) {
        vr.save(viesti);
        model.addAttribute("alueet", ar.findAll());
        return "redirect:naytaViestiketju?id=" + viesti.getViesti_id();
    }


}



