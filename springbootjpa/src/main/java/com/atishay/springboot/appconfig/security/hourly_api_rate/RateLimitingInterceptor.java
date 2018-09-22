package com.atishay.springboot.appconfig.security.hourly_api_rate;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.util.concurrent.RateLimiter;

/***
 * This intercepter class uses HourlyApiRateLimitingService for allowing user to
 * given number of api call per hour
 * 
 * @author vishvas
 *
 */
@Component
public class RateLimitingInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(RateLimitingInterceptor.class);

	@Value("${rate.limit.enabled}")
	private boolean enabled;

	@Value("${rate.limit.hourly.limit}")
	private int hourlyLimit;

	@Autowired
	private HourlyApiRateLimitingService hourlyApiRateLimitingService;

	private Map<String, Optional<RateLimiter>> limiters = new ConcurrentHashMap<>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!enabled) {
			return true;
		}
		String jwtToken = request.getHeader("Authorization");

		// let non-API requests pass
		if (jwtToken == null) {
			return true;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (hourlyApiRateLimitingService.isApiLimitOver(authentication.getName())) {
			response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
			response.addHeader("X-RateLimit-Limit", String.valueOf(hourlyLimit));
			return false;
		}

		Optional<RateLimiter> rateLimiter = getRateLimiter(jwtToken);
		boolean allowRequest = rateLimiter.get().tryAcquire();

		if (!allowRequest) {
			response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
		}

		hourlyApiRateLimitingService.saveApiRatePerUser(authentication.getName());

		response.addHeader("X-RateLimit-Limit", String.valueOf(hourlyLimit));
		return allowRequest;

	}

	private Optional<RateLimiter> getRateLimiter(String jwtToken) {

		if (limiters.containsKey(jwtToken)) {
			return limiters.get(jwtToken);
		} else {
			synchronized (jwtToken.intern()) {
				// double-checked locking to avoid multiple-reinitializations
				if (limiters.containsKey(jwtToken)) {
					return limiters.get(jwtToken);
				}

				RateLimiter rateLimiter = RateLimiter.create(1.0);
				limiters.put(jwtToken, Optional.of(rateLimiter));
				return Optional.ofNullable(rateLimiter);
			}
		}
	}

}
