/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.service.Impl;

import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.User;
import com.qlphongtro.repository.MotelRepository;
import com.qlphongtro.service.MotelService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HOME
 */
@Service
public class MotelServiceImpl implements MotelService {

    @Autowired
    private MotelRepository motelRepo;

    @Override
    public List<Motel> getListMotel(Map<String, String> params, User u) {
        return this.motelRepo.getListMotel(params, u);
    }

    @Override
    public Long countMotel(User u) {
        return this.motelRepo.countMotel(u);
    }

    @Override
    public boolean deleteMotelById(int id) {
        return this.motelRepo.deleteMotelById(id);

    }

    @Override
    public Motel getMotelById(int id) {
        return this.motelRepo.getMotelById(id);
    }

    @Override
    public boolean addOrUpdateMotel(Motel motel, Principal u) {
        return this.motelRepo.addOrUpdateMotel(motel, u);
    }

    @Override
    public List<Motel> getListMotel(Map<String, String> params) {
        return this.motelRepo.getListMotel(params);

    }

    @Override
    public Long countMotel() {
        return this.motelRepo.countMotel();
    }

    @Override
    public List<Motel> getListMotelApplyFalse() {
        return this.motelRepo.getListMotelApplyFalse();

    }

    @Override
    public boolean applyMotelById(int id) {
        return this.motelRepo.applyMotelById(id);
    }
}
