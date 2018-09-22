package com.atishay.springboot.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atishay.springboot.project.service.UserRoleService;

/***
 * This controller class indicates api for UserRole.java
 * 
 * @author vishvas
 *
 */
@RestController
@RequestMapping("/api/user_role")
public class UserRoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleController.class);

	@Autowired
	private UserRoleService userRoleService;

}
