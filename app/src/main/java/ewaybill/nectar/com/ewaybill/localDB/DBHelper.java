package ewaybill.nectar.com.ewaybill.localDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static ewaybill.nectar.com.ewaybill.localDB.Constants.*;


public class DBHelper extends SQLiteOpenHelper  {

	private static final String DB_NAME = "ewayBill2.sqlite";
	private static final int VERSION = 2;

	public DBHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	/*	db.execSQL("create table " + TABLE_GENERAL_INFORMATION + " ( "
				+ COL_S_NUMBER + " integer primary key, "
				+ COL_DATE_OF_CULTURE + " text, "
				+ COL_DATE_OF_REPORTING + " text, "
				+ COL_SPECIMEN + " text, "
				+ COL_PATIENT_NAME + " text, "
				+ COL_CONSULTANT_IP + " integer, "
				+ COL_CS_NUMBER + " integer, "
				+ COL_WARD_NUMBER + " integer, "
				+ COL_BED_NUMBER + " integer, "
				+ COL_DIAGNOSIS_CLINICAL_HISTORY + " text, "
				+ COL_ADEQUACY + " text, "
				+ COL_SQUAMOUS_CELLS + " text, "
				+ COL_POLYMORPHS + " text, "
				+ COL_OTHER_CELLS + " text, "
				+ COL_ORGANISMS + " text, "
				+ COL_ORGANISM_ARRANGEMENT + " text, "
				+ COL_ORGANISM_NUMBER + " text, "
				+ COL_MEDIUM + " text, "
				+ COL_SIZE + " text, "
				+ COL_COLOR + " text, "
				+ COL_HEMOLYSIS + " text);");
*/
		db.execSQL("create table " + TABLE_REGISTRATION + " ( "
				+ COL_REGISTRATION_ID + " integer primary key autoincrement, "
				+ COL_RUSER_NAME + " text, "
				+ COL_RUSER_PASSWORD + " text, "
				+ COL_EMAIL_ID + " text, "
				+ COL_MOBILE_NUMBER + " integer, "
				+ COL_GSTIN + " text);");
	}		


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		

	}


}