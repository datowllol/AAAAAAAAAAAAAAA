package bd.BookingPub.controller;


import bd.BookingPub.model.Admin;
import bd.BookingPub.services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("users")
public class UserServiceController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String all(Model model) {
        model.addAttribute("isGODADMIN", true);
        model.addAttribute("isSIMPADMIN", true);
        List<Admin> admins = userService.getAll();
        model.addAttribute("admins", admins);
        return "users";
    }

    @GetMapping("/add/admin")
    public String addPage(Model model) {
        model.addAttribute("isGODADMIN", true);
        return "add-users";
    }

    @PostMapping("/add/admin")
    public String register(@RequestParam(value = "login") String username,
                           @RequestParam(value = "password") String password,
                           Model model) {
        if(userService.isUnique(username)) {
            Admin admin = new Admin();
            admin.setLogin(username);
            admin.setPassword(password);
            userService.saveUser(admin);
            return "redirect:/users";
        }
        model.addAttribute("isAuthenticated", false);
        model.addAttribute("isAdmin", false);
        model.addAttribute("usernameMessage", "Login must be unique");
        return "users";
    }
}