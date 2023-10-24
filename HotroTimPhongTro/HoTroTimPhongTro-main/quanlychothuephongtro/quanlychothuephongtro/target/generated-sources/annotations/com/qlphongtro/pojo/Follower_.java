package com.qlphongtro.pojo;

import com.qlphongtro.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-10-20T17:00:09")
@StaticMetamodel(Follower.class)
public class Follower_ { 

    public static volatile SingularAttribute<Follower, User> fkfolloweruserRenterId;
    public static volatile SingularAttribute<Follower, Integer> id;
    public static volatile SingularAttribute<Follower, User> fkfolloweruserHostId;

}