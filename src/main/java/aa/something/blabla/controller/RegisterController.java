package aa.something.blabla.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import aa.something.blabla.entity.User;
import aa.something.blabla.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
    
    @Autowired
    private UserService userService;
    
    @ModelAttribute("user")
    public User constructUser() {
        return new User();
    }
    
    @RequestMapping
    public String showRegister() {
        return "user-register";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user-register";
        }
        userService.save(user);
        return "redirect:/register.html?success=true";
    }
    
    @RequestMapping("/available")
    @ResponseBody
    public String available(@RequestParam String userName) {
        Boolean available = userService.findOne(userName) == null;
        return available.toString();
    }
}
