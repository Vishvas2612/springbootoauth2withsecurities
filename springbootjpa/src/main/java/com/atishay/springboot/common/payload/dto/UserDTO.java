package com.atishay.springboot.common.payload.dto;

import java.util.Date;

import com.atishay.springboot.common.utils.MyJsonDateDeserializer;
import com.atishay.springboot.common.utils.MyJsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/***
 * This DTO class indicates dto class for User.java
 * 
 * @author vishvas
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class UserDTO {

	private String id;
	private String name;
	private String email;
	private String username;
	private String password;
	private Double salary;
	private Integer age;
	private UserRoleDTO userRole;

	@JsonSerialize(using = MyJsonDateSerializer.class)
	@JsonDeserialize(using = MyJsonDateDeserializer.class)
	private Date createdDate;

	public UserDTO() {

	}

	public UserDTO(String id) {
		this.id = id;
	}

	public UserDTO(String id, String name, String email, String username, String password, Double salary, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.salary = salary;
		this.age = age;
	}

	public UserDTO(String id, String name, String email, String username, String password, Double salary, Integer age,
			Date createdDate, String userRoleId, String roleName) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.salary = salary;
		this.age = age;
		this.createdDate = createdDate;
		this.userRole = new UserRoleDTO(userRoleId, roleName);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public UserRoleDTO getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleDTO userRole) {
		this.userRole = userRole;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
