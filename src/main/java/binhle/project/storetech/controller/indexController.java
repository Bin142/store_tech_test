package binhle.project.storetech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/", "index"})
public class indexController {

    @GetMapping
    public String showIndex(){
        return "index";
    }

    @GetMapping("/sign-in")
    public String showSignIn(){
        return "sign-in";
    }

}
