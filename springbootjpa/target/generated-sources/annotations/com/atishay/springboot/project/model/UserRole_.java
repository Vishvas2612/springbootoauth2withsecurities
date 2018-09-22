package com.atishay.springboot.project.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserRole.class)
public abstract class UserRole_ extends com.atishay.springboot.project.model.Auditable_ {

	public static volatile SingularAttribute<UserRole, String> description;
	public static volatile SingularAttribute<UserRole, String> id;
	public static volatile SetAttribute<UserRole, User> setOfUsers;
	public static volatile SingularAttribute<UserRole, String> userRoleName;

}

