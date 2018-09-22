package com.atishay.springboot.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.atishay.springboot.common.payload.SignUpRequest;
import com.atishay.springboot.project.model.User;

/***
 * This service class acts as service layer for User.java
 * 
 * @author vishvas
 *
 */
public interface UserService {

	public User saveAndFlushUser(User user);

	public String updateUser(String id, User user);

	public User signup(SignUpRequest signUpRequest);

	public Page<User> findUsersBySeachCriteria(String organizationId, String searchCriteria, PageRequest request);

}
