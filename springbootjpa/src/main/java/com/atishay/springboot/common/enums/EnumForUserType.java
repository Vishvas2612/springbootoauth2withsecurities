package com.atishay.springboot.common.enums;

public enum EnumForUserType {

	TYPE_OWNER(Types.TYPE_OWNER), TYPE_ASSORT_MANAGER(Types.TYPE_ASSORT_MANAGER), TYPE_ASSORTER(Types.TYPE_ASSORTER),
	TYPE_MARKER_MANAGER(Types.TYPE_MARKER_MANAGER), TYPE_MARKER1(Types.TYPE_MARKER1), TYPE_MARKER2(Types.TYPE_MARKER2),
	TYPE_CHECKER(Types.TYPE_CHECKER), TYPE_SAWING_MANAGER(Types.TYPE_SAWING_MANAGER),
	TYPE_PEL_MANAGER(Types.TYPE_PEL_MANAGER), TYPE_GALAXY_MANAGER(Types.TYPE_GALAXY_MANAGER),
	TYPE_CORNING_MANAGER(Types.TYPE_CORNING_MANAGER);

	private final String userType;

	public static class Types {

		public final static String TYPE_OWNER = "TYPE OWNER";
		public final static String TYPE_ASSORT_MANAGER = "TYPE ASSORT MANAGER";
		public final static String TYPE_ASSORTER = "TYPE ASSORTER";
		public final static String TYPE_MARKER_MANAGER = "TYPE MARKER MANAGER";
		public final static String TYPE_MARKER1 = "TYPE MARKER1";
		public final static String TYPE_MARKER2 = "TYPE MARKER2";
		public final static String TYPE_CHECKER = "TYPE CHECKER";
		public final static String TYPE_SAWING_MANAGER = "TYPE SAWING MANAGER";
		public final static String TYPE_PEL_MANAGER = "TYPE PEL MANAGER";
		public final static String TYPE_GALAXY_MANAGER = "TYPE GALAXY MANAGER";
		public final static String TYPE_CORNING_MANAGER = "TYPE CORNING MANAGER";
	}

	private EnumForUserType(String userType) {
		this.userType = userType;
	}

	public String value() {
		return this.userType;
	}

	@Override
	public String toString() {
		return userType;
	}

}
