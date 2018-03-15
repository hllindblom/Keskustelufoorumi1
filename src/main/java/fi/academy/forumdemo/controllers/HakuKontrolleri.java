package fi.academy.forumdemo.controllers;


import fi.academy.forumdemo.entities.Hakusana;
import fi.academy.forumdemo.entities.Viesti;
import fi.academy.forumdemo.repositories.AlueRepository;
import fi.academy.forumdemo.repositories.ViestiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HakuKontrolleri {
    private ViestiRepository vr;
    private AlueRepository ar;

    @Autowired
    public HakuKontrolleri(ViestiRepository vr, AlueRepository ar) {
        this.vr = vr;
        this.ar = ar;
    }


    @PostMapping("/haku")
    public String haku(Hakusana hakusana, Model model) {
        List<Viesti> haetutViestit = vr.haeViestitHakusanalla(hakusana.getHakusana());
        List<Viesti> haetutViestitKirjoittaja = vr.haeKirjoittajanViestit(hakusana.getHakusana());
        model.addAttribute("haetutViestit", haetutViestit);
        model.addAttribute("haetutviestitkirjoittaja", haetutViestitKirjoittaja);
        return "haku";
    }

    @GetMapping("/nav")
    public String hakuKasittelija(Model model) {
        Hakusana hakusana = new Hakusana();
        model.addAttribute("hakusana", hakusana);
        return "nav";
    }

}



