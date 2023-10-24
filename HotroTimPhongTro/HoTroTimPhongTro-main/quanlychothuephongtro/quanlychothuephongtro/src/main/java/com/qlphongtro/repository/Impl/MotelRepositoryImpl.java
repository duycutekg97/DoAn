/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.repository.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.Motelimage;
import com.qlphongtro.pojo.User;
import com.qlphongtro.repository.MotelRepository;
import com.qlphongtro.service.EmailService;

import com.qlphongtro.service.Impl.StaticSession;
import com.qlphongtro.service.MotelImageService;
import com.qlphongtro.service.UserService;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author HOME
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class MotelRepositoryImpl implements MotelRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private SimpleDateFormat f;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailSerivce;
    @Autowired
    private MotelImageService motelimageService;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Motel> getListMotel(Map<String, String> params, User u) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Motel> q = b.createQuery(Motel.class);
        Root root = q.from(Motel.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {

            String address = params.get("Address");
            if (address != null && !address.isEmpty()) {
                predicates.add(b.like(root.get("address"), String.format("%%%s%%", address)));
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
            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                predicates.add(b.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice)));
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                predicates.add(b.lessThanOrEqualTo(root.get("price"), Double.parseDouble(toPrice)));
            }
            String num = params.get("numberOfResidents");
            if (num != null && !num.isEmpty()) {
                predicates.add(b.equal(root.get("numberofresidents"), Integer.parseInt(num)));
            }
            String city = params.get("City2");
            if (city != null && !city.isEmpty()) {
                predicates.add(b.equal(root.get("cityprovince"), String.format("%%%s%%", city)));
            }
            String district = params.get("District2");
            if (district != null && !district.isEmpty()) {
                predicates.add(b.equal(root.get("district"), String.format("%%%s%%", district)));
            }
        }
        if (u != null && u.getFkuserroleuserId().getId() == 2) {
            predicates.add(b.equal(root.get("fkmoteluserId"), u.getId()));
        }
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
    public Long countMotel(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Motel> q = b.createQuery(Motel.class);
        Root root = q.from(Motel.class);
        q.select(root);
        if (u != null && u.getFkuserroleuserId().getId() == 2) {
            q.where(b.equal(root.get("fkmoteluserId"), u.getId()));
        }
        Query query = s.createQuery(q);
        return Long.valueOf(query.getResultList().size());

    }

    @Override
    public boolean deleteMotelById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Motel p = this.getMotelById(id);
        try {
            s.delete(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Motel getMotelById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Motel.class, id);
    }

    @Override
    public boolean addOrUpdateMotel(Motel motel, Principal u) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (motel.getId() == null) {

                if (motel.getFile().length < 3) {
                    StaticSession.msgError = "Tối thiểu 3 hình ảnh";
                    return false;
                } else if (motel.getName().isEmpty() || motel.getName().trim() == "") {
                    StaticSession.msgError = "Motel name must not be empty";
                    return false;
                } else if (motel.getAddress().isEmpty() || motel.getAddress().trim() == "") {
                    StaticSession.msgError = "Address must not be empty";
                    return false;
                } else if (motel.getMap().isEmpty() || motel.getMap().trim() == "") {
                    StaticSession.msgError = "Map must not be empty";
                    return false;
                } else if (motel.getPhone().isEmpty() || motel.getPhone().trim() == "") {
                    StaticSession.msgError = "Phone must not be empty ";
                    return false;
                } else if (motel.getNumberofresidents() < 2) {
                    StaticSession.msgError = "The number of residents is greater than 2";
                    return false;
                } else if (motel.getPrice() < 100000) {
                    StaticSession.msgError = "Price is greater than 100000";
                    return false;
                } else if (motel.getTitle().isEmpty() || motel.getTitle().trim() == "") {
                    StaticSession.msgError = "Title must not be empty";
                    return false;
                } else if (motel.getDescription().isEmpty() || motel.getDescription().trim() == "") {
                    StaticSession.msgError = "Description must not be empty";
                    return false;
                } else if (motel.getAcreage().isEmpty() || motel.getAcreage().trim() == "") {
                    StaticSession.msgError = "Acreage must not be empty";
                    return false;
                }
                // Vị trí bắt đầu của chuỗi src
                int srcStart = motel.getMap().indexOf("src=\"") + 5;
                if (srcStart != 13) {
                    StaticSession.msgError = "Invalid Google map embed string";
                    return false;
                }
//                // Vị trí kết thúc của chuỗi src
//                int srcEnd = motel.getMap().indexOf("\"", srcStart);
//                // Lấy chuỗi src
//                String src = motel.getMap().substring(srcStart, srcEnd);
//                motel.setMap(src);
                motel.setCreatedDate(f.format(new Date()));
                User user = this.userService.getUserByUsername(u.getName());
                motel.setFkmoteluserId(user);
                motel.setApply(false);

                // Save the Motel object.
                s.save(motel);
                ///Save image motel
                if (motel.getFile().length > 0) {
                    for (MultipartFile file : motel.getFile()) {
                        Motelimage image = new Motelimage();
                        try {
                            Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                            image.setImage(res.get("secure_url").toString());
                        } catch (IOException ex) {
                            Logger.getLogger(MotelRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        image.setFkmotelimagemotelId(motel);
                        s.save(image);

                    }
                }
                ArrayList<String> emails = new ArrayList<String>();
                for (User follower : this.userService.getFollowerByHost(this.userService.getUserByUsername(u.getName()))) {
                    emails.add(follower.getEmail());
                }
                InternetAddress dests[] = new InternetAddress[emails.size()];
                for (int i = 0; i < emails.size(); i++) {
                    try {
                        dests[i] = new InternetAddress(emails.get(i).trim().toLowerCase());
                    } catch (AddressException ex) {
                        Logger.getLogger(MotelRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("host", user);
                model.put("link", "http://localhost:8080/quanlyphongtro/detailMotel/" + motel.getId());
                model.put("userDetails", "http://localhost:8080/quanlyphongtro/details/users/" + user.getId());

                this.emailSerivce.sendEmail(dests, "New", model);

            } else {
                if (motel.getName().isEmpty() || motel.getName().trim() == "") {
                    StaticSession.msgError = "Motel name must not be empty";
                    return false;
                } else if (motel.getAddress().isEmpty() || motel.getAddress().trim() == "") {
                    StaticSession.msgError = "Address must not be empty";
                    return false;
                } else if (motel.getMap().isEmpty() || motel.getMap().trim() == "") {
                    StaticSession.msgError = "Map must not be empty";
                    return false;
                } else if (motel.getPhone().isEmpty() || motel.getPhone().trim() == "") {
                    StaticSession.msgError = "Phone must not be empty ";
                    return false;
                } else if (motel.getNumberofresidents() < 2) {
                    StaticSession.msgError = "The number of residents is greater than 2";
                    return false;
                } else if (motel.getPrice() < 100000) {
                    StaticSession.msgError = "Price is greater than 100000";
                    return false;
                } else if (motel.getTitle().isEmpty() || motel.getTitle().trim() == "") {
                    StaticSession.msgError = "Title must not be empty";
                    return false;
                } else if (motel.getDescription().isEmpty() || motel.getDescription().trim() == "") {
                    StaticSession.msgError = "Description must not be empty";
                    return false;
                } else if (motel.getAcreage().isEmpty() || motel.getAcreage().trim() == "") {
                    StaticSession.msgError = "Acreage must not be empty";
                    return false;
                }
                // Vị trí bắt đầu của chuỗi src
                int srcStart = motel.getMap().indexOf("src=\"") + 5;
                if (srcStart != 13) {
                    StaticSession.msgError = "Invalid Google map embed string";
                    return false;
                }
//                // Vị trí kết thúc của chuỗi src
//                int srcEnd = motel.getMap().indexOf("\"", srcStart);
//                // Lấy chuỗi src
//                String src = motel.getMap().substring(srcStart, srcEnd);
//                motel.setMap(src);
                MultipartFile[] motelFile = motel.getFile();
                Stream<MultipartFile> motelFileStream = Arrays.stream(motelFile);

                if (motelFileStream.anyMatch(file -> !file.isEmpty())) {
                    if (motel.getFile().length < 3) {
                        StaticSession.msgError = "Tối thiểu 3 hình ảnh";
                        return false;
                    }
                    this.motelimageService.deleteMotelimageByMotelId(motel);

                    for (MultipartFile file : motel.getFile()) {
                        Motelimage image = new Motelimage();
                        try {
                            Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                            image.setImage(res.get("secure_url").toString());
                        } catch (IOException ex) {
                            Logger.getLogger(MotelRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        image.setFkmotelimagemotelId(motel);
                        s.save(image);

                    }
                }

                User user = this.userService.getUserByUsername(u.getName());
                motel.setFkmoteluserId(user);
                s.update(motel);
            }
            StaticSession.msgError = "";

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Motel> getListMotel(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Motel> q = b.createQuery(Motel.class
        );
        Root root = q.from(Motel.class
        );
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {

            String address = params.get("Address");
            if (address != null && !address.isEmpty()) {
                predicates.add(b.like(root.get("address"), String.format("%%%s%%", address)));
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
            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                predicates.add(b.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice)));
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                predicates.add(b.lessThanOrEqualTo(root.get("price"), Double.parseDouble(toPrice)));
            }
            String num = params.get("numberOfResidents");
            if (num != null && !num.isEmpty()) {
                predicates.add(b.equal(root.get("numberofresidents"), Integer.parseInt(num)));
            }
            String city = params.get("City2");
            if (city != null && !city.isEmpty()) {
                predicates.add(b.like(root.get("cityprovince"), String.format("%%%s%%", city)));
            }
            String district = params.get("District2");
            if (district != null && !district.isEmpty()) {
                predicates.add(b.like(root.get("district"), String.format("%%%s%%", district)));
            }
        }
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
    public Long countMotel() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Motel> q = b.createQuery(Motel.class
        );
        Root root = q.from(Motel.class
        );
        q.select(root);
        Query query = s.createQuery(q);
        return Long.valueOf(query.getResultList().size());
    }

    @Override
    public List<Motel> getListMotelApplyFalse() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Motel> q = b.createQuery(Motel.class);
        Root root = q.from(Motel.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("apply"), false));
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(root.get("id")));
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean applyMotelById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Motel motel = this.getMotelById(id);
            motel.setApply(true);
            s.update(motel);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
