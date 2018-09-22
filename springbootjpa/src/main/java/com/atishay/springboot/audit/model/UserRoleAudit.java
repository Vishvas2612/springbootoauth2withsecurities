package com.atishay.springboot.audit.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.atishay.springboot.common.enums.EnumForAuditAction;
import com.atishay.springboot.common.utils.MethodUtils;
import com.atishay.springboot.common.utils.VariableUtils;
import com.atishay.springboot.project.model.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/***
 * This audit class is used to maintain history data for UserRole.java
 * 
 * @author vishvas
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = VariableUtils.TABLE_NAME_FOR_AUDIT_USER_ROLE)
@JsonInclude(Include.NON_EMPTY)
@EntityListeners(AuditingEntityListener.class)
public class UserRoleAudit implements Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "userRoleAuditId", nullable = false, insertable = false, updatable = false)
	private String id;

	@Column(name = "idOfEntity", columnDefinition = "varchar(100)")
	private String idOfEntity;

	@Enumerated(EnumType.STRING)
	private EnumForAuditAction action;

	@CreatedBy
	private String createdBy;

	@CreatedDate
	@Temporal(TIMESTAMP)
	protected Date createdDate;

	@Column(name = "userRoleName", columnDefinition = "varchar(100)")
	private String userRoleName;
	@Column(name = "description", columnDefinition = "varchar(100)")
	private String description;

	public UserRoleAudit() {
	}

	public UserRoleAudit(String id) {
		super();
		this.id = id;
	}

	public UserRoleAudit(UserRole userRole, EnumForAuditAction action) {
		this.idOfEntity = userRole.getId();

		this.userRoleName = userRole.getUserRoleName();
		this.description = userRole.getDescription();

		this.action = action;
	}

	@OneToMany(mappedBy = "userRoleAudit")
	private Set<UserAudit> setOfUserAudits;

	@PreRemove
	protected void preRemove() {
		Set<UserAudit> setOfUserAudits = this.setOfUserAudits;
		if (!MethodUtils.isSetIsNullOrEmpty(setOfUserAudits)) {
			setOfUserAudits.forEach(user -> {
				user.setUserRoleAudit(null);
			});
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoleAudit other = (UserRoleAudit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EnumForAuditAction getAction() {
		return action;
	}

	public void setAction(EnumForAuditAction action) {
		this.action = action;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getIdOfEntity() {
		return idOfEntity;
	}

	public void setIdOfEntity(String idOfEntity) {
		this.idOfEntity = idOfEntity;
	}

	public Set<UserAudit> getSetOfUserAudits() {
		return setOfUserAudits;
	}

	public void setSetOfUserAudits(Set<UserAudit> setOfUserAudits) {
		this.setOfUserAudits = setOfUserAudits;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
