/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.pojo;

import lombok.Data;

/**
 *
 * @author HOME
 */
@Data
public class GPT {
    private String question;
    private String respone;
    public GPT(){
        
    }
    public GPT(String question,String respone){
        this.question = question;   
        this.respone = respone;
    }
}
