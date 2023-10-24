/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.repository;

import com.qlphongtro.pojo.Evaluate;
import com.qlphongtro.pojo.Motel;
import java.security.Principal;
import java.util.List;

/**
 *
 * @author HOME
 */
public interface EvaluateRepository {

    List<Evaluate> getEvaluate();

    List<Evaluate> getEvaluate(Motel motel);

    Evaluate getEvaluateById(int id);

    boolean addOrUpdateEvaluate(Evaluate evaluate, Motel motel, Principal u);

    boolean addOrUpdateEvaluate(Evaluate evaluate, Motel motel, Evaluate evaluateId, Evaluate evaluateIdReply, Principal u);

    Long countEvaluate();

    Long countEvaluate(Motel motel);

    void deleteEvaluateByMotelId(Motel id);
    
    Evaluate addEvaluate(Evaluate e);

}
