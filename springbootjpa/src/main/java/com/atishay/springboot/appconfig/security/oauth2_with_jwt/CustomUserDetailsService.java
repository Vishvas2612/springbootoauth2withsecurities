package com.atishay.springboot.appconfig.security.oauth2_with_jwt;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.atishay.springboot.appconfig.security.brute_force_attack.LoginAttemptService;
import com.atishay.springboot.common.enums.EnumForUserRole;
import com.atishay.springboot.project.model.User;
import com.atishay.springboot.project.repository.UserRepository;

/***
 * This service class load user details of logged in user.
 * 
 * @author vishvas
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String ip = getClientIP();
		if (loginAttemptService.isBlocked(ip)) {
			throw new RuntimeException("blocked");
		}

		User user = userRepository.findByUsername(username).orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());
	}

	@SuppressWarnings("rawtypes")
	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority(EnumForUserRole.ROLE_ADMIN.toString()));
	}

	private String getClientIP() {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}
