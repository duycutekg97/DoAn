/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.service;

import com.qlphongtro.pojo.Evaluate;
import com.qlphongtro.pojo.Motel;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HOME
 */
public interface EvaluateService {

    List<Evaluate> getEvaluate();

    List<Evaluate> getEvaluate(Motel motel);

    Evaluate getEvaluateById(int id);

    boolean addOrUpdateEvaluate(Evaluate evaluate, Motel motel, Principal u);

    boolean addOrUpdateEvaluate(Evaluate evaluate, Motel motel, Evaluate evaluateId, Evaluate evaluateIdReply, Principal u);

    Long countEvaluate();

    Long countEvaluate(Motel motel);

    void deleteEvaluateByMotelId(Motel id);
    
    //Thêm đánh giá
    Evaluate addEvaluate(Evaluate e,Motel m);
    //Phản hồi đánh giá 
    //<evaluateId : là đánh giá đầu tiên>
    //<evaluateIdReply : là phẩn hồi đánh giá của Id>
    Evaluate addEvaluate(Evaluate e,Motel m,Evaluate evaluateId,Evaluate evaluateIdReply);

}
