/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.repository.Impl;

import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.Motelimage;
import com.qlphongtro.repository.MotelImageRepository;
import java.util.ArrayList;
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
public class MotelImageRepositoryImpl implements MotelImageRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Motelimage> getListMotelimage(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Motelimage> q = b.createQuery(Motelimage.class);
        Root root = q.from(Motelimage.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String address = params.get("Address");
            if (address != null && !address.isEmpty()) {
                predicates.add(b.equal(root.get("address"), address));
            }
            String cityId = params.get("City");
            if (cityId != null && !cityId.isEmpty()) {
                predicates.add(b.equal(root.get("fkmotelcityId"), Integer.parseInt(cityId)));
            }
            String districtId = params.get("District");
            if (districtId != null && !districtId.isEmpty()) {
                predicates.add(b.equal(root.get("fkmoteldistrictId"), Integer.parseInt(districtId)));
            }
            String wardId = params.get("Ward");
            if (wardId != null && !wardId.isEmpty()) {
                predicates.add(b.equal(root.get("fkmotelwardId"), Integer.parseInt(wardId)));
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
    public Long countMotelimage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteMotelimageById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Motelimage image = this.getMotelimageById(id);
        try {
            s.delete(image);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Motelimage getMotelimageById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Motelimage.class, id);
    }

    @Override
    public boolean addOrUpdateMotelimage(Motelimage motel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Motelimage> getImage() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Motelimage ");
        return q.getResultList();
    }

    @Override
    public List<Motelimage> getImageByMotelId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Motelimage> q = b.createQuery(Motelimage.class);
        Root rImage = q.from(Motelimage.class);
        Root rMotel = q.from(Motel.class);

        q.select(rImage);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rMotel.get("id"), id));
        predicates.add(b.equal(rImage.get("fkmotelimagemotelId"), rMotel.get("id")));
        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void deleteMotelimageByMotelId(Motel id) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("DELETE FROM Motelimage WHERE fkmotelimagemotelId = :id");
        query.setParameter("id", id);
        int rowsDeleted = query.executeUpdate();
        System.out.println("Rows deleted: " + rowsDeleted);
    }
}
