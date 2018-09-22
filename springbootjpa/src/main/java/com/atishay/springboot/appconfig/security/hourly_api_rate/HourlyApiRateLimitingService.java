package com.atishay.springboot.appconfig.security.hourly_api_rate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/***
 * This security service class allows user to call given
 * number(rate.limit.hourly.limit of application.properties) of api per hour.
 * 
 * if user exceed the limit before 1 hour, he/she will be block till that hour.
 * 
 * @author vishvas
 *
 */
@Service
public class HourlyApiRateLimitingService {

	private LoadingCache<String, Integer> hourlyApiRateCache;

	@Value("${rate.limit.hourly.limit}")
	private int hourlyLimit;

	public HourlyApiRateLimitingService() {
		super();
		hourlyApiRateCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public void saveApiRatePerUser(String key) {
		int attempts = 0;
		try {
			attempts = hourlyApiRateCache.get(key);
		} catch (ExecutionException e) {
			attempts = 0;
		}
		attempts++;
		hourlyApiRateCache.put(key, attempts);
	}

	public boolean isApiLimitOver(String key) {
		try {
			return hourlyApiRateCache.get(key) >= hourlyLimit;
		} catch (ExecutionException e) {
			return false;
		}
	}
}
