package com.qlphongtro.pojo;

import com.qlphongtro.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-10-20T17:00:09")
@StaticMetamodel(Roleuser.class)
public class Roleuser_ { 

    public static volatile SingularAttribute<Roleuser, String> name;
    public static volatile SingularAttribute<Roleuser, Integer> id;
    public static volatile SetAttribute<Roleuser, User> userSet;

}