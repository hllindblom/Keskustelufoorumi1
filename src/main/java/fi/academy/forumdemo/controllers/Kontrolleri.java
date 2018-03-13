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

import java.util.List;
import java.util.Optional;

@Controller
public class Kontrolleri {
    private ViestiRepository vr;
    private AlueRepository ar;

    @Autowired
    public Kontrolleri(ViestiRepository vr, AlueRepository ar) {
        this.vr = vr;
        this.ar = ar;
    }

    @GetMapping("/alueet")
    public String alueet(Model model){
        model.addAttribute("alueet", ar.findAll());
        return "alueet";
    }

    @GetMapping("/alue")
    public String alue(@RequestParam(name = "nimi") String nimi, Model model){
        Optional<Alue> optAlue = ar.findById(nimi);
        if (optAlue.isPresent()){
            Alue alue = optAlue.get();
            List<Viesti> langat = vr.haeViestitIlmanParenttia(alue);
            model.addAttribute("langat", langat);
            return "langat";
        }
        throw new RuntimeException("VIRHE");
    }

    @RequestMapping("/naytaViestiketju")
    public String viestiketju(@RequestParam(name="id") int id, Model model){
        //näyttää viestin ja viestin vastauksen, ei vielä vastausten vastauksia
        Optional<Viesti> optViesti = vr.findById(id);
        if(optViesti.isPresent()){
            model.addAttribute("viesti", optViesti.get());
            return  "viestiketjut";
        }
        throw new RuntimeException("VIRHE");
    }

    @GetMapping("/vastaa")
    public String vastaa(@RequestParam(name = "id") int id, Model model){
        Optional<Viesti> optViesti = vr.findById(id);
        if(optViesti.isPresent()){
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
    public String tallennavastaus(Viesti viesti, Model model){
        vr.save(viesti);

        while(viesti.getParent() != null){
            viesti = viesti.getParent();
        }

        model.addAttribute("alueet", ar.findAll());
        return "redirect:naytaViestiketju?id=" + viesti.getViesti_id();
    }
}
