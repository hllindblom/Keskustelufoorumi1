package fi.academy.forumdemo.controllers;


import fi.academy.forumdemo.configurations.UserService;
import fi.academy.forumdemo.entities.Hakusana;
import fi.academy.forumdemo.entities.Viesti;
import fi.academy.forumdemo.repositories.AlueRepository;
import fi.academy.forumdemo.repositories.ViestiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.AuthenticationException;
import java.net.Authenticator;
import java.util.List;

@Controller
public class HakuKontrolleri {
    private ViestiRepository vr;
    private AlueRepository ar;
    private UserService userService;

    @Autowired
    public HakuKontrolleri(ViestiRepository vr, AlueRepository ar, UserService userService) {
        this.vr = vr;
        this.ar = ar;
        this.userService = userService;
    }


    @PostMapping("/haku")
    public String haku(Authentication authentication, Hakusana hakusana, Model model) {
        List<Viesti> haetutViestit = vr.haeViestitHakusanalla(hakusana.getHakusana());
        List<Viesti> haetutViestitKirjoittaja = vr.haeKirjoittajanViestit(hakusana.getHakusana());
        model.addAttribute("haetutViestit", haetutViestit);
        model.addAttribute("haetutviestitkirjoittaja", haetutViestitKirjoittaja);
        model.addAttribute("auth", authentication);
        return "haku";
    }

    @GetMapping("/nav")
    public String hakuKasittelija(Authentication auth, Model model) {
        Hakusana hakusana = new Hakusana();
        model.addAttribute("hakusana", hakusana);
        model.addAttribute("auth", auth);
        return "nav";
    }

}



