package com.atishay.springboot.appconfig.security.brute_force_attack;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/***
 * This listener calls when any unauthorized attempts is made by any user.
 * 
 * it gets ip address of pc/laptop/mobile/device in which unauthorized access is
 * made.
 * 
 * @author vishvas
 *
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Autowired
	private HttpServletRequest request;

	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {

		String ip = getClientIP();
		loginAttemptService.loginFailed(ip);
	}

	private String getClientIP() {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}
