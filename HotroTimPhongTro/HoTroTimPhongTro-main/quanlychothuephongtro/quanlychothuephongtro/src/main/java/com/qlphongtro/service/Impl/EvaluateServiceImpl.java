/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.service.Impl;

import com.qlphongtro.pojo.Evaluate;
import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.User;
import com.qlphongtro.repository.EvaluateRepository;
import com.qlphongtro.service.EvaluateService;
import com.qlphongtro.service.UserService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author HOME
 */
@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateRepository evalRepo;
    @Autowired
    private SimpleDateFormat f;
    @Autowired
    private UserService userService;

    @Override
    public List<Evaluate> getEvaluate() {
        return this.evalRepo.getEvaluate();
    }

    @Override
    public Evaluate getEvaluateById(int id) {
        return this.evalRepo.getEvaluateById(id);
    }

    @Override
    public boolean addOrUpdateEvaluate(Evaluate evaluate, Motel motel, Principal u) {
        return this.evalRepo.addOrUpdateEvaluate(evaluate, motel, u);
    }

    @Override
    public Long countEvaluate() {
        return this.evalRepo.countEvaluate();
    }

    @Override
    public List<Evaluate> getEvaluate(Motel motel) {
        return this.evalRepo.getEvaluate(motel);
    }

    @Override
    public Long countEvaluate(Motel motel) {
        return this.evalRepo.countEvaluate(motel);
    }

    @Override
    public boolean addOrUpdateEvaluate(Evaluate evaluate, Motel motel, Evaluate evaluateId, Evaluate evaluateIdReply, Principal u) {
        return this.evalRepo.addOrUpdateEvaluate(evaluate, motel, evaluateId, evaluateIdReply, u);
    }

    @Override
    public void deleteEvaluateByMotelId(Motel id) {
        this.evalRepo.deleteEvaluateByMotelId(id);
    }

    @Override
    public Evaluate addEvaluate(Evaluate e, Motel m) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getUserByUsername(authentication.getName());
        e.setFkevaluteuserIdRespondent(user);
        e.setFkevalutemotelId(m);
        e.setCreatedDate(f.format(new Date()));
        return this.evalRepo.addEvaluate(e);
    }

    @Override
    public Evaluate addEvaluate(Evaluate e, Motel m, Evaluate evaluateId, Evaluate evaluateIdReply) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getUserByUsername(authentication.getName());
        e.setFkevaluteuserIdRespondent(user);
        e.setFkevalutemotelId(m);
        e.setFkevaluteevaluateId(evaluateId);
        e.setFkevaluteevaluateIdReply(evaluateIdReply);
        e.setCreatedDate(f.format(new Date()));
        return this.evalRepo.addEvaluate(e);
    }

}
