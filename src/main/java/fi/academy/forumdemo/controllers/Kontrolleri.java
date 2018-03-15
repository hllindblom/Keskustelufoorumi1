package fi.academy.forumdemo.controllers;

import fi.academy.forumdemo.configurations.UserService;
import fi.academy.forumdemo.entities.Alue;
import fi.academy.forumdemo.entities.User;
import fi.academy.forumdemo.entities.Viesti;
import fi.academy.forumdemo.repositories.AlueRepository;
import fi.academy.forumdemo.repositories.ViestiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class Kontrolleri {
    private ViestiRepository vr;
    private AlueRepository ar;
    private UserService userService;

    @Autowired
    public Kontrolleri(ViestiRepository vr, AlueRepository ar, UserService userService) {
        this.vr = vr;
        this.ar = ar;
        this.userService = userService;
    }

    @GetMapping("/alueet")
    public String alueet(Model model) {
        model.addAttribute("alueet", ar.findAll());
        return "alueet";
    }


    @GetMapping("/alue")
    public String alue(@RequestParam(name = "nimi") String nimi, Model model) {
        Optional<Alue> optAlue = ar.findById(nimi);
        if (optAlue.isPresent()) {
            Alue alue = optAlue.get();
            List<Viesti> langat = vr.haeViestitIlmanParenttia(alue);
            model.addAttribute("alue", alue);
            model.addAttribute("langat", langat);
            return "langat";
        }
        throw new RuntimeException("VIRHE");
    }


    //näyttää viestin ja viestin vastauksen, ei vielä vastausten vastauksia
    @RequestMapping("/naytaViestiketju")
    public String viestiketju(@RequestParam(name = "id") int id, Model model) {
        Optional<Viesti> optViesti = vr.findById(id);
        if (optViesti.isPresent()) {
            Viesti viesti = optViesti.get();
            while (viesti.getParent() != null) {
                viesti = viesti.getParent();
            }
            model.addAttribute("viesti", viesti);
            return "viestiketjut";
        }
        throw new RuntimeException("VIRHE");
    }


    @GetMapping("/etusivu")
    public String uusimmatViestitEtusivulle(Model model) {
        List<Viesti> uudet = vr.ViestitAikajarjestyksessa();
        List<Viesti> limited = uudet.stream()
                .limit(10)
                .collect(Collectors.toList());
        model.addAttribute("limited", limited);
        return "etusivu";
    }




    @RequestMapping("/admin")
    public String hallinnoi(Model model){
        return "adminEtusivu";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("kayttaja", new User());
        return "login";
    }

    @GetMapping("/rekisteroityminen")
    public String rekisteroidy(Model model) {
        return "rekisteroityminen";
    }

}



