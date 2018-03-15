package fi.academy.forumdemo.controllers;

import fi.academy.forumdemo.configurations.UserService;
import fi.academy.forumdemo.entities.User;
import fi.academy.forumdemo.entities.UserRole;
import fi.academy.forumdemo.repositories.AlueRepository;
import fi.academy.forumdemo.repositories.UserRepository;
import fi.academy.forumdemo.repositories.UserRoleRepository;
import fi.academy.forumdemo.repositories.ViestiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityKontrolleri {
    private ViestiRepository vr;
    private AlueRepository ar;
    private UserService userService;
    private UserRoleRepository urr;
    private UserRepository ur;
    private BCryptPasswordEncoder bcpe;

    @Autowired
    public SecurityKontrolleri(ViestiRepository vr, AlueRepository ar, UserService userService, UserRoleRepository urr, UserRepository ur, BCryptPasswordEncoder bcpe) {
        this.vr = vr;
        this.ar = ar;
        this.userService = userService;
        this.urr = urr;
        this.ur = ur;
        this.bcpe = bcpe;
    }

    @RequestMapping("/admin")
    public String hallinnoi(Authentication authentication, Model model){
        model.addAttribute("auth", authentication);
        return "adminEtusivu";
    }

    @RequestMapping("/login")
    public String login(Authentication authentication, Model model){
        model.addAttribute("kayttaja", new User());
        model.addAttribute("auth", authentication);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("bööööööööööööööööööööööööööööööööööööö");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:etusivu";
    }




    @GetMapping("/rekisteroityminen")
    public String rekisteroidy(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication);
        User uusi = new User();
        UserRole role = urr.findByRole("user");
        uusi.setRooli(role);
        uusi.setActive(1);
        model.addAttribute("uusiKayttaja", uusi);
        return "rekisteroityminen";
    }

    @PostMapping("/rekisteroi")
    public String rekisteroi(User uusi, Authentication authentication, Model model){
        String pw = uusi.getPassword();
        uusi.setPassword(bcpe.encode(pw));
        ur.save(uusi);
        model.addAttribute("auth", authentication);
        return "redirect:login";
    }
/*
    @RequestMapping(value = "/username")
    public String currentUserName(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication);
        return "nav";
    }*/

    @RequestMapping("/kayttaja")
    public String naytaKayttajanTiedot(Authentication authentication, Model model){
        User kayttaja = ur.findByUsername(authentication.getName());
        model.addAttribute("kayttaja", kayttaja);
        model.addAttribute("auth", authentication);
        return "kayttaja";
    }

    @RequestMapping("/muokkaaKayttajaa")
    public String muokkaaKayttajaa(Authentication authentication, Model model){
        User kayttaja = ur.findByUsername(authentication.getName());
        model.addAttribute("kayttaja", kayttaja);
        model.addAttribute("auth", authentication);
        return "muokkaaKayttaja";
    }

    @PostMapping("/tallennaTiedot")
    public String tallennaTiedot(User muokattu, Authentication authentication, Model model){
        ur.save(muokattu);
        model.addAttribute("kayttaja", muokattu);
        model.addAttribute("auth", authentication);
        return "kayttaja";
    }

}



