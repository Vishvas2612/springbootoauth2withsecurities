package com.atishay.springboot.audit.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
import com.atishay.springboot.project.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/***
 * This audit class is used to maintain history data for User.java
 * 
 * @author vishvas
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = VariableUtils.TABLE_NAME_FOR_AUDIT_USER)
@JsonInclude(Include.NON_EMPTY)
@EntityListeners(AuditingEntityListener.class)
public class UserAudit implements Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "userAuditId", nullable = false, insertable = false, updatable = false)
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

	@Column(name = "firstName", columnDefinition = "varchar(35)")
	private String firstName;

	@Column(name = "lastName", columnDefinition = "varchar(35)")
	private String lastName;

	@Column(name = "email", columnDefinition = "varchar(254)")
	private String email;

	@Column(name = "mobileNumber", columnDefinition = "varchar(15)")
	private String mobileNumber;

	@Column(name = "username", columnDefinition = "varchar(25)")
	private String username;
	@Column(name = "password", columnDefinition = "varchar(500)")
	private String password;
	@Column(name = "salary", columnDefinition = "Decimal(10,2)")
	private Double salary;
	@Column(name = "age")
	private Integer age;

	@ManyToOne
	@JsonIgnore
	private UserRoleAudit userRoleAudit;

	

	public UserAudit() {
	}

	public UserAudit(String id) {
		super();
		this.id = id;
	}

	public UserAudit(User user, UserRoleAudit userRoleAudit, EnumForAuditAction action) {
		this.idOfEntity = user.getId();

		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.salary = user.getSalary();
		this.age = user.getAge();

		this.userRoleAudit = userRoleAudit;
		this.action = action;
	}

	@PreRemove
	protected void preRemove() {
		UserRoleAudit userRoleAudit = this.userRoleAudit;
		if (!MethodUtils.isObjectisNullOrEmpty(userRoleAudit)) {
			userRoleAudit.getSetOfUserAudits().remove(this);
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
		UserAudit other = (UserAudit) obj;
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

	public UserRoleAudit getUserRoleAudit() {
		return userRoleAudit;
	}

	public void setUserRoleAudit(UserRoleAudit userRoleAudit) {
		this.userRoleAudit = userRoleAudit;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



}
