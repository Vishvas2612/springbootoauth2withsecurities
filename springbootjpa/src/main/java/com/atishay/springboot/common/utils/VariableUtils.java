package com.atishay.springboot.common.utils;

import java.text.SimpleDateFormat;

/***
 * This class indicates all commonly used static variables in project.
 * 
 * @author vishvas
 *
 */
public class VariableUtils {

	/***
	 * Table name of Database.
	 */
	public static final String TABLE_NAME_FOR_USER = "tblUser";
	public static final String TABLE_NAME_FOR_USER_ROLES = "tblUserRoles";
	public static final String TABLE_NAME_FOR_USER_TYPES = "tblUserTypes";
	public static final String TABLE_NAME_FOR_MENU = "tblMenu";
	public static final String TABLE_NAME_FOR_ORGANIZATION = "tblOrganization";
	public static final String TABLE_NAME_FOR_ROUGH = "tblRough";
	public static final String TABLE_NAME_FOR_LOT = "tblLot";
	public static final String TABLE_NAME_FOR_CUT = "tblCut";
	public static final String TABLE_NAME_FOR_TAG = "tblTag";
	public static final String TABLE_NAME_FOR_PIECE = "tblPiece";

	public static final String TABLE_NAME_FOR_COUNTRY_MASTER = "tblCountryMaster";
	public static final String TABLE_NAME_FOR_STATE_MASTER = "tblStateMaster";
	public static final String TABLE_NAME_FOR_CITY_MASTER = "tblCityMaster";
	public static final String TABLE_NAME_FOR_AMOUNT_UNIT_MASTER = "tblAmountUnitMaster";
	public static final String TABLE_NAME_FOR_PARTY_MASTER = "tblPartyMaster";

	/***
	 * Table name of Database for Audit.
	 */
	public static final String TABLE_NAME_FOR_AUDIT_USER = "auditUser";
	public static final String TABLE_NAME_FOR_AUDIT_USER_ROLE = "auditUserRole";
	public static final String TABLE_NAME_FOR_AUDIT_USER_TYPE = "auditUserType";
	public static final String TABLE_NAME_FOR_AUDIT_MENU = "auditMenu";
	public static final String TABLE_NAME_FOR_AUDIT_ORGANIZATION_USER = "auditOrganization";
	public static final String TABLE_NAME_FOR_AUDIT_ROUGH = "auditRough";
	public static final String TABLE_NAME_FOR_AUDIT_LOT = "auditLot";
	public static final String TABLE_NAME_FOR_AUDIT_CUT = "auditCut";
	public static final String TABLE_NAME_FOR_AUDIT_PIECE = "auditPiece";
	public static final String TABLE_NAME_FOR_AUDIT_TAG = "auditTag";

	public static final String TABLE_NAME_FOR_AUDIT_COUNTRY_MASTER = "auditCountryMaster";
	public static final String TABLE_NAME_FOR_AUDIT_STATE_MASTER = "auditStateMaster";
	public static final String TABLE_NAME_FOR_AUDIT_CITY_MASTER = "auditCityMaster";
	public static final String TABLE_NAME_FOR_AUDIT_AMOUNT_UNIT_MASTER = "auditAmountUnitMaster";
	public static final String TABLE_NAME_FOR_AUDIT_PARTY_MASTER = "auditPartyMaster";

	/***
	 * Response variable for API.
	 */
	public static final String SUCCESS_RESULT = "SUCCESS";
	public static final String FAIL_RESULT = "FAIL";
	public static final String INCOMPLETE_RESULT = "INCOMPLETE";
	public static final String UNAPPLICABLE = "UNAPPLICABLE";
	public static final String ALREADY_AVAILABLE_RESULT = "Already Available";
	public static final String INCOMPLETE_DATA_FROM_ANGULAR = "Incomplete data from angular side.";

	public static final String ERROR = "error";

	/***
	 * Variable for different data format.
	 */
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT_OF_DATABASE = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");
	public static final String DATE_FORMAT_FROM_ANGULAR = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

}
