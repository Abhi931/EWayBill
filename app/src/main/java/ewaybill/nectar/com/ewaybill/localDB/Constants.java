package ewaybill.nectar.com.ewaybill.localDB;

import android.net.Uri;

public class Constants {
	// related Login table
	public static final String TABLE_LOGIN = "LogIn";
	public static final String COL_USER_ID = "UserId";
	public static final String COL_USER_NAME = "UserName";
	public static final String COL_USER_PASSWORD = "UserPassword";

	// related registration table
	public static final String TABLE_REGISTRATION = "RegisterTable";
	public static final String COL_REGISTRATION_ID = "RegistrationId";
	public static final String COL_RUSER_NAME = "RUserName";
	public static final String COL_RUSER_PASSWORD = "RUserPassword";
	public static final String COL_EMAIL_ID = "REmailId";
	public static final String COL_MOBILE_NUMBER = "RMobileNo";
	public static final String COL_GSTIN = "RGSTIN";

	// related generalinfo table
	public static final String TABLE_GENERAL_INFORMATION="GeneralInfoTable";
	public static final String COL_S_NUMBER= "SNo";
	public static final String COL_DATE_OF_CULTURE = "DateOfCulture";
	public static final String COL_DATE_OF_REPORTING = "DateOfReporting";
	public static final String COL_SPECIMEN = "Specimen";
	public static final String COL_PATIENT_NAME = "PatientName";
	public static final String COL_CONSULTANT_IP = "ConsultantIp";
	public static final String COL_CS_NUMBER= "CsNumber";
	public static final String COL_WARD_NUMBER = "WardNumber";
	public static final String COL_BED_NUMBER = "BedNumber";
	public static final String COL_DIAGNOSIS_CLINICAL_HISTORY = "DiagnosisCHistory";
	public static final String COL_ADEQUACY="Adequacy"; 
	public static final String COL_SQUAMOUS_CELLS= "SquamousCells";
	public static final String COL_POLYMORPHS = "Polymorphs";
	public static final String COL_OTHER_CELLS = "OtherCells";
	public static final String COL_ORGANISMS = "Organism";
	public static final String COL_ORGANISM_ARRANGEMENT = "organismArrangement";
	public static final String COL_ORGANISM_NUMBER = "OrganismNumber";
	public static final String COL_MEDIUM= "Medium";
	public static final String COL_SIZE = "BSize";
	public static final String COL_COLOR = "BColor";
	public static final String COL_HEMOLYSIS = "BHemolysis";
	
/*	public static final String KEY_TYPE = "KEY_TYPE";
	public static final int TYPE_GENRAL_INFO = 1;
	public static final int TYPE_GMZN_INFO = 2;
	public static final int TYPE_Gross = 3;*/

	// related to content provider
	public static final String SCHEME = "content://";
	public static final String AUTHORITY = "ewaybill.nectar.com.ewaybill";
	public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY);
	
	/*public static final String PATH_EMBRYYO_DATA = "GeneralInfoTable";
	public static final Uri CONTENT_URI_EMBRYYO = Uri.parse(SCHEME + AUTHORITY + "/" + PATH_EMBRYYO_DATA);
*/
	public static final String PATH_EWAYBILL_REGISTER_DATA = "RegisterTable";
	public static final Uri CONTENT_URI_REGISTER_EWAYBILL = Uri.parse(SCHEME + AUTHORITY + "/" + PATH_EWAYBILL_REGISTER_DATA);
}
