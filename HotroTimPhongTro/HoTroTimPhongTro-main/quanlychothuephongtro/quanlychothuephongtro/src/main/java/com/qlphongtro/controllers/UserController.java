/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.controllers;

import com.qlphongtro.pojo.User;
import com.qlphongtro.service.Impl.StaticSession;
import com.qlphongtro.service.RoleUserService;
import com.qlphongtro.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author HOME
 */
@Controller
@RequestMapping("/admin")
@PropertySource("classpath:configs.properties")
public class UserController {
     @Autowired
    private UserService userService;
     @Autowired
    private RoleUserService roleService;
    @Autowired
    private Environment env;
//     @GetMapping("/users")
//    public String list(Model model) {
//        model.addAttribute("user", new User());
//        return "register";
//    }
    

    @GetMapping("/users/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("userRole", this.roleService.getLoaiNguoiDung());
        model.addAttribute("user", this.userService.getUserById(id));
        return "register";
    }

    @PostMapping("/users")
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
    
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.userService.deleteUserById(id);
    }
}
