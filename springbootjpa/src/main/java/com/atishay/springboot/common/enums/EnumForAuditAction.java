package com.atishay.springboot.common.enums;

/***
 * This enum indicates different actions for storing audit logs.
 * 
 * @author vishvas
 *
 */
public enum EnumForAuditAction {

	INSERTED("INSERTED"), UPDATED("UPDATED"), DELETED("DELETED");

	private final String name;

	private EnumForAuditAction(String value) {
		this.name = value;
	}

	public String value() {
		return this.name;
	}

	@Override
	public String toString() {
		return name;
	}

}
