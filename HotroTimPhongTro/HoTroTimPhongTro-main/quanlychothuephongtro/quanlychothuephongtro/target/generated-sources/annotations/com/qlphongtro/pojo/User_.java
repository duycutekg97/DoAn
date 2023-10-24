package com.qlphongtro.pojo;

import com.qlphongtro.pojo.Evaluate;
import com.qlphongtro.pojo.Findmotel;
import com.qlphongtro.pojo.Follower;
import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.Roleuser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-10-20T17:00:09")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> image;
    public static volatile SingularAttribute<User, String> firstname;
    public static volatile SetAttribute<User, Findmotel> findmotelSet;
    public static volatile SetAttribute<User, Motel> motelSet;
    public static volatile SingularAttribute<User, String> lastname;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> createdDate;
    public static volatile SetAttribute<User, Follower> followerSet1;
    public static volatile SingularAttribute<User, Roleuser> fkuserroleuserId;
    public static volatile SetAttribute<User, Follower> followerSet;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SetAttribute<User, Evaluate> evaluateSet;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}