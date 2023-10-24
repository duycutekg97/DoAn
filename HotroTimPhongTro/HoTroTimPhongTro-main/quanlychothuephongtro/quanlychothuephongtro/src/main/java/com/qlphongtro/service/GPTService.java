/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.service;

/**
 *
 * @author HOME
 */
public interface GPTService {
    String GPT(String message);
    String extractContentFromResponse(String response);
}
