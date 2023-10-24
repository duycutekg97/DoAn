/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.service.Impl;

import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.Motelimage;
import com.qlphongtro.repository.MotelImageRepository;
import com.qlphongtro.service.MotelImageService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HOME
 */
@Service
public class MotelImageServiceImpl implements MotelImageService{
    @Autowired
    private MotelImageRepository motelImageRepo;

    @Override
    public List<Motelimage> getListMotelimage(Map<String, String> params) {
        return this.motelImageRepo.getListMotelimage(params);
    }

    @Override
    public Long countMotelimage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteMotelimageById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Motelimage getMotelimageById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addOrUpdateMotelimage(Motelimage motel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Motelimage> getImage() {
       return this.motelImageRepo.getImage();
    }

    @Override
    public List<Motelimage> getImageByMotelId(int id) {
       return this.motelImageRepo.getImageByMotelId(id);
    }

    @Override
    public void deleteMotelimageByMotelId(Motel id) {
        this.motelImageRepo.deleteMotelimageByMotelId(id);
    }
    
}
