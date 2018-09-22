package com.atishay.springboot.project.service;

import com.atishay.springboot.project.model.UserRole;

/***
 * This service class acts as service layer for UserRole.java
 * 
 * @author vishvas
 *
 */
public interface UserRoleService {

	public UserRole saveAndFlushUserRole(UserRole userRole);

	public String updateUserRole(String id, UserRole userRole);
}
