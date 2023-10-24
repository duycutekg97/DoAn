/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.service.Impl;

import com.qlphongtro.pojo.Roleuser;
import com.qlphongtro.repository.RoleUserRepository;
import com.qlphongtro.service.RoleUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HOME
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleUserRepository roleRepo;

    @Override
    public List<Roleuser> getLoaiNguoiDung() {
        return this.roleRepo.getLoaiNguoiDung();
    }

    @Override
    public Roleuser getRoleUserById(int id) {
        return this.roleRepo.getRoleUserById(id);

    }
}
