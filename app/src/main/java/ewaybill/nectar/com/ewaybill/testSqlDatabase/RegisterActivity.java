package ewaybill.nectar.com.ewaybill.testSqlDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ewaybill.nectar.com.ewaybill.EmailRegistrationActivity;
import ewaybill.nectar.com.ewaybill.EwayBillDashboardActivity;
import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.RegisterPageActivity;
import ewaybill.nectar.com.ewaybill.helpers.InputValidation;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.signup.SignUpResponse;
import ewaybill.nectar.com.ewaybill.model.User;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.SignUpPresenterImpl;
import ewaybill.nectar.com.ewaybill.sql.DatabaseHelper;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.SignUpView;


/**
 * Created by Abhishek on 3/27/2018.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener,SignUpView {

    private final AppCompatActivity activity = RegisterActivity.this;

    private ScrollView nestedScrollView;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private EditText textInputEditTextName;
    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;
    private EditText textInputEditTextConfirmPassword;
    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;
    private EditText textInputEditGSTIN;
    private TextInputLayout textInputLayoutGSTIN;
    private Spinner spinner;
    private EditText textInputEditTextRegisterName;
    private EditText textInputLayoutEditTextMobileNo;
    private TextInputLayout textInputLayoutRegisterAddress;
    private EditText textInputLayoutEditTextRegisterAddress;
    private TextInputLayout textInputLayoutState;
    private EditText textInputLayoutEditTextState;
    private TextInputLayout textInputLayoutPincode;
    private EditText textInputLayoutEditTextPincode;
    private String email;
    private String rUserType;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordpattern="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";

    TextInputLayout textInputLayoutRegisterName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

        Intent i=getIntent();
        email= i.getStringExtra("EMAILID");
        rUserType=i.getStringExtra("USERTYPE");

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (ScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputEditTextName = (EditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextRegisterName = (EditText) findViewById(R.id.textInputEditTextRegisterName);
        textInputLayoutEditTextMobileNo= (EditText) findViewById(R.id.textInputLayoutEditTextMobileNo);
        textInputEditTextEmail = (EditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (EditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (EditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditGSTIN = (EditText) findViewById(R.id.textInputEditGSTIN);
        textInputLayoutGSTIN = (TextInputLayout) findViewById(R.id.textInputLayoutGSTIN);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
        textInputLayoutRegisterAddress = (TextInputLayout) findViewById(R.id.textInputLayoutRegisterAddress);
        textInputLayoutEditTextRegisterAddress = (EditText) findViewById(R.id.textInputLayoutEditTextRegisterAddress);
        textInputLayoutState = (TextInputLayout) findViewById(R.id.textInputLayoutState);
        textInputLayoutEditTextState = (EditText) findViewById(R.id.textInputLayoutEditTextState);
        textInputLayoutPincode = (TextInputLayout) findViewById(R.id.textInputLayoutPincode);
        textInputLayoutEditTextPincode = (EditText) findViewById(R.id.textInputLayoutEditTextPincode);

        textInputLayoutRegisterName=(TextInputLayout) findViewById(R.id.textInputLayoutRegisterName);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.displayUserType);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select User Type");
        categories.add("Client");
        categories.add("Supplier");
        categories.add("Transporter");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        textInputEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!textInputEditTextPassword.getText().toString().matches(passwordpattern) && !TextUtils.isEmpty(textInputEditTextPassword.getText().toString()))
                {
                   textInputEditTextPassword.setError(getApplicationContext().getString(R.string.passwordpattern));
                    textInputEditTextPassword.requestFocus();
                }

            }
        });

       textInputEditTextConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!textInputEditTextPassword.getText().toString().equals(textInputEditTextConfirmPassword.getText().toString()))
                {
                    textInputEditTextConfirmPassword.setError(getString(R.string.Entermpasswordmatch));
                    textInputEditTextConfirmPassword.requestFocus();
                }

            }
        });
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        /*databaseHelper = new DatabaseHelper(activity);
        user = new User();*/
    }

  /*  private void verifyFromSQLite() {

        if (databaseHelper.checkRegisteredUser(textInputEditTextName.getText().toString().trim()
                , textInputEditGSTIN.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, EwayBillDashboardActivity.class);
            // accountsIntent.putExtra("NAME", textInputEditTextName.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }*/


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:

                if (TextUtils.isEmpty(textInputEditTextRegisterName.getText().toString()))
                {
                    textInputLayoutRegisterName.setError(getString(R.string.Entercompanyname));
                    textInputLayoutRegisterName.requestFocus();
                   // Snackbar.make(nestedScrollView, getString(R.string.Entercompanyname), Snackbar.LENGTH_LONG).show();
                }
               else if (TextUtils.isEmpty(textInputEditTextPassword.getText().toString()))
               {
                   textInputEditTextPassword.setError(getString(R.string.Enterpassword));
                   textInputEditTextPassword.requestFocus();
                  // Snackbar.make(nestedScrollView, getString(R.string.Enterpassword), Snackbar.LENGTH_LONG).show();
               }
                else
                if (!textInputEditTextPassword.getText().toString().matches(passwordpattern) && !TextUtils.isEmpty(textInputEditTextPassword.getText().toString()))
                {
                    textInputEditTextPassword.setError(getString(R.string.passwordpattern));
                    textInputEditTextPassword.requestFocus();
                  //  Snackbar.make(nestedScrollView, getString(R.string.passwordpattern), Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(textInputEditTextConfirmPassword.getText().toString()))
                {
                    textInputEditTextConfirmPassword.setError(getString(R.string.Enterconfirmpassword));
                    textInputEditTextConfirmPassword.requestFocus();
                   // Snackbar.make(nestedScrollView, getString(R.string.Enterconfirmpassword), Snackbar.LENGTH_LONG).show();
                }
                else if (!textInputEditTextPassword.getText().toString().equals(textInputEditTextConfirmPassword.getText().toString()))
                {
                    textInputEditTextConfirmPassword.setError(getString(R.string.Entermpasswordmatch));
                    textInputEditTextConfirmPassword.requestFocus();
                   // Snackbar.make(nestedScrollView, getString(R.string.Entermpasswordmatch), Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(textInputLayoutEditTextMobileNo.getText().toString()))
                {
                    textInputLayoutEditTextMobileNo.setError(getString(R.string.EnterMobileNo));
                    textInputLayoutEditTextMobileNo.requestFocus();
                   // Snackbar.make(nestedScrollView, getString(R.string.EnterMobileNo), Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(textInputEditGSTIN.getText().toString()))
                {
                    textInputEditGSTIN.setError(getString(R.string.EnterGstinNO));
                    textInputEditGSTIN.requestFocus();
                  //  Snackbar.make(nestedScrollView, getString(R.string.EnterGstinNO), Snackbar.LENGTH_LONG).show();
                }
               else {
                    postDataToSQLite();
                }
                break;

            case R.id.appCompatTextViewLoginLink:
                Intent intent=new Intent(RegisterActivity.this, TLoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
       /* if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }*/




        if (NetworkUtil.isOnline(RegisterActivity.this)) {

            String name = textInputEditTextRegisterName.getText().toString().trim(); ;
            String ruserName = textInputEditTextName.getText().toString().trim();
            String rpassword = textInputEditTextPassword.getText().toString().trim();
           // String email = textInputEditTextEmail.getText().toString().trim();
            String mobileNo =textInputLayoutEditTextMobileNo.getText().toString().trim();
            String gstn = textInputEditGSTIN.getText().toString().trim();
            String address = null;
            String state = null;
            String pincode = null;
         //   String rUserType=spinner.getSelectedItem().toString().trim();


            initAPIResources(name,ruserName,rpassword,email,gstn,mobileNo,rUserType,address,state,pincode);
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
        } else {
            databaseHelper = new DatabaseHelper(activity);
            user = new User();
            if (!databaseHelper.checkRegisteredUser(textInputEditTextName.getText().toString().trim(),
                    textInputEditGSTIN.getText().toString().trim())) {

                user.setName(textInputEditTextName.getText().toString().trim());
                user.setEmail(textInputEditTextEmail.getText().toString().trim());
                user.setPassword(textInputEditTextPassword.getText().toString().trim());
                user.setGstin(textInputEditGSTIN.getText().toString().trim());
                user.setUserType(spinner.getSelectedItem().toString());

                databaseHelper.addUser(user);

                // Snack Bar to show success message that record saved successfully
                Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                emptyInputEditText();


            } else {
                // Snack Bar to show error message that record already exists
                Snackbar.make(nestedScrollView, getString(R.string.account_already_exists), Snackbar.LENGTH_LONG).show();
            }
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
       // textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputEditGSTIN.setText(null);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*---------------------API Call-----------------------------*/
    private void initAPIResources(String name, String ruserName, String rpassword, String email, String gstn, String mobileNo,String rUserType
            , String address, String state, String pincode){
        SignUpPresenterImpl signUpPresenter = new SignUpPresenterImpl(RegisterActivity.this);
        signUpPresenter.callApi(AppConstants.SIGNUP,name,ruserName,rpassword,email,gstn,mobileNo,rUserType,address,state,pincode);
    }


   /* *//*---------------------API Call-----------------------------*//*
    private void initAPIResources(String name, String ruserName, String rpassword, String email, String gstn, String mobileNo){
        SignUpPresenterImpl signUpPresenter = new SignUpPresenterImpl(RegisterActivity.this);
        signUpPresenter.callApi(name,ruserName,rpassword,email,gstn,mobileNo);
    }*/

    @Override
    public void onSignUpSuccess(SignUpResponse signUpResponse) {
        Toast.makeText(this,signUpResponse.getMsg(),Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onSignUpFailure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
