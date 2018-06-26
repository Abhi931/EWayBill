package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.signup.SignUpResponse;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.SignUpPresenterImpl;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.SignUpView;

import static ewaybill.nectar.com.ewaybill.localDB.Constants.COL_EMAIL_ID;
import static ewaybill.nectar.com.ewaybill.localDB.Constants.COL_GSTIN;
import static ewaybill.nectar.com.ewaybill.localDB.Constants.COL_MOBILE_NUMBER;
import static ewaybill.nectar.com.ewaybill.localDB.Constants.COL_REGISTRATION_ID;
import static ewaybill.nectar.com.ewaybill.localDB.Constants.COL_RUSER_NAME;
import static ewaybill.nectar.com.ewaybill.localDB.Constants.COL_RUSER_PASSWORD;
import static ewaybill.nectar.com.ewaybill.localDB.Constants.CONTENT_URI_REGISTER_EWAYBILL;

/**
 * Created by Abhishek on 4/6/2018.
 */

public class RegisterPageActivity extends Activity {

    private Button btnRegister;
    private Button btnCancel;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etEmail;
    private EditText etMobile;
    private EditText userGSTIN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
     /*   btnRegister=(Button) findViewById(R.id.btnRegister);
        etUserName=(EditText)findViewById(R.id.etUserName);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etMobile=(EditText)findViewById(R.id.etMobile);
        userGSTIN=(EditText)findViewById(R.id.userGSTIN);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = "";
                String ruserName = etUserName.getText().toString();
                String rpassword = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                String mobileNo = etMobile.getText().toString();
                String usertype = userGSTIN.toString();

                if(ruserName.equals("")|| rpassword.equals("")
                        || email.equals("") || mobileNo.equals("")
                        || usertype.equals(""))
                {
                    Toast.makeText(RegisterPageActivity.this, "Please fill the empty(*) fields", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    if (NetworkUtil.isOnline(RegisterPageActivity.this)) {
                       initAPIResources(name,ruserName,rpassword,email,usertype,mobileNo);

                    } else {
                        String where = COL_RUSER_NAME + "=?";
                        String[] whereclause = new String[]{ruserName};
                        String projection[] = {COL_REGISTRATION_ID, COL_RUSER_NAME, COL_RUSER_PASSWORD};
                        Cursor cursor = getContentResolver().query(CONTENT_URI_REGISTER_EWAYBILL, projection, where, whereclause, null);

                        if (cursor.getCount() < 1) // UserName Not Exist
                        {
                            cursor.close();
                            ContentValues values = new ContentValues();

                            values.put(COL_RUSER_NAME, ruserName);
                            values.put(COL_RUSER_PASSWORD, rpassword);
                            values.put(COL_EMAIL_ID, etEmail.getText().toString());
                            values.put(COL_MOBILE_NUMBER, etMobile.getText().toString());
                            values.put(COL_GSTIN, userGSTIN.toString());

                            // ((LoginSignInActivity) getActivity()).getContentResolverMethod(values);
                            //  (C.getContentResolverMethod(values);
                            Log.e("COL_RUSER_NAME:", etUserName.getText().toString());
                            Log.e("COL_RUSER_PASSWORD :", etPassword.getText().toString());
                            Log.e("COL_EMAIL_ID :", etEmail.getText().toString());
                            Log.e("COL_MOBILE_NUMBER :", etMobile.getText().toString());
                            Log.e("COL_USER_TYPE :", userGSTIN.toString());

                            Toast.makeText(RegisterPageActivity.this, "Account Successfully Created " + values, Toast.LENGTH_LONG).show();
                            etUserName.setText("");
                            etPassword.setText("");
                            etEmail.setText("");
                            etMobile.setText("");
                            //Toast.makeText(getActivity(), "Please enter the correct User Name and Passward", Toast.LENGTH_LONG).show();

                            return;
                        }
                        cursor.moveToFirst();

                        String username = cursor.getString(cursor.getColumnIndex(COL_RUSER_NAME));
                        cursor.close();

                        if (ruserName.equalsIgnoreCase(username)) {

                            Toast.makeText(RegisterPageActivity.this, "UserName already Exists", Toast.LENGTH_LONG).show();
                        }
				*//*else if(!ruserName.equalsIgnoreCase(username)){

				}*//*
         *//*	ContentValues values = new ContentValues();

				values.put(COL_RUSER_NAME, ruserName);
				values.put(COL_RUSER_PASSWORD, rpassword);
				values.put(COL_EMAIL_ID, etEmail.getText().toString());
				values.put(COL_MOBILE_NUMBER,etMobile.getText().toString());
				values.put(COL_USER_TYPE,userType.toString());

				((LoginSignInActivity)getActivity()).getContentResolverMethod(values);
				Log.e("COL_RUSER_NAME:",etUserName.getText().toString());
				Log.e("COL_RUSER_PASSWORD :", etPassword.getText().toString());
				Log.e("COL_EMAIL_ID :", etEmail.getText().toString());
				Log.e("COL_MOBILE_NUMBER :",etMobile.getText().toString());
				Log.e("COL_USER_TYPE :", userType.toString());

				Toast.makeText(getActivity(), "Account Successfully Created " + values, Toast.LENGTH_LONG).show();*//*

                        Toast.makeText(RegisterPageActivity.this, "Registration Done!", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }
            }
        });

        btnCancel=(Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    *//*---------------------API Call-----------------------------*//*
    private void initAPIResources(String name, String ruserName, String rpassword, String email, String usertype, String mobileNo){
        SignUpPresenterImpl signUpPresenter = new SignUpPresenterImpl(RegisterPageActivity.this);
        signUpPresenter.callApi(name,ruserName,rpassword,email,usertype,mobileNo);
    }

    @Override
    public void onSignUpSuccess(SignUpResponse signUpResponse) {
        Toast.makeText(this,signUpResponse.getMsg(),Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onSignUpFailure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

*/

    }
}