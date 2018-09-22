package com.atishay.springboot.common.enums;

/***
 * This enum indicates different user roles.
 * 
 * @author vishvas
 *
 */
public enum EnumForUserRole {

	ROLE_USER(Roles.ROLE_USER), ROLE_ADMIN(Roles.ROLE_ADMIN), ROLE_SUPER_ADMIN(Roles.ROLE_SUPER_ADMIN);

	private final String userRole;

	public static class Roles {

		public final static String ROLE_USER = "ROLE USER";
		public final static String ROLE_ADMIN = "ROLE ADMIN";
		public final static String ROLE_SUPER_ADMIN = "ROLE SUPER ADMIN";
	}

	private EnumForUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String value() {
		return this.userRole;
	}

	@Override
	public String toString() {
		return userRole;
	}

}
