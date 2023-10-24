package com.qlphongtro.pojo;

import com.qlphongtro.pojo.Evaluate;
import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-10-20T17:00:09")
@StaticMetamodel(Evaluate.class)
public class Evaluate_ { 

    public static volatile SingularAttribute<Evaluate, Evaluate> fkevaluteevaluateId;
    public static volatile SingularAttribute<Evaluate, String> createdDate;
    public static volatile SingularAttribute<Evaluate, User> fkevaluteuserIdRespondent;
    public static volatile SingularAttribute<Evaluate, Evaluate> fkevaluteevaluateIdReply;
    public static volatile SetAttribute<Evaluate, Evaluate> evaluateSet1;
    public static volatile SingularAttribute<Evaluate, String> comment;
    public static volatile SingularAttribute<Evaluate, Integer> id;
    public static volatile SetAttribute<Evaluate, Evaluate> evaluateSet;
    public static volatile SingularAttribute<Evaluate, Motel> fkevalutemotelId;

}