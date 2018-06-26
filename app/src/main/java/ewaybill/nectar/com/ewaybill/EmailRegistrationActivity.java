package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.signup.SignUpResponse;
import ewaybill.nectar.com.ewaybill.model.User;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.SignUpPresenterImpl;
import ewaybill.nectar.com.ewaybill.sql.DatabaseHelper;
import ewaybill.nectar.com.ewaybill.testSqlDatabase.RegisterActivity;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.SignUpView;

public class EmailRegistrationActivity extends Activity  implements View.OnClickListener, AdapterView.OnItemSelectedListener,SignUpView {

    private TextInputLayout textInputLayoutEmail;
    private AppCompatButton appCompatButtonRegister;
    private Spinner spinner;
    private EditText textInputEditTextEmail;
    private ScrollView nestedScrollView;
    String usertype;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initViews();
        initListeners();
    }

    private void initViews() {
        nestedScrollView = (ScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        textInputEditTextEmail = (EditText) findViewById(R.id.textInputEditTextEmail);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.displayUserType);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select User Type");
        categories.add("Retailer");
        categories.add("Distributer");
        categories.add("Transporter");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        textInputEditTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (!textInputEditTextEmail.getText().toString().matches(emailPattern) && !TextUtils.isEmpty(textInputEditTextEmail.getText().toString()))
                {
                    textInputEditTextEmail.setError(getString(R.string.EntervalidEmailAddress));
                    textInputEditTextEmail.requestFocus();
                }

            }
        });

    }

    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                if (TextUtils.isEmpty(textInputEditTextEmail.getText().toString()))
                {
                    textInputEditTextEmail.setError(getString(R.string.EnterEmailAddress));
                    textInputEditTextEmail.requestFocus();

                }
                else
                if (!textInputEditTextEmail.getText().toString().matches(emailPattern) && !TextUtils.isEmpty(textInputEditTextEmail.getText().toString()))
                {

                    textInputEditTextEmail.setError(getString(R.string.EntervalidEmailAddress));
                    textInputEditTextEmail.requestFocus();
                }
                else if (spinner.getSelectedItem().toString().equals("Select User Type"))
                {
                    Snackbar.make(nestedScrollView, getString(R.string.selectusertype), Snackbar.LENGTH_LONG).show();
                }
                else {
                    postDataToSQLite();
                }
                break;

        }
    }

    private void postDataToSQLite() {
        if (NetworkUtil.isOnline(EmailRegistrationActivity.this)) {
            if (!TextUtils.isEmpty(textInputEditTextEmail.getText().toString())
                    && !TextUtils.isEmpty(spinner.getSelectedItem().toString().trim())){
                String email = textInputEditTextEmail.getText().toString().trim();
                String rUserType=spinner.getSelectedItem().toString().trim();

                initAPIResources(email,rUserType);
            }

        } else {
                // Snack Bar to show error message that record already exists
                Snackbar.make(nestedScrollView, "Email Already Exists", Snackbar.LENGTH_LONG).show();
                finish();

            }

    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
    }

    /*---------------------API Call-----------------------------*/
    private void initAPIResources(String email,String rUserType){
        SignUpPresenterImpl signUpPresenter = new SignUpPresenterImpl(EmailRegistrationActivity.this);
        signUpPresenter.callApi(AppConstants.EMAIL_REGISTRATION,email,rUserType);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onSignUpSuccess(SignUpResponse signUpResponse) {
        String email = textInputEditTextEmail.getText().toString().trim();
        String rUserType=spinner.getSelectedItem().toString().trim();


        Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
        Intent intent=new Intent(EmailRegistrationActivity.this, RegisterActivity.class);
        intent.putExtra("EMAILID",email);
        intent.putExtra("USERTYPE", rUserType);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSignUpFailure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        Log.d("msg",msg);
    }
}
