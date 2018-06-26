package ewaybill.nectar.com.ewaybill.localDB;

import static ewaybill.nectar.com.ewaybill.localDB.Constants.*;

import java.util.List;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

public class EwaybillContentProvider extends ContentProvider {
	DBHelper helper; 
	SQLiteDatabase database;
	private static final int CODE_DEFAULT = 1;
	private static final int CODE_EMBRYYO_REGISTER_DATA=2;
	private static final int CODE_REGISTER_ID = 3;

	static UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

		uriMatcher.addURI(AUTHORITY, null, CODE_DEFAULT);

	//	uriMatcher.addURI(AUTHORITY, PATH_EMBRYYO_DATA, CODE_EMBRYYO);

		uriMatcher.addURI(AUTHORITY, PATH_EWAYBILL_REGISTER_DATA, CODE_EMBRYYO_REGISTER_DATA);

		//uriMatcher.addURI(AUTHORITY, PATH_EMBRYYO_REGISTER_DATA + "/#", CODE_REGISTER_ID);

	}
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int code = uriMatcher.match(uri);
		if (code == CODE_EMBRYYO_REGISTER_DATA) {
			database.insert(TABLE_REGISTRATION, null, values);	
			Toast.makeText(getContext(), "Registeration Successfull" , Toast.LENGTH_LONG).show();
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		helper = new DBHelper(getContext());
		database = helper.getWritableDatabase();
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		int code = uriMatcher.match(uri);
		 if (code == CODE_EMBRYYO_REGISTER_DATA) {
			cursor = database.query(TABLE_REGISTRATION, projection, selection, selectionArgs, null, null, sortOrder);
		}
		else if (code == CODE_REGISTER_ID) {
			//List<String> segments = uri.getPathSegments();
			//String where = COL_REGISTRATION_ID + "=" + segments.get(1);
			cursor = database.query(TABLE_REGISTRATION, projection, selection, selectionArgs, null, null, sortOrder);	
		}

		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int code = uriMatcher.match(uri);
	if (code == CODE_EMBRYYO_REGISTER_DATA) {
			database.update(TABLE_REGISTRATION, values, selection, selectionArgs);	
		}
		return 0;
	}

}
