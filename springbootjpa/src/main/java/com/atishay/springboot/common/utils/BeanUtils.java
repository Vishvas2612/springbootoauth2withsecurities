package com.atishay.springboot.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/***
 * This class is used to get bean by bean name and bean class from spring
 * context.
 * 
 * @author vishvas
 *
 */
@Service
public class BeanUtils implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	/***
	 * This method gets bean by class name from spring boot context.
	 * 
	 * @param beanClass indicates class name.
	 * @return returns bean from spring boot context.
	 */
	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}

	/***
	 * This method gets bean by bean name from spring boot context.
	 * 
	 * @param beanName indicates bean name.
	 * @return returns bean from spring boot context.
	 */
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}
