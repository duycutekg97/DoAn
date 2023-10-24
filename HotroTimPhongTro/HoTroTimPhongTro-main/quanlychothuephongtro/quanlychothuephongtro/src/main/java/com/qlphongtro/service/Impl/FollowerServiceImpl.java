/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.service.Impl;

import com.qlphongtro.pojo.Follower;
import com.qlphongtro.pojo.User;
import com.qlphongtro.repository.FollowerRepository;
import com.qlphongtro.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HOME
 */
@Service
public class FollowerServiceImpl implements FollowerService{
    @Autowired
    private FollowerRepository followerRepository;
    @Override
    public boolean following(User userRenter, User userHost) {
        return this.followerRepository.following(userRenter, userHost);
    }

    @Override
    public boolean Unfollow(User userRenter, User userHost) {
        return this.followerRepository.Unfollow(userRenter, userHost);
    }

    @Override
    public Follower getFollowerByRenterAndHost(User userRenter, User userHost) {
        return this.followerRepository.getFollowerByRenterAndHost(userRenter, userHost);
    }

    @Override
    public int checkFollow(User userRenter, User userHost) {
        return this.followerRepository.checkFollow(userRenter, userHost);
    }

    @Override
    public int countFollowerByHost(User userHost) {
        return this.followerRepository.countFollowerByHost(userHost);
    }
    
}
