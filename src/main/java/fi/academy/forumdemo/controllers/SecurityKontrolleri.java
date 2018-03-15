package fi.academy.forumdemo.controllers;

import fi.academy.forumdemo.configurations.UserService;
import fi.academy.forumdemo.entities.User;
import fi.academy.forumdemo.repositories.AlueRepository;
import fi.academy.forumdemo.repositories.ViestiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityKontrolleri {
    private ViestiRepository vr;
    private AlueRepository ar;
    private UserService userService;

    @Autowired
    public SecurityKontrolleri(ViestiRepository vr, AlueRepository ar, UserService userService) {
        this.vr = vr;
        this.ar = ar;
        this.userService = userService;
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



