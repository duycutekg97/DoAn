/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.controllers;

import com.qlphongtro.pojo.GPT;
import com.qlphongtro.pojo.Motel;
import com.qlphongtro.service.GPTService;
import com.qlphongtro.service.Impl.StaticSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HOME
 */
@Controller
public class GPTController {

    @Autowired
    private GPTService gptService;

    private List<GPT> listGPT = new ArrayList<>();

    @GetMapping("/gpt/")
    public String gptindex(Model model) {
        model.addAttribute("gptModel", new GPT());
        model.addAttribute("gptRespone", listGPT);
        return "gpt";
    }

    @PostMapping("/gpt/")
    public String add(@ModelAttribute(value = "gptModel") @Valid GPT gpt, Model model, Principal u,
            BindingResult rs) {
        String respone = this.gptService.GPT(gpt.getQuestion());
        respone = respone.replace("\\n", "</br>");
        listGPT.add(new GPT(gpt.getQuestion(), respone));
        Collections.reverse(listGPT);
        model.addAttribute("gptRespone", listGPT);
        return "gpt";
    }
}
