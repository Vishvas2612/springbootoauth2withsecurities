package com.atishay.springboot.appconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/***
 * This class indicates configuration for Audit.
 * 
 * @author vishvas
 *
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfig {

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new SpringSecurityAuditAwareImpl();
	}

}
