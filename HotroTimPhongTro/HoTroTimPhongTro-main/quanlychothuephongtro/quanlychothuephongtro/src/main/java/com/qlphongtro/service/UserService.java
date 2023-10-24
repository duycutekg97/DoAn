/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.service;

import com.qlphongtro.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author HOME
 */
public interface UserService extends UserDetailsService{
    List<User> getUser();
    List<User> getFollowerByHost(User userHost);

    List<User> getListUser(Map<String, String> params);

    Long countUser();

    boolean deleteUserById(int id);

    User getUserById(int id);

    boolean addOrUpdateUser(User user);

    User getUserByUsername(String username);

    boolean checkUserName(String username);

    boolean authUser(String username, String password);

    User addUser(Map<String, String> params, MultipartFile avatar);
}
