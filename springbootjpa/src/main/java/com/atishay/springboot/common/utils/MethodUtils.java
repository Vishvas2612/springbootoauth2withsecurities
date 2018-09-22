package com.atishay.springboot.common.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * This class indicates all commonly used static methods of project.
 * 
 * @author vishvas
 *
 */
public class MethodUtils {

	/***
	 * This generic method checks weather zero or more objects are null or empty.
	 * 
	 * @param t indicates zero or more object of any type.
	 * @return returns true any object is null or empty, otherwise returns false.
	 */
	@SuppressWarnings("unchecked")
	public static <T> boolean isObjectisNullOrEmpty(T... t) {
		for (T ob : t) {
			if (ob == null || ob.toString().trim().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/***
	 * This method checks weather zero or more passed lists are null or empty.
	 * 
	 * @param listT indicates zero or more list of any type.
	 * @return returns true if any of list is empty or null, otherwise returns
	 *         false.
	 */
	public static <T> boolean isListIsNullOrEmpty(List<T> listT) {

		if (listT == null || listT.isEmpty()) {
			return true;
		}
		return false;
	}

	/***
	 * This method checks weather zero or more sets are null or empty.
	 * 
	 * @param copyOnWriteArraySetT indicates zero or more sets of any type.
	 * @return returns true if any of set is empty or null, otherwise returns false.
	 */
	public static <T> boolean isCopyOnWriteArraySetIsNullOrEmpty(CopyOnWriteArraySet<T> copyOnWriteArraySetT) {

		if (copyOnWriteArraySetT == null || copyOnWriteArraySetT.isEmpty()) {
			return true;
		}
		return false;
	}

	/***
	 * This method checks weather zero or more lists are null or empty.
	 * 
	 * @param copyOnWriteArrayListT indicates zero or more lists of any type.
	 * @return returns true if any of list is empty or null, otherwise returns
	 *         false.
	 */
	public static <T> boolean isCopyOnWriteArrayListIsNullOrEmpty(CopyOnWriteArrayList<T> copyOnWriteArrayListT) {

		if (copyOnWriteArrayListT == null || copyOnWriteArrayListT.isEmpty()) {
			return true;
		}
		return false;
	}

	/***
	 * This method checks weather zero or more sets are null or empty.
	 * 
	 * @param setT indicates zero or more sets of any type.
	 * @return returns true if any of set is empty or null, otherwise returns false.
	 */
	public static <T> boolean isSetIsNullOrEmpty(Set<T> setT) {

		if (setT == null || setT.isEmpty()) {
			return true;
		}
		return false;
	}

	/***
	 * This method checks weather zero or more maps are null or empty.
	 * 
	 * @param mapOfT indicates zero or more maps of any type.
	 * @return returns true if any of map is empty or null, otherwise returns false.
	 */
	public static <T> boolean isMapIsNullOrEmpty(Map<String, T> mapOfT) {
		if (mapOfT == null || mapOfT.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * This method gets full api path from http servlet request.
	 * 
	 * @param httpServletRequest indicates http servlet request.
	 * @return returns full api path.
	 */
	public static String getApiPathFromHttpServletRequest(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
	}

	/**
	 * This method is used to convert object to json string.
	 * 
	 * @param t indicates object of any type.
	 * @return returns json string of object using jackson.
	 */
	@SuppressWarnings("unchecked")
	public static <T> String convertObjectToStringUsingJackson(T t) {

		if (isObjectisNullOrEmpty(t)) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate5Module());

		try {
			return mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
