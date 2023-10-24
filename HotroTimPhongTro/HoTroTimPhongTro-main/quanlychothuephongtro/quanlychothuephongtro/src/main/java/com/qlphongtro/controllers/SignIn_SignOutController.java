/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.controllers;

import com.qlphongtro.pojo.User;
import com.qlphongtro.service.Impl.StaticSession;
import com.qlphongtro.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author HOME
 */
@Controller
public class SignIn_SignOutController {
    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;
    @GetMapping("/login/")
    public String login() {
        return "login";
    }

    @GetMapping("/perform-logout")
    public String logout(final Model model) {
        System.out.println("Loggingout");
        return "login";
    }
    
     
     @GetMapping("/register")
    public String list(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

//    @GetMapping("/register/{id}")
//    public String update(Model model, @PathVariable(value = "id") int id) {
//        model.addAttribute("user", this.userService.getUserById(id));
//        return "register";
//    }

    @PostMapping("/register")
    public String add(@ModelAttribute(value = "user") @Valid User user,Model model,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (userService.addOrUpdateUser(user) == true) {
                return "redirect:/";
            } else {
                model.addAttribute("msgErrShow",StaticSession.msgError);
                return "register";
            }
        }
        return "register";
    }
}
