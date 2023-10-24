package com.qlphongtro.pojo;

import com.qlphongtro.pojo.Evaluate;
import com.qlphongtro.pojo.Motelimage;
import com.qlphongtro.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-10-20T17:00:09")
@StaticMetamodel(Motel.class)
public class Motel_ { 

    public static volatile SingularAttribute<Motel, String> address;
    public static volatile SingularAttribute<Motel, Boolean> apply;
    public static volatile SingularAttribute<Motel, String> acreage;
    public static volatile SingularAttribute<Motel, User> fkmoteluserId;
    public static volatile SingularAttribute<Motel, String> description;
    public static volatile SingularAttribute<Motel, Integer> numberofresidents;
    public static volatile SingularAttribute<Motel, String> title;
    public static volatile SetAttribute<Motel, Motelimage> motelimageSet;
    public static volatile SingularAttribute<Motel, String> createdDate;
    public static volatile SingularAttribute<Motel, String> phone;
    public static volatile SingularAttribute<Motel, Long> price;
    public static volatile SingularAttribute<Motel, String> district;
    public static volatile SingularAttribute<Motel, String> name;
    public static volatile SingularAttribute<Motel, Integer> id;
    public static volatile SetAttribute<Motel, Evaluate> evaluateSet;
    public static volatile SingularAttribute<Motel, String> map;
    public static volatile SingularAttribute<Motel, String> cityprovince;

}