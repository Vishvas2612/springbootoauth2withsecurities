package com.atishay.springboot.appconfig.security.brute_force_attack;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/***
 * This listener calls when any successful api call is made by any user.
 * 
 * @author vishvas
 *
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Autowired
	private HttpServletRequest request;

	public void onApplicationEvent(AuthenticationSuccessEvent e) {

		String ip = getClientIP();
		loginAttemptService.loginSucceeded(ip);
	}

	private String getClientIP() {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}
