package com.atishay.springboot.project.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.atishay.springboot.common.enums.EnumForUserRole;
import com.atishay.springboot.common.payload.dto.ResponseErrorDTO;
import com.atishay.springboot.common.payload.dto.ResponseWrapperDTO;
import com.atishay.springboot.common.utils.MethodUtils;
import com.atishay.springboot.common.utils.VariableUtils;
import com.atishay.springboot.project.model.User;
import com.atishay.springboot.project.repository.UserRepository;
import com.atishay.springboot.project.service.UserService;

/***
 * This controller class indicates api for User.java
 * 
 * @author vishvas
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin
	@PutMapping(value = "/updateUser/{id}")
	@ResponseBody
	public ResponseEntity<?> updatePatient(@PathVariable(name = "id") String id, @Valid @RequestBody User user,
			HttpServletRequest httpServletRequest) {
		try {

			if (MethodUtils.isObjectisNullOrEmpty(id, user)) {
				return new ResponseEntity(new ResponseErrorDTO(HttpServletResponse.SC_BAD_REQUEST,
						MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest),
						VariableUtils.INCOMPLETE_DATA_FROM_ANGULAR), HttpStatus.BAD_REQUEST);
			}

			userService.updateUser(id, user);

			return ResponseEntity.ok(new ResponseWrapperDTO(HttpServletResponse.SC_OK,
					httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString(),
					"User updated successfully."));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(
					new ResponseErrorDTO(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("getUserByUserName")
	public ResponseEntity<?> getUserByUserName(@RequestParam String username, HttpServletRequest httpServletRequest) {

		User user = new User();
		try {

			user = userRepository.findByUsername(username).orElse(new User());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(
					new ResponseErrorDTO(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(new ResponseWrapperDTO(HttpServletResponse.SC_OK,
				MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest), "User got successfully.",
				MethodUtils.isObjectisNullOrEmpty(user) ? new User() : user));

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin
	@GetMapping("findAllUsersWithPagination")
	@PreAuthorize("hasRole('" + EnumForUserRole.Roles.ROLE_ADMIN + "')")
	public ResponseEntity<?> findAllUsersWithPagination(@RequestParam Integer pageNumber,
			@RequestParam Integer noOfRecords, @RequestParam String sortColumn, @RequestParam String sortOrder,
			HttpServletRequest httpServletRequest) {

		Page<User> listOfUsers = null;
		try {

			@SuppressWarnings("deprecation")
			PageRequest request = new PageRequest(pageNumber - 1, noOfRecords,
					sortOrder.equals("A") ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn);

			listOfUsers = userRepository.findAll(request);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(
					new ResponseErrorDTO(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(new ResponseWrapperDTO(HttpServletResponse.SC_OK,
				MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest), "Users got successfully.",
				listOfUsers));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin
	@GetMapping("paginationUsingNativeQuery")
	@PreAuthorize("hasRole('" + EnumForUserRole.Roles.ROLE_ADMIN + "')")
	public ResponseEntity<?> paginationUsingNativeQuery(@RequestParam Integer age, @RequestParam Integer pageNumber,
			@RequestParam Integer noOfRecords, @RequestParam String sortColumn, @RequestParam String sortOrder,
			HttpServletRequest httpServletRequest) {

		Page<User> listOfUsers = null;
		try {

			@SuppressWarnings("deprecation")
			PageRequest request = new PageRequest(pageNumber - 1, noOfRecords,
					sortOrder.equals("A") ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn);

			listOfUsers = userRepository.paginationUsingNativeQuery(age, request);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(
					new ResponseErrorDTO(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(new ResponseWrapperDTO(HttpServletResponse.SC_OK,
				MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest), "Users got successfully.",
				listOfUsers));
	}

	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("getUserById")
	public ResponseEntity<?> getUserById(@RequestParam String id, HttpServletRequest httpServletRequest) {

		User user = null;
		try {

			user = userRepository.findById(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(
					new ResponseErrorDTO(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(new ResponseWrapperDTO(HttpServletResponse.SC_OK,
				MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest), "User got successfully.",
				MethodUtils.isObjectisNullOrEmpty(user) ? new User() : user));

	}

}
