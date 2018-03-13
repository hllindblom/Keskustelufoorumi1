package fi.academy.forumdemo.controllers;

import fi.academy.forumdemo.entities.Alue;
import fi.academy.forumdemo.repositories.AlueRepository;
import fi.academy.forumdemo.repositories.ViestiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            model.addAttribute("alue", optAlue.get());
            return "langat";
        }
        throw new RuntimeException("VIRHE");
    }
}
