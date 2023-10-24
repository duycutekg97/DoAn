/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.repository;

import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.Motelimage;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HOME
 */
public interface MotelImageRepository {
    List<Motelimage> getImage();
    List<Motelimage> getListMotelimage(Map<String, String> params);
    List<Motelimage> getImageByMotelId(int id);

    Long countMotelimage();

    boolean deleteMotelimageById(int id);
    void deleteMotelimageByMotelId(Motel id);

    Motelimage getMotelimageById(int id);

    boolean addOrUpdateMotelimage(Motelimage motel);
}
