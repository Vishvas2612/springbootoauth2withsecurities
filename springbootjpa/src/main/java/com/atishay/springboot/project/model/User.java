package com.atishay.springboot.project.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.atishay.springboot.audit.listener.UserEntityListener;
import com.atishay.springboot.common.utils.MethodUtils;
import com.atishay.springboot.common.utils.VariableUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/***
 * This class indicates details for users.
 * 
 * @author vishvas
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = VariableUtils.TABLE_NAME_FOR_USER)
@JsonInclude(Include.NON_EMPTY)
@EntityListeners(UserEntityListener.class)
public class User extends Auditable<String> implements Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "userId", nullable = false, insertable = false, updatable = false)
	private String id;

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
	private UserRole userRole;

	

	
	@Transient
	private Set<String> setOfAddressesIds;

	public User() {
	}

	public User(String id) {
		this.id = id;
	}

	@PreRemove
	protected void preRemove() {

		UserRole role = this.userRole;
		if (!MethodUtils.isObjectisNullOrEmpty(role)) {
			role.getSetOfUsers().remove(this);
		}

	

		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Set<String> getSetOfAddressesIds() {
		return setOfAddressesIds;
	}

	public void setSetOfAddressesIds(Set<String> setOfAddressesIds) {
		this.setOfAddressesIds = setOfAddressesIds;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	

}
