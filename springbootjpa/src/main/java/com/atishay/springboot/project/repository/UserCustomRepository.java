package com.atishay.springboot.project.repository;

import com.atishay.springboot.project.model.User;

/***
 * This interface indicates dao layer for User.java
 * 
 * @author vishvas
 *
 */
public interface UserCustomRepository {

	public String updateUser(String id, User user);
}
