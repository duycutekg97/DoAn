/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.repository;

import com.qlphongtro.pojo.Findmotel;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HOME
 */
public interface FindMotelRepository {

    List<Findmotel> getFindmotel();

    List<Findmotel> getFindmotel(Map<String, String> params);

    Long countFindmotel();

    Findmotel getFindmotelById(int id);

    boolean addOrUpdateFindmotel(Findmotel findmotel, Principal u);

    boolean addOrUpdateFindmotel(Findmotel findmotel, Findmotel findmotelId, Findmotel findmotelIdReply, Principal u);

}
