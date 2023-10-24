/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.repository.Impl;

import com.qlphongtro.pojo.Evaluate;
import com.qlphongtro.pojo.Motel;
import com.qlphongtro.repository.EvaluateRepository;
import com.qlphongtro.service.Impl.StaticSession;
import com.qlphongtro.service.UserService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HOME
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class EvaluateRepositoryImpl implements EvaluateRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private UserService userService;
    @Autowired
    private SimpleDateFormat f;

    @Override
    public List<Evaluate> getEvaluate() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Evaluate");
        return q.getResultList();
    }

    @Override
    public Evaluate getEvaluateById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Evaluate.class, id);
    }

    @Override
    public boolean addOrUpdateEvaluate(Evaluate evaluate, Motel motel, Principal u) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (evaluate.getId() == null) {
                if (evaluate.getComment() == null || evaluate.getComment().isEmpty() || motel.getName().trim() == "") {
                    StaticSession.msgError = "Comment must not be empty";
                    return false;
                }
                evaluate.setFkevalutemotelId(motel);
                evaluate.setFkevaluteuserIdRespondent(this.userService.getUserByUsername(u.getName()));
                evaluate.setCreatedDate(f.format(new Date()));
                s.save(evaluate);
            } else {

                s.update(evaluate);
            }
            StaticSession.msgError = "";
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Long countEvaluate() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Evaluate> q = b.createQuery(Evaluate.class
        );
        Root root = q.from(Evaluate.class
        );
        q.select(root);
        Query query = s.createQuery(q);
        return Long.valueOf(query.getResultList().size());
    }

    @Override
    public List<Evaluate> getEvaluate(Motel motel) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Evaluate WHERE fkevalutemotelId = :id ORDER BY id DESC");
        q.setParameter("id", motel);

        return q.getResultList();
    }

    @Override
    public Long countEvaluate(Motel motel) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Evaluate> q = b.createQuery(Evaluate.class);
        Root root = q.from(Evaluate.class);
        q.select(root);
        q.where(b.equal(root.get("fkevalutemotelId"), motel.getId()));
        Query query = s.createQuery(q);
        return Long.valueOf(query.getResultList().size());
    }

    @Override
    public boolean addOrUpdateEvaluate(Evaluate evaluate, Motel motel, Evaluate evaluateId, Evaluate evaluateIdReply, Principal u) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (evaluate.getId() == null) {
                if (evaluate.getComment() == null || evaluate.getComment().isEmpty() || motel.getName().trim() == "") {
                    StaticSession.msgError = "Comment must not be empty";
                    return false;
                }
                evaluate.setFkevalutemotelId(motel);
                evaluate.setFkevaluteuserIdRespondent(this.userService.getUserByUsername(u.getName()));
                evaluate.setCreatedDate(f.format(new Date()));
                evaluate.setFkevaluteevaluateId(evaluateId);
                evaluate.setFkevaluteevaluateIdReply(evaluateIdReply);
                s.save(evaluate);
            } else {

                s.update(evaluate);
            }
            StaticSession.msgError = "";
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteEvaluateByMotelId(Motel id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Evaluate> q = b.createQuery(Evaluate.class);
        Root rEvaluate = q.from(Evaluate.class);
        q.select(rEvaluate);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rEvaluate.get("fkevalutemotelId"), id));
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(rEvaluate.get("id")));
        Query query = s.createQuery(q);

        List<Evaluate> listE = query.getResultList();
        for (Evaluate e : listE) {
            s.delete(e);
        }

    }

    @Override
    public Evaluate addEvaluate(Evaluate e) {
         Session s = this.factory.getObject().getCurrentSession();
        s.save(e);
        
        return e;
    }

}
