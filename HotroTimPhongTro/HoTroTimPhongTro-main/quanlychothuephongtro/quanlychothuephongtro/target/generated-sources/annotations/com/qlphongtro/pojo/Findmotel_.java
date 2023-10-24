package com.qlphongtro.pojo;

import com.qlphongtro.pojo.Findmotel;
import com.qlphongtro.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-10-20T17:00:09")
@StaticMetamodel(Findmotel.class)
public class Findmotel_ { 

    public static volatile SingularAttribute<Findmotel, String> createdDate;
    public static volatile SetAttribute<Findmotel, Findmotel> findmotelSet;
    public static volatile SingularAttribute<Findmotel, String> city;
    public static volatile SingularAttribute<Findmotel, String> district;
    public static volatile SingularAttribute<Findmotel, String> detaileddescription;
    public static volatile SingularAttribute<Findmotel, Findmotel> fkfindmotelfindmotelIdReply;
    public static volatile SingularAttribute<Findmotel, User> fkfindmoteluserId;
    public static volatile SingularAttribute<Findmotel, Integer> id;
    public static volatile SingularAttribute<Findmotel, String> map;
    public static volatile SingularAttribute<Findmotel, Findmotel> fkfindmotelfindmotelId;
    public static volatile SetAttribute<Findmotel, Findmotel> findmotelSet1;

}