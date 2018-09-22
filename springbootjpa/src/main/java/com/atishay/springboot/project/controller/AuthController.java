package com.atishay.springboot.project.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.atishay.springboot.common.payload.SignUpRequest;
import com.atishay.springboot.common.payload.dto.ResponseWrapperDTO;
import com.atishay.springboot.common.utils.MethodUtils;
import com.atishay.springboot.project.model.User;
import com.atishay.springboot.project.repository.UserRepository;
import com.atishay.springboot.project.service.UserRoleService;
import com.atishay.springboot.project.service.UserService;

/***
 * This controller indicates api for sign up, forgot password etc where there is
 * no oauth2 jwt token required.
 * 
 * @author vishvas
 *
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserRoleService userRoleService;


	@GetMapping(value = "/hello")
	public String printHello() {
		return "hello world";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest signUpRequest,
			HttpServletRequest httpServletRequest) {

		
		// return if user name is already present.
		if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
			return new ResponseEntity(new ResponseWrapperDTO(HttpServletResponse.SC_CONFLICT,
					httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString(),
					"Username is already taken!"), HttpStatus.CONFLICT);
		}

		// return if mobile number is already present.
		if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
			return new ResponseEntity(new ResponseWrapperDTO(HttpServletResponse.SC_CONFLICT,
					httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString(),
					"Email address already taken!"), HttpStatus.CONFLICT);
		}

		// return if mobile number is already present.
		if (userRepository.findByMobileNumber(signUpRequest.getMobileNumber()).isPresent()) {
			return new ResponseEntity(new ResponseWrapperDTO(HttpServletResponse.SC_CONFLICT,
					httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString(),
					"Mobile number already taken!"), HttpStatus.CONFLICT);
		}

		User result = userService.signup(signUpRequest);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ResponseWrapperDTO(HttpServletResponse.SC_OK,
				MethodUtils.getApiPathFromHttpServletRequest(httpServletRequest), "You registered successfully."));
	}

}
