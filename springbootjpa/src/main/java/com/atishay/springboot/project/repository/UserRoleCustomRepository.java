package com.atishay.springboot.project.repository;

import com.atishay.springboot.project.model.UserRole;

/***
 * This interface indicates dao layer for UserRole.java
 * 
 * @author vishvas
 *
 */
public interface UserRoleCustomRepository {

	public String updateUserRole(String id, UserRole userRole);
}
