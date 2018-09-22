package com.atishay.springboot.project.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.atishay.springboot.project.model.Auditable_ {

	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> mobileNumber;
	public static volatile SingularAttribute<User, String> id;
	public static volatile SingularAttribute<User, Double> salary;
	public static volatile SingularAttribute<User, UserRole> userRole;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Integer> age;
	public static volatile SingularAttribute<User, String> username;

}

