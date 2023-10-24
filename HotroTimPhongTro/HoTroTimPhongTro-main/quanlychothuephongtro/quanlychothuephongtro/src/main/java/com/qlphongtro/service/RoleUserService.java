/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.service;

import com.qlphongtro.pojo.Roleuser;
import java.util.List;

/**
 *
 * @author HOME
 */
public interface RoleUserService {

    List<Roleuser> getLoaiNguoiDung();

    Roleuser getRoleUserById(int id);
}
