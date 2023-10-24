/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.repository.Impl;

import com.qlphongtro.pojo.User;
import com.qlphongtro.repository.StatsRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private SimpleDateFormat f;

    @Override
    public int getNumberUser(Map<String, String> params, int quarter) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root root = q.from(User.class);
        q.select(b.count(root));
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String year = params.get("Year");

            if (year != null && !year.isEmpty()) {
                predicates.add(b.equal(b.function("YEAR", Integer.class, root.get("createdDate")), Integer.parseInt(year)));
            }
            if (quarter != 0) {
                predicates.add(b.equal(b.function("QUARTER", Integer.class, root.get("createdDate")), quarter));
            }
            String roleId = params.get("roleUser");
            if (roleId != null && !roleId.isEmpty()) {
                predicates.add(b.equal(root.get("fkuserroleuserId"), Integer.parseInt(roleId)));
            }
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(root.get("id")));
        Query query = s.createQuery(q);

        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public int getNumberUserMonthOfYear(Map<String, String> params, int month) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root root = q.from(User.class);
        q.select(b.count(root));
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String year = params.get("Year");

            if (year != null && !year.isEmpty()) {
                predicates.add(b.equal(b.function("YEAR", Integer.class, root.get("createdDate")), Integer.parseInt(year)));

            }
            String roleId = params.get("roleUser");
            if (roleId != null && !roleId.isEmpty()) {
                predicates.add(b.equal(root.get("fkuserroleuserId"), Integer.parseInt(roleId)));
            }
        }
        predicates.add(b.equal(b.function("MONTH", Integer.class, root.get("createdDate")), month));
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(root.get("id")));
        Query query = s.createQuery(q);

        return Integer.parseInt(query.getSingleResult().toString());
    }

}
