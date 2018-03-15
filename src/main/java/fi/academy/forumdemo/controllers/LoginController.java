package fi.academy.forumdemo.controllers;

import fi.academy.forumdemo.configurations.UserService;
import fi.academy.forumdemo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginSimppeli(Model model){
        model.addAttribute("kayttaja", new User());
        return "login";
    }
//
//    @PostMapping("/kasitteleKayttaja")
//    public String kasitteleKayttaja(User kayttaja){
//        return "etusivu";
//    }
//
//    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
//    public ModelAndView login() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }
//
//
//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
//    public ModelAndView registration() {
//        ModelAndView modelAndView = new ModelAndView();
//        User user = new User();
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        User userExists = userService.findUserByUsername(user.getUsername());
//        if (userExists != null) {
//            bindingResult
//                    .rejectValue("username", "error.user",
//                            "There is already a user registered with the username provided");
//        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("registration");
//        } else {
//            userService.saveUser(user);
//            modelAndView.addObject("successMessage", "User has been registered successfully");
//            modelAndView.addObject("user", new User());
//            modelAndView.setViewName("registration");
//
//        }
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
//    public ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUsername(auth.getName());
//        modelAndView.addObject("userName", "Welcome " + user.getUsername());
//        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }
}
