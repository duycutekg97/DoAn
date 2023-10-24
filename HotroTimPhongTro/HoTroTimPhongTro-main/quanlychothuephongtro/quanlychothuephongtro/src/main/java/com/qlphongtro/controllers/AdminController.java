/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.controllers;

import com.qlphongtro.service.Impl.StaticSession;
import com.qlphongtro.service.MotelImageService;
import com.qlphongtro.service.MotelService;
import com.qlphongtro.service.RoleUserService;
import com.qlphongtro.service.StatsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author HOME
 */
@Controller
@RequestMapping("/admin")
@PropertySource("classpath:configs.properties")
public class AdminController {

    @Autowired
    private MotelService motelService;
    @Autowired
    private MotelImageService motelImageService;
    @Autowired
    private StatsService statsService;
    @Autowired
    private RoleUserService roleService;

    @GetMapping("/motels/apply/false/")
    public String get(Model model) {
        model.addAttribute("motelFalse", this.motelService.getListMotelApplyFalse());
        model.addAttribute("listMotelImages", this.motelImageService.getImage());

        return "indexAwaitingReview";
    }

    @PostMapping("/active/{id}/motel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void applyTrue(@PathVariable(value = "id") int id) {
        this.motelService.applyMotelById(id);
    }

    @GetMapping("/chart/")
    public String getChart(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("roleList", this.roleService.getLoaiNguoiDung());

        //Year
        model.addAttribute("numberUser", this.statsService.getNumberUser(params, 0));

        //Moth
        model.addAttribute("January", this.statsService.getNumberUserMonthOfYear(params, 1));
        model.addAttribute("February", this.statsService.getNumberUserMonthOfYear(params, 2));
        model.addAttribute("March", this.statsService.getNumberUserMonthOfYear(params, 3));
        model.addAttribute("April", this.statsService.getNumberUserMonthOfYear(params, 4));
        model.addAttribute("May", this.statsService.getNumberUserMonthOfYear(params, 5));
        model.addAttribute("June", this.statsService.getNumberUserMonthOfYear(params, 6));
        model.addAttribute("July", this.statsService.getNumberUserMonthOfYear(params, 7));
        model.addAttribute("August", this.statsService.getNumberUserMonthOfYear(params, 8));
        model.addAttribute("September", this.statsService.getNumberUserMonthOfYear(params, 9));
        model.addAttribute("October", this.statsService.getNumberUserMonthOfYear(params, 10));
        model.addAttribute("November", this.statsService.getNumberUserMonthOfYear(params, 11));
        model.addAttribute("December", this.statsService.getNumberUserMonthOfYear(params, 12));

        //Quarter
        model.addAttribute("Q1", this.statsService.getNumberUser(params, 1));
        model.addAttribute("Q2", this.statsService.getNumberUser(params, 2));
        model.addAttribute("Q3", this.statsService.getNumberUser(params, 3));
        model.addAttribute("Q4", this.statsService.getNumberUser(params, 4));

        return "chart";
    }
}
