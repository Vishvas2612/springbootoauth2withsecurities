package com.atishay.springboot.appconfig.security.brute_force_attack;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/***
 * This service class allow any user to make unauthorized login attempts only 3
 * times.
 * 
 * if any user make 3 times unauthorized attempts then it block that
 * pc/laptop/mobile/device for 1 day.
 * 
 * @author vishvas
 *
 */
@Service
public class LoginAttemptService {

	private final int MAX_ATTEMPT = 300;
	private LoadingCache<String, Integer> attemptsCache;

	public LoginAttemptService() {
		super();
		attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public void loginSucceeded(String key) {
		attemptsCache.invalidate(key);
	}

	public int getTotalLoginAttempts(String key) {
		try {
			return attemptsCache.get(key);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void loginFailed(String key) {
		int attempts = 0;
		try {
			attempts = attemptsCache.get(key);
		} catch (ExecutionException e) {
			attempts = 0;
		}
		attempts++;
		attemptsCache.put(key, attempts);
	}

	public boolean isBlocked(String key) {
		try {
			return attemptsCache.get(key) >= MAX_ATTEMPT;
		} catch (ExecutionException e) {
			return false;
		}
	}
}
