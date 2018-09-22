package com.atishay.springboot.appconfig;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/***
 * This class gets logged in user for storing historical audit data.
 * 
 * @author vishvas
 *
 */
public class SpringSecurityAuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()
				|| authentication instanceof AnonymousAuthenticationToken) {
			return Optional.empty();
		}

		String loggedInUserName = authentication.getName(); // get logged in username

		return Optional.ofNullable(loggedInUserName);
	}

}
