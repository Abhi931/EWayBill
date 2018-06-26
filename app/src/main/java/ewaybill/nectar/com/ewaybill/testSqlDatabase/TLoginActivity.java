package ewaybill.nectar.com.ewaybill.testSqlDatabase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import ewaybill.nectar.com.ewaybill.ClientActivity;
import ewaybill.nectar.com.ewaybill.EmailRegistrationActivity;
import ewaybill.nectar.com.ewaybill.EwayBillRegistration;
import ewaybill.nectar.com.ewaybill.ForgotPasswordActivity;
import ewaybill.nectar.com.ewaybill.PrefManager;
import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.SupplierActivity;
import ewaybill.nectar.com.ewaybill.TransporterActivity;
import ewaybill.nectar.com.ewaybill.helpers.InputValidation;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.signup.SignUpResponse;
import ewaybill.nectar.com.ewaybill.model.User;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.SignUpPresenterImpl;
import ewaybill.nectar.com.ewaybill.sql.DatabaseHelper;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.SignUpView;
import static ewaybill.nectar.com.ewaybill.utils.AppConstants.*;


public class TLoginActivity extends AppCompatActivity implements  View.OnClickListener,SignUpView {
    private final AppCompatActivity activity = TLoginActivity.this;

    private ScrollView nestedScrollView;
    public static CheckBox checkbox;
    TLoginActivity loginactivity;
    public static EditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private TextView textViewLinkRegister;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private EditText textInputEditTextGSTIN;
    public static  EditText textInputEditTextName;
    private SQLiteDatabase db;
    private User user1;
    private DatabaseHelper db1;
    private String loginUser;
    private String loginUserType;
    private String loginEmail;
    private String loginGSTIN;
   String uType="Client";
    private Button btnForgotPwd;
    public static Context context;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

        context=this;
    }


    /**
     * This method is to initialize views
     */


    private void initViews() {

        nestedScrollView = (ScrollView) findViewById(R.id.nestedScrollView);

       // textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
       // textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
       // textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (EditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextGSTIN = (EditText) findViewById(R.id.textInputEditTextGSTIN);
        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (TextView) findViewById(R.id.textViewLinkRegister);
        // textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputEditTextName = (EditText) findViewById(R.id.textInputEditTextName);
        btnForgotPwd=(Button)findViewById(R.id.btnForgotPwd);
        checkbox=(CheckBox)findViewById(R.id.checkbox);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String email=pref.getString("email", null);
        String password=pref.getString("password", null);


        if(email!=null)
        {
            Log.d("email",email);
            textInputEditTextName.setText(email);
            checkbox.setChecked(true);
        }
        if(password!=null)
        {
            Log.d("password",password);
            textInputEditTextPassword.setText(password);
            checkbox.setChecked(true);
        }



    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        btnForgotPwd.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                if (TextUtils.isEmpty(textInputEditTextName.getText().toString()))
                {
                    Snackbar.make(nestedScrollView, getString(R.string.EnterEmailAddress), Snackbar.LENGTH_LONG).show();
                }
                else  if (TextUtils.isEmpty(textInputEditTextPassword.getText().toString()))
                {
                    Snackbar.make(nestedScrollView, getString(R.string.Enterpassword), Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    login();
                }

                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), EmailRegistrationActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.btnForgotPwd:
                // Navigate to RegisterActivity
                Intent i= new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(i);
                break;
        }
    }

    private void login(){
        if (NetworkUtil.isOnline(TLoginActivity.this)) {
            checkValidation();
        } else {
          //  verifyFromSQLite();
            Snackbar.make(nestedScrollView, "Please Check Internet Connection", Snackbar.LENGTH_LONG).show();
        }
    }

    private void checkValidation() {
        if (!TextUtils.isEmpty(textInputEditTextName.getText().toString())
                && !TextUtils.isEmpty(textInputEditTextPassword.getText().toString())){
            String name = textInputEditTextName.getText().toString().trim();
            String password = textInputEditTextPassword.getText().toString().trim();

            initLoginAPIResources(name, password);
        }else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, "Please Check Internet Connection", Snackbar.LENGTH_LONG).show();
        }
    }

    public void dataDetaills(){
       String name= textInputEditTextName.getText().toString().trim();
        String password=textInputEditTextPassword.getText().toString().trim();

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_TYPE,
                COLUMN_USER_GSTIN
        };
        db1=new DatabaseHelper(this);
        db =  db1.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {name, password};
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        if (cursorCount > 0) {

            if (!cursor.isAfterLast()) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {

                    loginUser = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
                    loginEmail = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL));
                    loginUserType = cursor.getString(cursor.getColumnIndex(COLUMN_USER_TYPE));
                    loginGSTIN = cursor.getString(cursor.getColumnIndex(COLUMN_USER_GSTIN));
                    cursor.moveToNext();
                }
            }
        }
    }


    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
      /* if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        } */



        if (databaseHelper.checkUser(textInputEditTextName.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {
               dataDetaills();

            if(loginUserType.equalsIgnoreCase("Client")){
                Intent accountsIntent = new Intent(activity, ClientActivity.class);

                accountsIntent.putExtra("NAME", loginUser);
                accountsIntent.putExtra("EMAIL", loginEmail);
                accountsIntent.putExtra("USERTYPE", loginUserType);
                accountsIntent.putExtra("GSTIN", loginGSTIN);

                emptyInputEditText();
                startActivity(accountsIntent);

            }else if(loginUserType.equalsIgnoreCase("Supplier")){
                Intent accountsIntent = new Intent(activity, SupplierActivity.class);

                accountsIntent.putExtra("NAME", loginUser);
                accountsIntent.putExtra("EMAIL", loginEmail);
                accountsIntent.putExtra("USERTYPE", loginUserType);
                accountsIntent.putExtra("GSTIN", loginGSTIN);

                emptyInputEditText();
                startActivity(accountsIntent);

            }else if(loginUserType.equalsIgnoreCase("Transporter")){

                Intent accountsIntent = new Intent(activity, TransporterActivity.class);

                accountsIntent.putExtra("NAME", loginUser);
                accountsIntent.putExtra("EMAIL", loginEmail);
                accountsIntent.putExtra("USERTYPE", loginUserType);
                accountsIntent.putExtra("GSTIN", loginGSTIN);

                emptyInputEditText();
                startActivity(accountsIntent);
            }

        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }


    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextPassword.setText(null);
    }

    /*------------------------ API Resources-----------------------------*/
    private void initLoginAPIResources(String name, String password) {
        SignUpPresenterImpl loginPresenter = new SignUpPresenterImpl(TLoginActivity.this);
        loginPresenter.callApi(AppConstants.LOGIN, name,password);
    }

    @Override
    public void onSignUpSuccess(SignUpResponse signUpResponse) {
        Snackbar.make(nestedScrollView, signUpResponse.getMsg(), Snackbar.LENGTH_LONG).show();
        String rUserType=signUpResponse.getUsertype();
        if(signUpResponse.getUsertype().equalsIgnoreCase(rUserType)) {
            PrefManager.getActiveInstance(this).setUserId(Integer.parseInt(signUpResponse.getUserId()));

            Intent intent = new Intent(TLoginActivity.this, EwayBillRegistration.class);
            intent.putExtra("NAME", signUpResponse.getName());
            intent.putExtra("EMAIL", signUpResponse.getEmailid());
            intent.putExtra("USERTYPE", signUpResponse.getUsertype());
            intent.putExtra("USERID", signUpResponse.getUserId());
            intent.putExtra("GSTIN", signUpResponse.getGSTIN());


            SharedPreferences pref = TLoginActivity.context.getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();


            editor.putString("name", signUpResponse.getName());
            editor.putString("emailid", signUpResponse.getEmailid());
            editor.putString("gstin", signUpResponse.getGSTIN());
            editor.putString("userid", signUpResponse.getUserId());
            editor.putString("phoneno", signUpResponse.getContactno());
            editor.commit();
            startActivity(intent);
            finish();

        }

            // String UserType=signUpResponse.getUsertype();
            // Intent accountsIntent = null;
     /*   if(signUpResponse.getUsertype().equalsIgnoreCase("Client")){
           Intent accountsIntent = new Intent(activity, ClientActivity.class);
            startActivity(accountsIntent);
        }else if(signUpResponse.getUsertype().equalsIgnoreCase("Supplier")){
            Intent accountsIntent = new Intent(activity, SupplierActivity.class);
            startActivity(accountsIntent);
        }else if(loginUserType.equalsIgnoreCase("Transporter")){
            Intent accountsIntent = new Intent(activity, TransporterActivity.class);
            startActivity(accountsIntent);*/
        /*else if(signUpResponse.getUsertype().equalsIgnoreCase("Client")){
            Intent intent =new Intent(TLoginActivity.this, EwayBillRegistration.class);
            intent.putExtra("USERTYPE",uType);
            startActivity(intent);
        }
*/
      /*  if(accountsIntent != null) {
            accountsIntent.putExtra("NAME", signUpResponse.getName());
            accountsIntent.putExtra("EMAIL", signUpResponse.getEmailid());
            accountsIntent.putExtra("USERTYPE", signUpResponse.getUsertype());
            intent.putExtra("USERID", signUpResponse.getUserId());
            accountsIntent.putExtra("GSTIN", signUpResponse.getGSTIN());

            emptyInputEditText();
            startActivity(accountsIntent);
        }*/
    }
    @Override
    public void onSignUpFailure(String msg) {
        Snackbar.make(nestedScrollView, msg, Snackbar.LENGTH_LONG).show();
    }
}
