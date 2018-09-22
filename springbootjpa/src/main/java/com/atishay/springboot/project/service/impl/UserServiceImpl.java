package com.atishay.springboot.project.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atishay.springboot.common.enums.EnumForUserRole;
import com.atishay.springboot.common.payload.SignUpRequest;
import com.atishay.springboot.common.utils.EntitySpecificationUtils;
import com.atishay.springboot.common.utils.MethodUtils;
import com.atishay.springboot.project.model.User;
import com.atishay.springboot.project.model.UserRole;
import com.atishay.springboot.project.model.User_;
import com.atishay.springboot.project.repository.UserCustomRepository;
import com.atishay.springboot.project.repository.UserRepository;
import com.atishay.springboot.project.repository.UserRoleRepository;
import com.atishay.springboot.project.service.UserRoleService;
import com.atishay.springboot.project.service.UserService;

/***
 * This service class acts as impl class service layer of User.java
 * 
 * @author vishvas
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCustomRepository userCustomRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRoleService userRoleService;

	@Transactional
	@Override
	public User signup(SignUpRequest signUpRequest) {

		User user = new User();
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setEmail(signUpRequest.getEmail());
		user.setMobileNumber(signUpRequest.getMobileNumber());

		user.setUsername(signUpRequest.getUsername());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

		UserRole userRole = userRoleRepository.findByUserRoleName(EnumForUserRole.ROLE_ADMIN.toString()).orElse(null);

		if (MethodUtils.isObjectisNullOrEmpty(userRole)) {
			userRole = new UserRole();
			userRole.setUserRoleName(EnumForUserRole.ROLE_ADMIN.toString());
			userRoleService.saveAndFlushUserRole(userRole);

		}

		user.setUserRole(userRole);

		user = userRepository.saveAndFlush(user);

		return user;
	}

	@Transactional
	@Override
	public User saveAndFlushUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Transactional
	@Override
	public String updateUser(String id, User user) {
		return userCustomRepository.updateUser(id, user);
	}

	@Override
	public Page<User> findUsersBySeachCriteria(String organizationId, String searchCriteria, PageRequest request) {

		return userRepository.findAll(EntitySpecificationUtils.textInAllColumnsForUser(searchCriteria, Arrays.asList(
				User_.firstName.getName(), User_.email.getName(), User_.username.getName(), User_.age.getName())),
				request);

	}

}
