package com.atishay.springboot.audit.model;

import com.atishay.springboot.common.enums.EnumForAuditAction;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserRoleAudit.class)
public abstract class UserRoleAudit_ {

	public static volatile SingularAttribute<UserRoleAudit, String> idOfEntity;
	public static volatile SingularAttribute<UserRoleAudit, Date> createdDate;
	public static volatile SingularAttribute<UserRoleAudit, String> createdBy;
	public static volatile SetAttribute<UserRoleAudit, UserAudit> setOfUserAudits;
	public static volatile SingularAttribute<UserRoleAudit, EnumForAuditAction> action;
	public static volatile SingularAttribute<UserRoleAudit, String> description;
	public static volatile SingularAttribute<UserRoleAudit, String> id;
	public static volatile SingularAttribute<UserRoleAudit, String> userRoleName;

}

