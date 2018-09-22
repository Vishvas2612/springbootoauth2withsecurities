package com.atishay.springboot.appconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories(basePackages = "com.atishay.springboot.audit.repository", entityManagerFactoryRef = "entityManagerFactoryForAudit", transactionManagerRef = "transactionManagerForAudit")
@EntityScan("com.atishay.springboot.audit.model")
public class SqlServerConfigurationForAudit {

	@Autowired
	private Environment env;

	@Bean(name = "datasourceForAudit")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.auditDatasource.driverClassName"));
		dataSource.setUrl(env.getProperty("spring.auditDatasource.url"));
		return dataSource;
	}

	/**
	 * Declare the JPA entity manager factory.
	 */
	@Bean(name = "entityManagerFactoryForAudit")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setDataSource(dataSource());

		// Classpath scanning of @Component, @Service, etc annotated class
		entityManagerFactory.setPackagesToScan(env.getProperty("spring.auditDatasource.packagesToScan"));

		// Vendor adapter
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		entityManagerFactory.setPersistenceUnitName(env.getProperty("spring.auditDatasource.persistenceUnitName"));

		// Hibernate properties
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		additionalProperties.put("hibernate.id.new_generator_mappings",
				env.getProperty("hibernate.id.new_generator_mappings"));
		additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

		entityManagerFactory.setJpaProperties(additionalProperties);

		return entityManagerFactory;
	}

	/**
	 * Declare the transaction manager.
	 */
	@Bean(name = "transactionManagerForAudit")
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
