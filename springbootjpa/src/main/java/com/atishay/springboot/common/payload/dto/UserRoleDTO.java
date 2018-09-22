package com.atishay.springboot.common.payload.dto;

/***
 * This DTO class indicates dto class for UserRole.java
 * 
 * @author vishvas
 *
 */
public class UserRoleDTO {

	private String id;
	private String roleName;

	public UserRoleDTO() {

	}

	public UserRoleDTO(String id) {
		this.id = id;
	}

	public UserRoleDTO(String id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
