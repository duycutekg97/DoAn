/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.repository.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlphongtro.pojo.Follower;
import com.qlphongtro.pojo.User;
import com.qlphongtro.repository.UserRepository;
import com.qlphongtro.service.Impl.StaticSession;
import com.qlphongtro.service.Impl.UserServiceImpl;
import com.qlphongtro.service.RoleUserService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HOME
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private RoleUserService roleService;

    @Autowired
    private BCryptPasswordEncoder passEncoder;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private SimpleDateFormat f;

    @Override
    public List<User> getUser() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User "
                + "ORDER BY fkuserroleuserId , id ASC");
        return q.getResultList();
    }

    @Override
    public List<User> getListUser(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String login = params.get("Username");
            if (login != null && !login.isEmpty()) {
                predicates.add(b.equal(root.get("username"), login));
            }
            String roleId = params.get("roleUser");
            if (roleId != null && !roleId.isEmpty()) {
                predicates.add(b.equal(root.get("fkuserroleuserId"), Integer.parseInt(roleId)));
            }

            q.where(predicates.toArray(Predicate[]::new));

        }

        q.orderBy(b.desc(root.get("id")));
        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                query.setMaxResults(pageSize);
                query.setFirstResult((p - 1) * pageSize);
            }
        }
        return query.getResultList();
    }

    @Override
    public Long countUser() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM User");
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public boolean deleteUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        User p = this.getUserById(id);
        try {
            s.delete(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(User.class, id);
    }

    @Override
    public boolean addOrUpdateUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (user.getId() == null) {
                if (checkUserName(user.getUsername())) {
                    StaticSession.msgError = "Username exist";
                    return false;
                } else {
                    if (!user.getFile().isEmpty()) {
                        try {
                            Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                            user.setImage(res.get("secure_url").toString());
                        } catch (IOException ex) {
                            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        StaticSession.msgError = "Must have Avatar";
                        return false;
                    }
                    user.setCreatedDate(f.format(new Date()));
                    user.setFkuserroleuserId(this.roleService.getRoleUserById(3));
                    user.setPassword(this.passEncoder.encode(user.getPassword()));
                    StaticSession.msgError = "";
                    s.save(user);
                }
            } else {
                if (!user.getFile().isEmpty()) {
                    try {
                        Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                        user.setImage(res.get("secure_url").toString());
                    } catch (IOException ex) {
                        Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                s.update(user);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();

        Query q = s.createQuery("FROM User WHERE username=:un");
        q.setParameter("un", username);
        return (User) q.getSingleResult();
    }

    @Override
    public boolean checkUserName(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM User WHERE username=:un");
        q.setParameter("un", username);

        if (Integer.parseInt(q.getSingleResult().toString()) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public User addUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(u);

        return u;
    }

    @Override
    public List<User> getFollowerByHost(User userHost) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root rFollower = q.from(Follower.class);
        Root rUser = q.from(User.class);
        q.select(rUser);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rFollower.get("fkfolloweruserRenterId"), rUser.get("id")));
        predicates.add(b.equal(rFollower.get("fkfolloweruserHostId"), userHost));
        q.where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);

        return query.getResultList();
    }

}
