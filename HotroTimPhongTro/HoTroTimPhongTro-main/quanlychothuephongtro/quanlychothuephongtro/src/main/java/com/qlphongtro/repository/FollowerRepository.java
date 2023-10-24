/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.repository;

import com.qlphongtro.pojo.Follower;
import com.qlphongtro.pojo.User;

/**
 *
 * @author HOME
 */
public interface FollowerRepository {

    boolean following(User userRenter, User userHost);
    int countFollowerByHost(User userHost);
    boolean Unfollow(User userRenter, User userHost);
    int checkFollow(User userRenter, User userHost);
    Follower getFollowerByRenterAndHost(User userRenter, User userHost);

}
