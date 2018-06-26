package ewaybill.nectar.com.ewaybill.utils;

/**
 * Created by Abhishek on 4/8/2018.
 */

public class AppConstants {

public static final String TAG = AppConstants.class.getSimpleName();

    //**********************************************************************************************//*
    //*----------------------------Local Database Constant Variables------------------------------------------*//*
    //***************************************Strat***********************************************//*

    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "UserManager8.db";

    // User table name
    public static final String TABLE_USER = "user";
    public static final String TABLE_CLIENT = "client_details";
    public static final String TABLE_TRANSPORTER = "transporter_details";
    public static final String TABLE_SUPPLIER = "supplier_details";
    public static final String TABLE_INVOICE_ORDER = "order_details";
    // User Table Columns names
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";
    public static final String COLUMN_USER_GSTIN = "user_gstin";
    public static final String COLUMN_USER_TYPE = "user_type";

    public static final String COLUMN_USER_CLIENT_ID = "client_id";
    public static final String COLUMN_USER_GSTIN_NO = "gstin_no";
    public static final String COLUMN_USER_EMAILID = "email_id";
    public static final String COLUMN_USER_NAME_CLIENT = "client_name";
    public static final String COLUMN_USER_MOBILENO = "mobile_no";
    public static final String COLUMN_USER_PINCODE = "client_pincode";
    public static final String COLUMN_USER_STATE = "client_state";
    public static final String COLUMN_USER_PLACE = "client_place";


    public static final String COLUMN_SUPPIER_ID = "supplier_id";
    public static final String COLUMN_SUPPIER_GSTIN_NO = "supplier_gstin_no";
    public static final String COLUMN_SUPPIER_EMAILID = "supplier_email_id";
    public static final String COLUMN_SUPPIER_NAME_CLIENT = "supplier_name";
    public static final String COLUMN_SUPPIER_MOBILENO = "supplier_mobile_no";
    public static final String COLUMN_SUPPIER_PINCODE = "supplier_pincode";
    public static final String COLUMN_SUPPIER_STATE = "supplier_state";
    public static final String COLUMN_SUPPIER_PLACE = "supplier_place";


    public static final String COLUMN_TRANS_ID = "transporter_id";
    public static final String COLUMN_ID_TRANSPORTER = "idTrans";
    public static final String COLUMN_TRANSPORTER_ADDRESS = "transporter_address";
    public static final String COLUMN_TRANSPORTER_EMAILID = "transporter_emailid";
    public static final String COLUMN_TRANSPORTER_NAME = "transporter_name";
    public static final String COLUMN_TRANSPORTER_MOBILENO = "transporter_mobileno";
    public static final String COLUMN_TRANSPORTER_PINCODE = "transporter_pincode";
    public static final String COLUMN_TRANSPORTER_STATE = "transporter_state";
    public static final String COLUMN_TRANSPORTER_PLACE = "transporter_place";


    public static final String COLUMN_INVOICE_ORDER_ID = "order_id";
    public static final String COLUMN_INVOICE_CLIENT_NAME = "orderClientName";
    public static final String COLUMN_INVOICE_CLIENT_GSTIN = "orderclientGstin";
    public static final String COLUMN_INVOICE_SUPPLIER_NAME = "orderSupplierName";
    public static final String COLUMN_INVOICE_SUPPLIER_GSTIN = "orderSupplierGstin";
    public static final String COLUMN_INVOICE_TRANSPORTER_NAME = "orderTransporterName";
    public static final String COLUMN_INVOICE_TRANSPORTER_GSTIN = "orderTransporterGstin";
    public static final String COLUMN_INVOICE_PRODUCT = "orderProductName";

    //***************************************End***********************************************//*
   // http://nectarinfotel.com/ewaybill/
    //http://192.168.168.101/ewaybillapp
    public static final String TEST_URL = "http://nectarinfotel.com/ewaybill/";
    public static final String LIVE_URL = "";
    public static final String SIGNUP = "signup";
    public static final String EMAIL_REGISTRATION = "Email_Registration";
    public static final String CLIENT_REGISTRATION = "client";
    public static final String SUPPLIER_REGISTRATION ="supplier";
    public static final String TRANSPORTER_REGISTRATION = "transporter";
    public static final String CLIENT_LIST = "client_list";
    public static final String SUPPLIER_LIST = "supplier_list";
    public static final String INVOICE_LIST = "invoice_list";

    public static final String TRANSPORTER_LIST = "transporter_list";
    public static final String LOGIN = "login";

    public static final String RESET_PASSWORD = "reset_password";
    public static final String NEW_PASSWORD= "new_password";
    public static final String OTP_VALUE = "otp";
    public static final String BASE_URL = TEST_URL;

    /*---------- Request Code ---------------------------------------*/
    public static final int REQUEST_ADD_SUPPLIER_CODE = 1000;
    public static final int REQUEST_OPEN_DOCUMENTS_FOLDER = 1100;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1200;

    public static final String ANDROID_DOCUMENTS_ACTIVITY = "com.android.documentsui.DocumentsActivity";
    public static final String APPLICATION_DIR_NAME = "Nectar";
    public static final String IMAGES_DIR = "/Images/";
}
