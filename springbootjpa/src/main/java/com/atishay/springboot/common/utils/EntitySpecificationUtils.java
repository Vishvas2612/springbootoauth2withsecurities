package com.atishay.springboot.common.utils;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.atishay.springboot.project.model.User;

/***
 * This class indicates methods that searches data in given specific columns.
 * 
 * @author vishvas
 *
 */
public class EntitySpecificationUtils {

	public static Specification<User> textInAllColumnsForUser(String text, List<String> attributes) {

		if (!text.contains("%")) {
			text = "%" + text + "%";
		}
		String finalText = text;

		return (root, query, builder) -> builder.or(root.getModel().getDeclaredSingularAttributes().stream()
				.filter(a -> attributes.contains(a.getName()))
				.map(a -> builder.like(root.get(a.getName()).as(String.class), finalText)).toArray(Predicate[]::new));
	}


}
