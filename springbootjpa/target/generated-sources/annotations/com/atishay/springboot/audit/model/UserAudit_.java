package com.atishay.springboot.audit.model;

import com.atishay.springboot.common.enums.EnumForAuditAction;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserAudit.class)
public abstract class UserAudit_ {

	public static volatile SingularAttribute<UserAudit, String> lastName;
	public static volatile SingularAttribute<UserAudit, String> idOfEntity;
	public static volatile SingularAttribute<UserAudit, String> mobileNumber;
	public static volatile SingularAttribute<UserAudit, UserRoleAudit> userRoleAudit;
	public static volatile SingularAttribute<UserAudit, Double> salary;
	public static volatile SingularAttribute<UserAudit, String> firstName;
	public static volatile SingularAttribute<UserAudit, String> password;
	public static volatile SingularAttribute<UserAudit, Date> createdDate;
	public static volatile SingularAttribute<UserAudit, String> createdBy;
	public static volatile SingularAttribute<UserAudit, EnumForAuditAction> action;
	public static volatile SingularAttribute<UserAudit, String> id;
	public static volatile SingularAttribute<UserAudit, String> email;
	public static volatile SingularAttribute<UserAudit, Integer> age;
	public static volatile SingularAttribute<UserAudit, String> username;

}

