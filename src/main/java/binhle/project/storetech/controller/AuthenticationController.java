package binhle.project.storetech.controller;

import binhle.project.storetech.DTO.request.AuthenticationRequest;
import binhle.project.storetech.entity.impo.Product;
import binhle.project.storetech.entity.impo.User;
import binhle.project.storetech.services.AuthenticationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
@SessionAttributes("username")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @ModelAttribute("username")
    public User username(){
        return new User();
    }


    @PostMapping("/checklogin")
    public String login(
            @RequestParam("username") String username
            , @Valid @ModelAttribute AuthenticationRequest request_authen
            , HttpServletResponse response
            , Model model
            , HttpServletRequest request_httpServlet
            , BindingResult result
            , RuntimeException ex) {

        if(result.hasErrors()) {
            model.addAttribute("error", "User or password is incorrect");
        }
        if(authenticationService.authenticated(request_authen)){
            model.addAttribute("error", ex.getMessage());
            //set cookie to response
            Cookie cookie = new Cookie("username", username);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            if(request_authen.isRememeber()){
                cookie.setMaxAge(100);
                request_httpServlet.getSession().setAttribute("username", username);
                response.addCookie(cookie);
                //get cookie to request
                Cookie[] cookies = request_httpServlet.getCookies();
                for (Cookie c : cookies) {
                    if (c.getName().equals("username")) {
                        model.addAttribute("cookieValue", c);
                        break;
                    } else {
                        c.setValue("");
                        model.addAttribute("cookieValue", c);
                        break;
                    }
                }}
            model.addAttribute("status", "login success!!!");
            model.addAttribute("cookieValue", cookie);
            return "redirect:/home";

        }else{
            model.addAttribute("error", "login.failed");
            Cookie cookie = new Cookie("username", username);
            return "redirect:/auth/checklogin";
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //gán session là null
        request.getSession().setAttribute("username", null);
        request.getRequestDispatcher("login").forward(request, response);
    }

    @GetMapping("/register")
    public String register(){
        return "registeruser";
    }
}
