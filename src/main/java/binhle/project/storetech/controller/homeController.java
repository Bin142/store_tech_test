package binhle.project.storetech.controller;

import binhle.project.storetech.entity.impo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class homeController {

    @ModelAttribute("username")
    public User username(){
        return new User();
    }
    @GetMapping({"", "/"})
    public String showhome(){
        return "home";
    }

    @GetMapping("/signin")
    public String showSignIn(){
        return "sign-in";
    }

    @GetMapping("/signup")
    public String showSignUp(){
        return "redirect:/users/createUser";
    }

    @GetMapping("/signin_with_admin")
    public String showSignInAdmin(){
        return "redirect:/users/createUser";
    }


}
