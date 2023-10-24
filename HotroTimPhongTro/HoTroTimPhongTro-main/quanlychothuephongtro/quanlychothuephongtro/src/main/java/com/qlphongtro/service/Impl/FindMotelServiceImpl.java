/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.service.Impl;

import com.qlphongtro.pojo.Findmotel;
import com.qlphongtro.repository.FindMotelRepository;
import com.qlphongtro.service.FindMotelService;
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
public class FindMotelServiceImpl implements FindMotelService{
    @Autowired
    private FindMotelRepository findMotelRepo; 
    @Override
    public List<Findmotel> getFindmotel(Map<String, String> params) {
        return this.findMotelRepo.getFindmotel(params);
    }

    @Override
    public Long countFindmotel() {
        return this.findMotelRepo.countFindmotel();
    }

    @Override
    public Findmotel getFindmotelById(int id) {
        return this.findMotelRepo.getFindmotelById(id);
    }

    @Override
    public boolean addOrUpdateFindmotel(Findmotel findmotel, Principal u) {
        return this.findMotelRepo.addOrUpdateFindmotel(findmotel, u);
    }

    @Override
    public boolean addOrUpdateFindmotel(Findmotel findmotel, Findmotel findmotelId, Findmotel findmotelIdReply, Principal u) {
        return this.findMotelRepo.addOrUpdateFindmotel(findmotel, findmotelId, findmotelIdReply, u);
    }

    @Override
    public List<Findmotel> getFindmotel() {
        return this.findMotelRepo.getFindmotel();
    }
    
}
