/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.repository;

import com.qlphongtro.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HOME
 */
public interface UserRepository {
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

    User addUser(User user);
}
