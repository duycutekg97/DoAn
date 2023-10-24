/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.controllers;

import com.qlphongtro.pojo.Motel;
import com.qlphongtro.service.EvaluateService;

import com.qlphongtro.service.Impl.StaticSession;
import com.qlphongtro.service.MotelImageService;
import com.qlphongtro.service.MotelService;
import com.qlphongtro.service.UserService;

import java.security.Principal;
import java.util.Objects;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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
@RequestMapping("/host")
@PropertySource("classpath:configs.properties")
public class MotelController {

    @Autowired
    private MotelService motelService;
    @Autowired
    private UserService userService;
    @Autowired
    private MotelImageService motelimageService;
    @Autowired
    private EvaluateService evaluateService;

    @GetMapping("/motels")
    public String list(Model model) {
        model.addAttribute("motelAdd", new Motel());

        return "addMotel";
    }

    @GetMapping("/motels/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, Principal u) {
        if (this.userService.getUserByUsername(u.getName()).getFkuserroleuserId().getName().equals("ROLE_ADMIN")
                || this.motelService.getMotelById(id).getFkmoteluserId().getId() == this.userService.getUserByUsername(u.getName()).getId()) {
            model.addAttribute("motelAdd", this.motelService.getMotelById(id));

            return "addMotel";
        }
        return "errPage";
    }

    @PostMapping("/motels")
    public String add(@ModelAttribute(value = "motelAdd") @Valid Motel motel, Model model, Principal u,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (motelService.addOrUpdateMotel(motel, u) == true) {
                return "redirect:/index/Motel/";
            } else {
                model.addAttribute("msgErrShow", StaticSession.msgError);
                return "addMotel";
            }
        }
        return "addMotel";
    }

    @DeleteMapping("/motels/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id, Principal u) {

        if (this.userService.getUserByUsername(u.getName()).getFkuserroleuserId().getName().equals("ROLE_ADMIN")
                || this.motelService.getMotelById(id).getFkmoteluserId().getId() == this.userService.getUserByUsername(u.getName()).getId()) {
            try {
                Motel m = this.motelService.getMotelById(id);
                this.evaluateService.deleteEvaluateByMotelId(m);
                this.motelimageService.deleteMotelimageByMotelId(m);
                this.motelService.deleteMotelById(id);

            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }

    }
}
