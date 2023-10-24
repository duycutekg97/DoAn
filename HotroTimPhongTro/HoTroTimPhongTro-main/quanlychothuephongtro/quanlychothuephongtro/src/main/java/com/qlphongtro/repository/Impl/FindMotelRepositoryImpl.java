/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.repository.Impl;

import com.qlphongtro.pojo.Evaluate;
import com.qlphongtro.pojo.Findmotel;
import com.qlphongtro.repository.FindMotelRepository;
import com.qlphongtro.service.Impl.StaticSession;
import com.qlphongtro.service.UserService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
public class FindMotelRepositoryImpl implements FindMotelRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private SimpleDateFormat f;
    @Autowired
    private UserService userService;

    @Override
    public List<Findmotel> getFindmotel(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Findmotel> q = b.createQuery(Findmotel.class);
        Root root = q.from(Findmotel.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {

        }
        predicates.add(b.isNull(root.get("fkfindmotelfindmotelId")));
        predicates.add(b.isNull(root.get("fkfindmotelfindmotelIdReply")));

        q.where(predicates.toArray(Predicate[]::new));
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
    public Long countFindmotel() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Findmotel> q = b.createQuery(Findmotel.class);
        Root root = q.from(Findmotel.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.isNull(root.get("fkfindmotelfindmotelId")));
        predicates.add(b.isNull(root.get("fkfindmotelfindmotelIdReply")));
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        return Long.valueOf(query.getResultList().size());
    }

    @Override
    public Findmotel getFindmotelById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Findmotel.class, id);
    }

    @Override
    public boolean addOrUpdateFindmotel(Findmotel findmotel, Principal u) {
        Session s = this.factory.getObject().getCurrentSession();
        try {

            if (findmotel.getId() == null) {
                if (findmotel.getDetaileddescription() == null || findmotel.getDetaileddescription().isEmpty() || findmotel.getDetaileddescription().trim() == "") {
                    StaticSession.msgError = "Detailed description must not be empty";
                    return false;
                }
                findmotel.setFkfindmoteluserId(this.userService.getUserByUsername(u.getName()));
                findmotel.setCreatedDate(f.format(new Date()));
                s.save(findmotel);
            }
            StaticSession.msgError = "";
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addOrUpdateFindmotel(Findmotel findmotel, Findmotel findmotelId, Findmotel findmotelIdReply, Principal u) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (findmotel.getId() == null) {
                if (findmotel.getDetaileddescription() == null || findmotel.getDetaileddescription().isEmpty() || findmotel.getDetaileddescription().trim() == "") {
                    StaticSession.msgError = "Detaileddescription must not be empty";
                    return false;
                }
                findmotel.setFkfindmoteluserId(this.userService.getUserByUsername(u.getName()));
                findmotel.setCreatedDate(f.format(new Date()));
                findmotel.setFkfindmotelfindmotelId(findmotelId);
                findmotel.setFkfindmotelfindmotelIdReply(findmotelIdReply);
                s.save(findmotel);
            }
            StaticSession.msgError = "";
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Findmotel> getFindmotel() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Findmotel");
        return q.getResultList();
    }
}
