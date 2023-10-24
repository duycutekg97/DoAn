/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.service.Impl;

import com.qlphongtro.pojo.User;
import com.qlphongtro.repository.UserRepository;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

/**
 *
 * @author HOME
 */
@Service
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private UserRepository userRepo;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        SecurityContextHolder.clearContext();

        try {
            final User userEntity = userRepo.getUserByUsername(authentication.getName());
            //set status to false
            StaticSession.user = null;
            //redirecting to another controller endpoint
            response.sendRedirect("/quanlyphongtro/perform-logout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
