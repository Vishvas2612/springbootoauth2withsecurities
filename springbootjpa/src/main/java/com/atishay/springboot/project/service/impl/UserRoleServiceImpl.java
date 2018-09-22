package com.atishay.springboot.project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atishay.springboot.project.model.UserRole;
import com.atishay.springboot.project.repository.UserRoleCustomRepository;
import com.atishay.springboot.project.repository.UserRoleRepository;
import com.atishay.springboot.project.service.UserRoleService;

/***
 * This service class acts as impl class service layer of UserRole.java
 * 
 * @author vishvas
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceImpl.class);

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRoleCustomRepository userRoleCustomRepository;

	@Transactional
	@Override
	public UserRole saveAndFlushUserRole(UserRole userRole) {
		return userRoleRepository.saveAndFlush(userRole);
	}

	@Override
	public String updateUserRole(String id, UserRole userRole) {
		return id;
	}
}
