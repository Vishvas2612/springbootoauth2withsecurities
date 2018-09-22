package com.atishay.springboot.project.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.atishay.springboot.common.utils.VariableUtils;
import com.atishay.springboot.project.model.User;
import com.atishay.springboot.project.repository.UserCustomRepository;

/***
 * This interface indicates impl class of dao layer for User.java
 * 
 * @author vishvas
 *
 */
@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserCustomRepositoryImpl.class);

	@PersistenceContext(unitName = "projectPersistenceUnit")
	private EntityManager entityManager;

	@Override
	public String updateUser(String id, User user) {
		User userFromDB = entityManager.find(User.class, id);

		userFromDB.setFirstName(user.getFirstName());
		userFromDB.setEmail(user.getEmail());

		entityManager.merge(userFromDB);

		return VariableUtils.SUCCESS_RESULT;
	}

}
