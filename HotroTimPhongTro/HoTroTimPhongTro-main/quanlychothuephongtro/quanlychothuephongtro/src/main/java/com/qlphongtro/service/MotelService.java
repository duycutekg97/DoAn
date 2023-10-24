/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.service;

import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.User;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HOME
 */
public interface MotelService {
    List<Motel> getListMotel(Map<String, String> params);
    List<Motel> getListMotelApplyFalse();

    List<Motel> getListMotel(Map<String, String> params, User u);
    Long countMotel();
    Long countMotel(User u);

    boolean deleteMotelById(int id);

    Motel getMotelById(int id);

    boolean addOrUpdateMotel(Motel motel,Principal u);
    boolean applyMotelById(int id);

}
