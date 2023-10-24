/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author HOME
 */
public interface EmailService {
    void sendSimpleMessageDiemTB(String fileNameTMP,ArrayList<String> emailsTMP);
    void sendHtmlMessage(String to, String subject, String htmlBody);
    void sendEmail(InternetAddress[] to, String subject,Map<String, Object> model);
    String geFreeMarkerTemplateContent(Map<String, Object> model);
}
