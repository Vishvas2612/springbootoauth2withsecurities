package com.atishay.springboot.project.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.atishay.springboot.project.model.UserRole;
import com.atishay.springboot.project.repository.UserRoleCustomRepository;

/***
 * This interface indicates custom impl class of dao layer for UserRole.java
 * 
 * @author vishvas
 *
 */
@Repository
public class UserRoleCustomRepositoryImpl implements UserRoleCustomRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleCustomRepositoryImpl.class);

	@Override
	public String updateUserRole(String id, UserRole userRole) {
		return null;
	}

}
