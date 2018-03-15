package fi.academy.forumdemo.controllers;

import fi.academy.forumdemo.configurations.UserService;
import fi.academy.forumdemo.entities.Alue;
import fi.academy.forumdemo.entities.Viesti;
import fi.academy.forumdemo.repositories.AlueRepository;
import fi.academy.forumdemo.repositories.ViestiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private UserService userService;

    @Autowired
    public LuomisKontrolleri(ViestiRepository vr, AlueRepository ar, UserService userService) {
        this.vr = vr;
        this.ar = ar;
        this.userService = userService;
    }


    // Luodaan uusi keskustelulanka
    @RequestMapping("/uusilanka")
    public String luoUusiViestiKetjuLomake(@RequestParam(name = "alue") String alue, Authentication authentication, Model model) {
        Optional<Alue> optAlue = ar.findById(alue);
        if (optAlue.isPresent()) {
            Alue hae = optAlue.get();
            Viesti uusiViesti = new Viesti();
            uusiViesti.setAlue(hae);
            model.addAttribute("uusiViesti", uusiViesti);
            model.addAttribute("auth", authentication);
            return "uusilanka";

        }
        throw new RuntimeException("VIRHE");

    }

    @PostMapping("/viestiketjut")
    public String uudenViestinLomakeKasittelija(Viesti uusiViesti, Authentication authentication, Model model) {
        vr.save(uusiViesti);
        model.addAttribute("auth", authentication);
        return "redirect:naytaViestiketju?id=" + uusiViesti.getViesti_id();
    }


    @GetMapping("/vastaa")
    public String vastaa(@RequestParam(name = "id") int id, Authentication authentication, Model model) {
        Optional<Viesti> optViesti = vr.findById(id);
        if (optViesti.isPresent()) {
            Viesti mihinVastataan = optViesti.get();
            Viesti uusiViesti = new Viesti();
            uusiViesti.setParent(mihinVastataan); //asetetaan parent-kentän arvo
            uusiViesti.setAlue(mihinVastataan.getAlue()); //asetetaan alueeksi parentin alue
            model.addAttribute("uusiViesti", uusiViesti);
            model.addAttribute("auth", authentication);
            return "kirjoitaVastaus";
        }
        throw new RuntimeException("VIRHE");
    }

    @PostMapping("/luoUusiVastaus")
    public String tallennavastaus(Viesti viesti, Authentication authentication, Model model) {
        vr.save(viesti);
        model.addAttribute("alueet", ar.findAll());
        model.addAttribute("auth", authentication);
        return "redirect:naytaViestiketju?id=" + viesti.getViesti_id();
    }


}


