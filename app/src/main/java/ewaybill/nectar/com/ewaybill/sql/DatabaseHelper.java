package ewaybill.nectar.com.ewaybill.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import static ewaybill.nectar.com.ewaybill.utils.AppConstants.*;
import ewaybill.nectar.com.ewaybill.model.Client;
import ewaybill.nectar.com.ewaybill.model.Order;
import ewaybill.nectar.com.ewaybill.model.Supplier;
import ewaybill.nectar.com.ewaybill.model.User;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // create table sql query
    private String CREATE_INVOICE_ORDER_TABLE = "CREATE TABLE " + TABLE_INVOICE_ORDER + "("
            + COLUMN_INVOICE_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_INVOICE_CLIENT_NAME + " TEXT,"
            + COLUMN_INVOICE_CLIENT_GSTIN + " TEXT," + COLUMN_INVOICE_SUPPLIER_NAME + " TEXT,"
            + COLUMN_INVOICE_SUPPLIER_GSTIN + " TEXT," + COLUMN_INVOICE_TRANSPORTER_NAME + " TEXT,"
            + COLUMN_INVOICE_TRANSPORTER_GSTIN + " TEXT," + COLUMN_INVOICE_PRODUCT + " TEXT" + ")";


    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_USER_TYPE + " TEXT,"
            + COLUMN_USER_GSTIN + " TEXT" + ")";



    // create table sql query
    private String CREATE_CLIENT_TABLE = "CREATE TABLE " + TABLE_CLIENT + "("
            + COLUMN_USER_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_GSTIN_NO + " TEXT,"
            + COLUMN_USER_EMAILID + " TEXT," + COLUMN_USER_NAME_CLIENT + " TEXT,"
            + COLUMN_USER_MOBILENO + " TEXT," + COLUMN_USER_PINCODE + " TEXT,"
            + COLUMN_USER_STATE + " TEXT," + COLUMN_USER_PLACE + " TEXT" + ")";

    // create table sql query
    private String CREATE_SUPPLIER_TABLE = "CREATE TABLE " + TABLE_SUPPLIER + "("
            + COLUMN_SUPPIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SUPPIER_GSTIN_NO + " TEXT,"
            + COLUMN_SUPPIER_EMAILID + " TEXT," + COLUMN_SUPPIER_NAME_CLIENT + " TEXT,"
            + COLUMN_SUPPIER_MOBILENO + " TEXT," + COLUMN_SUPPIER_PINCODE + " TEXT,"
            + COLUMN_SUPPIER_STATE + " TEXT," + COLUMN_SUPPIER_PLACE + " TEXT" + ")";



    // create table sql query
    private String CREATE_TRANSPORTER_TABLE = "CREATE TABLE " + TABLE_TRANSPORTER + "("
            + COLUMN_TRANS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_ID_TRANSPORTER + " TEXT,"
            + COLUMN_TRANSPORTER_NAME + " TEXT," + COLUMN_TRANSPORTER_EMAILID + " TEXT,"
            + COLUMN_TRANSPORTER_MOBILENO + " TEXT," + COLUMN_TRANSPORTER_ADDRESS + " TEXT,"
            + COLUMN_TRANSPORTER_PINCODE + " TEXT," + COLUMN_TRANSPORTER_STATE + " TEXT,"
            + COLUMN_TRANSPORTER_PLACE + " TEXT" + ")";



    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String userName;
    private String email;
    private User user1;

    /**
     * Constructor
     * 
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CLIENT_TABLE);
        db.execSQL(CREATE_SUPPLIER_TABLE);
        db.execSQL(CREATE_TRANSPORTER_TABLE);
        db.execSQL(CREATE_INVOICE_ORDER_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param order
     */
    public void addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_INVOICE_CLIENT_NAME, order.getClientName());
        values.put(COLUMN_INVOICE_CLIENT_GSTIN, order.getClientGSTINNo());
        values.put(COLUMN_INVOICE_SUPPLIER_NAME, order.getSupplierName());
        values.put(COLUMN_INVOICE_SUPPLIER_GSTIN, order.getSupplierGSTINNo());
        values.put(COLUMN_INVOICE_TRANSPORTER_NAME, order.getTransporterName());
        values.put(COLUMN_INVOICE_TRANSPORTER_GSTIN, order.getTransporterGSTINNo());
        values.put(COLUMN_INVOICE_PRODUCT, order.getProductDescription());
        // Inserting Row
        db.insert(TABLE_INVOICE_ORDER, null, values);
        db.close();
    }


    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_GSTIN, user.getGstin());
        values.put(COLUMN_USER_TYPE, user.getUserType());
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }



    /**
     * This method is to create user record
     *
     * @param client
     */
    public void addClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_GSTIN_NO, client.getGstin());
        values.put(COLUMN_USER_EMAILID, client.getEmail());
        values.put(COLUMN_USER_NAME_CLIENT, client.getName());
        values.put(COLUMN_USER_MOBILENO, client.getMobileno());
        values.put(COLUMN_USER_PINCODE, client.getPincode());
        values.put(COLUMN_USER_STATE, client.getState());
        values.put(COLUMN_USER_PLACE, client.getPlace());

        // Inserting Row
        db.insert(TABLE_CLIENT, null, values);
        db.close();
    }



    /**
     * This method is to create user record
     *
     * @param supplier
     */
    public void addSupplier(Client supplier) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SUPPIER_GSTIN_NO, supplier.getGstin());
        values.put(COLUMN_SUPPIER_EMAILID, supplier.getEmail());
        values.put(COLUMN_SUPPIER_NAME_CLIENT, supplier.getName());
        values.put(COLUMN_SUPPIER_MOBILENO, supplier.getMobileno());
        values.put(COLUMN_SUPPIER_PINCODE, supplier.getPincode());
        values.put(COLUMN_SUPPIER_STATE, supplier.getState());
        values.put(COLUMN_SUPPIER_PLACE, supplier.getPlace());

        // Inserting Row
        db.insert(TABLE_SUPPLIER, null, values);
        db.close();
    }


    /**
     * This method is to create user record
     *
     * @param transporter
     */
    public void addTransporter(Supplier transporter) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_TRANSPORTER, transporter.getTransporterId());
        values.put(COLUMN_TRANSPORTER_NAME, transporter.getTransporterName());
        values.put(COLUMN_TRANSPORTER_EMAILID, transporter.getTransporterEmail());
        values.put(COLUMN_TRANSPORTER_MOBILENO, transporter.getTransporterMobileno());
        values.put(COLUMN_TRANSPORTER_ADDRESS, transporter.getTransporterAddress());
        values.put(COLUMN_TRANSPORTER_PINCODE, transporter.getTransporterPincode());
        values.put(COLUMN_TRANSPORTER_PLACE, transporter.getTransporterPlace());
        values.put(COLUMN_TRANSPORTER_STATE, transporter.getTransporterState());

        // Inserting Row
        db.insert(TABLE_TRANSPORTER, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_GSTIN
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setGstin(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GSTIN)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_GSTIN, user.getGstin());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param name
     * @param password
     * @return true/false
     */
    public boolean checkUser(String name, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_TYPE,
                COLUMN_USER_GSTIN
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {name, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

      //  cursor.close();
       // db.close();
        if (cursorCount > 0) {

            if (!cursor.isAfterLast()) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {

                  user1=new User();
                    user1.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                    user1.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                    user1.setUserType(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TYPE)));
                    user1.setGstin(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GSTIN)));

                  /*  textName.setText("Name : " + mobile.name);
                    textCompany.setText("Company : " + mobile.company);
                    ratingBar.setRating(mobile.rating);
*/
                    cursor.moveToNext();
                }
            }

            return true;
        }

        return false;
    }



    /**
     * This method to check user exist or not
     *
     * @param name
     * @param gstin
     * @return true/false
     */
    public boolean checkRegisteredUser(String name, String gstin) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?" + " OR " + COLUMN_USER_GSTIN + " = ?";

        // selection arguments
        String[] selectionArgs = {name, gstin};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkClient(String name, String gstin) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_CLIENT_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME_CLIENT + " = ?" + " OR " + COLUMN_USER_GSTIN_NO + " = ?";

        // selection arguments
        String[] selectionArgs = {name, gstin};

        Cursor cursor = db.query(TABLE_CLIENT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkSupplier(String name, String gstin) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_SUPPIER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_SUPPIER_NAME_CLIENT + " = ?" + " OR " + COLUMN_SUPPIER_GSTIN_NO + " = ?";

        // selection arguments
        String[] selectionArgs = {name, gstin};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_SUPPLIER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkTransporter(String name, String gstin) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_TRANS_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_TRANSPORTER_NAME + " = ?" + " OR " + COLUMN_ID_TRANSPORTER + " = ?";

        // selection arguments
        String[] selectionArgs = {name, gstin};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_TRANSPORTER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }



}
