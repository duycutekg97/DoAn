/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.repository.Impl;

import com.qlphongtro.pojo.Follower;
import com.qlphongtro.pojo.User;
import com.qlphongtro.repository.FollowerRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HOME
 */
@Repository
@Transactional
public class FollowerRepositoryImpl implements FollowerRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean following(User userRenter, User userHost) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Follower follower = new Follower();
            follower.setFkfolloweruserRenterId(userRenter);
            follower.setFkfolloweruserHostId(userHost);
            s.save(follower);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Unfollow(User userRenter, User userHost) {
        Session s = this.factory.getObject().getCurrentSession();
        Follower follower = this.getFollowerByRenterAndHost(userRenter, userHost);
        try {
            s.delete(follower);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Follower getFollowerByRenterAndHost(User userRenter, User userHost) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Follower> q = b.createQuery(Follower.class);
        Root root = q.from(Follower.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("fkfolloweruserRenterId"), userRenter));
        predicates.add(b.equal(root.get("fkfolloweruserHostId"), userHost));
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        return (Follower) query.getSingleResult();

    }

    @Override
    public int checkFollow(User userRenter, User userHost) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Follower WHERE fkfolloweruserRenterId = :renterId AND fkfolloweruserHostId = :hostId");
        q.setParameter("renterId", userRenter);
        q.setParameter("hostId", userHost);

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public int countFollowerByHost(User userHost) {
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


        return query.getResultList().size();
    }

}
