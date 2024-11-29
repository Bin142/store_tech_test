package binhle.project.storetech.controller;


import binhle.project.storetech.DTO.request.UserCreationRequest;
import binhle.project.storetech.entity.impo.User;
import binhle.project.storetech.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping({"","/"})
    public String getAllUser(Model model){
        userService.findAll();
        model.addAttribute("users", userService.findAll());
        return "users/UsersIndex";
    }
    @GetMapping("/createUser")
    public String showcreateUser(Model model){
        UserCreationRequest request = new UserCreationRequest();
        model.addAttribute("request", request);
        return "sign-up";
    }
    @PostMapping("/createUser")
    public String CreateUser(@Valid @ModelAttribute UserCreationRequest request
            , BindingResult result){
        if(result.hasErrors()){
            return "sign-up";
        }
//        if(request.getImageFile().isEmpty()){
//            result.addError(new FieldError("request", "imageFile", "Image file is required"));
//        }
        userService.createUser(request);
        return "redirect:/index/sign-in";
    }

    @GetMapping("/edituser")
    public String showEditUser(Model model, @RequestParam int id){
        try{
            User user = userService.findById(id);
            model.addAttribute("user", user);
            userService.showEditUser(id, model);
        }catch(Exception e){
            System.out.println("Exception: "+e);
            return "redirect:/users";
        }
        return "users/editUser";
    }
    @PostMapping("/edituser")
    public String EditUser(@Valid @ModelAttribute UserCreationRequest request
            , BindingResult result
            , @RequestParam int id
            , Model model){
        try {
            User user = userService.findById(id);
            model.addAttribute("user", user);
            if (result.hasErrors()) {
                return "users/editUser";
            }
        }catch(Exception e){
            System.out.println("Exception: "+e);
        }
        return "redirect:/users";
    }

    @GetMapping("/deleteuser")
    public String DeleteUser(@RequestParam int id){
        try{
            userService.deleteUser(id);
        }catch(Exception e){
            System.out.println("Exception: "+e);
        }
        return "redirect:/users";
    }
}
