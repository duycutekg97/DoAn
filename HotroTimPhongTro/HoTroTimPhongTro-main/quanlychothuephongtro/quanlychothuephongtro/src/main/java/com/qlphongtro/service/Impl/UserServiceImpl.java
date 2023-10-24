/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlphongtro.pojo.User;
import com.qlphongtro.repository.UserRepository;
import com.qlphongtro.service.RoleUserService;
import com.qlphongtro.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author HOME
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
//    @Autowired
//    private LoaiNguoiDungRepository loaiRepo;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleUserService roleService;
    @Override
    public List<User> getUser() {
        return this.userRepo.getUser();
    }

    @Override
    public List<User> getListUser(Map<String, String> params) {
        return this.userRepo.getListUser(params);
    }

    @Override
    public Long countUser() {
        return this.userRepo.countUser();
    }

    @Override
    public boolean deleteUserById(int id) {
        return this.userRepo.deleteUserById(id);
    }

    @Override
    public User getUserById(int id) {
        return this.userRepo.getUserById(id);
    }

    @Override
    public boolean addOrUpdateUser(User user) {
        
        return this.userRepo.addOrUpdateUser(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public boolean checkUserName(String username) {
        return this.userRepo.checkUserName(username);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }

   @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        User u = new User();
        u.setFirstname(params.get("firstName"));
        u.setLastname(params.get("lastName"));
        u.setEmail(params.get("email"));
        u.setUsername(params.get("username"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));
        u.setFkuserroleuserId((this.roleService.getRoleUserById(3)));
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(), 
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.userRepo.addUser(u);
        return u;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getFkuserroleuserId().getName()));
         org.springframework.security.core.userdetails.User p  = new org.springframework.security.core.userdetails.User( u.getUsername(), u.getPassword(), authorities);
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
        
    }

    @Override
    public List<User> getFollowerByHost(User userHost) {
        return this.userRepo.getFollowerByHost(userHost);
    }
    
    
}
