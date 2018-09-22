package com.atishay.springboot.project.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Auditable.class)
public abstract class Auditable_ {

	public static volatile SingularAttribute<Auditable, Date> createdDate;
	public static volatile SingularAttribute<Auditable, Object> createdBy;
	public static volatile SingularAttribute<Auditable, Date> lastModifiedDate;
	public static volatile SingularAttribute<Auditable, Object> lastModifiedBy;

}

