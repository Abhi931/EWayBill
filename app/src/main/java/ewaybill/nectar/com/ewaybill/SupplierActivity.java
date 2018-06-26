package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static ewaybill.nectar.com.ewaybill.utils.AppConstants.*;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserClientRegistrationResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserSupplierRegistrationResponse;
import ewaybill.nectar.com.ewaybill.model.Client;
import ewaybill.nectar.com.ewaybill.model.Supplier;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.UserClientRegistrationPresenterImpl;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.UserDetailsPresenterImpl;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.UserSupplierRegistrationPresenterImpl;
import ewaybill.nectar.com.ewaybill.sql.DatabaseHelper;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.UserClientRegistrationView;
import ewaybill.nectar.com.ewaybill.viewstate.UserSupplierRegistrationView;

/**
 * Created by Abhishek on 4/12/2018.
 */

public class SupplierActivity extends Activity implements View.OnClickListener,UserSupplierRegistrationView{


    private View rootView;
    private Button btnUnRegisteredDetails;
    private Button btnRegisteredDetails;
    private LinearLayout unRegisteredLayout;
    private RelativeLayout registeredLayout;
    private EditText etGSTIN;
    private EditText etName;
    private EditText etMobileNo;
    private EditText etEmailId;
    private EditText etPlace;
    private EditText etPincode;
    private EditText etState;
    private Button btnSave;
    private Button btnCancel;
    private DatabaseHelper databaseHelper;
    private Client client;
    private View nestedScrollView;
    private Button btnClear;
    private RelativeLayout relativeLayout;
    private Button btnNext;
    private String nameU;
    private String emailU;
    private String userType;
    private String gstnU;
    private Button btnUpdate;
    private EditText userTypeEditText;
    private String loginUserId;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);

        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);

        etGSTIN=(EditText)findViewById(R.id.etGSTIN);
        etName=(EditText)findViewById(R.id.etName);
        etMobileNo=(EditText)findViewById(R.id.etMobileNo);
        etEmailId=(EditText)findViewById(R.id.etEmailId);
        etPlace=(EditText)findViewById(R.id.etPlace);
        etPincode=(EditText)findViewById(R.id.etPincode);
        etState=(EditText)findViewById(R.id.etState);
        userTypeEditText=(EditText)findViewById(R.id.userTypeEditText);


        Intent i=getIntent();
      //  loginUserId= i.getStringExtra("USERID");
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        loginUserId=pref.getString("userid", null);
       /* Intent intent=getIntent();
        if(intent!=null) {
            nameU = intent.getStringExtra("NAME");
            emailU = intent.getStringExtra("EMAIL");
            userType = intent.getStringExtra("USERTYPE");
            gstnU = intent.getStringExtra("GSTIN");

            etName.setText(nameU);
            etEmailId.setText(emailU);
            etGSTIN.setText(gstnU);
        }*/
        btnSave=(Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
/*
        btnClear=(Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);*/

       /* btnNext=(Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToSQLite();
                btnUpdate.setEnabled(false);
                //updateUser(etName.getText().toString().trim(),etGSTIN.getText().toString().trim());
               // Snackbar.make(relativeLayout, getString(R.string.supplier_success_update_message), Snackbar.LENGTH_LONG).show();
            }
        });

        btnUpdate.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);*/

       /* if(!(etGSTIN.getText().toString().trim()).isEmpty()){

            btnSave.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
        }*/
    }

    private void postDataToSQLite() {
        if (NetworkUtil.isOnline(SupplierActivity.this)) {

            String gstin =etGSTIN.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String email =etEmailId.getText().toString().trim();
            String mobileno =etMobileNo.getText().toString().trim();
            String usertype = userTypeEditText.getText().toString().trim();
            String place =etPlace.getText().toString().trim();
            String pincode =etPincode.getText().toString().trim();
            String state =etState.getText().toString().trim();
            String userid=loginUserId;


            initAPIResources(gstin,name,email,mobileno,usertype,place,pincode,state,userid);
            // Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();

        }else {

            databaseHelper = new DatabaseHelper(this);
            if (!databaseHelper.checkSupplier(etName.getText().toString().trim(),
                    etGSTIN.getText().toString().trim())) {

                Client client = new Client();


                //  if (!(etGSTIN.getText().toString().trim().isEmpty())) {

                client.setGstin(etGSTIN.getText().toString().trim());
                client.setName(etName.getText().toString().trim());
                client.setMobileno(etMobileNo.getText().toString().trim());
                client.setEmail(etEmailId.getText().toString().trim());
                client.setPlace(etPlace.getText().toString().trim());
                client.setPincode(etPincode.getText().toString().trim());
                client.setState(etState.getText().toString().trim());

                databaseHelper.addSupplier(client);

                // Snack Bar to show success message that record saved successfully
                Snackbar.make(relativeLayout, getString(R.string.supplier_success_message), Snackbar.LENGTH_LONG).show();
                emptyInputEditText();


            } else {
                // Snack Bar to show error message that record already exists
                Snackbar.make(relativeLayout, getString(R.string.account_already_exists), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private void emptyInputEditText() {
        etGSTIN.setEnabled(false);
        etName.setEnabled(false);
        etMobileNo.setEnabled(false);
        etEmailId.setEnabled(false);
        etPlace.setEnabled(false);
        etPincode.setEnabled(false);
        etState.setEnabled(false);

    }

    public void updateUser(String name, String gstin) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_SUPPIER_ID,
                COLUMN_SUPPIER_GSTIN_NO,
                COLUMN_SUPPIER_EMAILID,
                COLUMN_SUPPIER_NAME_CLIENT,
                COLUMN_SUPPIER_MOBILENO,
                COLUMN_SUPPIER_PINCODE,
                COLUMN_SUPPIER_STATE,
                COLUMN_SUPPIER_PLACE

        };
        DatabaseHelper db=new DatabaseHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_SUPPIER_NAME_CLIENT + " = ?" + " OR " + COLUMN_SUPPIER_GSTIN_NO + " = ?";

        // selection arguments
        String[] selectionArgs = {name, gstin};

        // SQLiteDatabase db1 = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SUPPIER_GSTIN_NO, etGSTIN.getText().toString().trim());
        values.put(COLUMN_SUPPIER_EMAILID,etEmailId.getText().toString().trim());
        values.put(COLUMN_SUPPIER_NAME_CLIENT, etGSTIN.getText().toString().trim());
        values.put(COLUMN_SUPPIER_MOBILENO,etMobileNo.getText().toString().trim());
        values.put(COLUMN_SUPPIER_PINCODE,etPincode.getText().toString().trim());
        values.put(COLUMN_SUPPIER_STATE,etState.getText().toString().trim());
        values.put(COLUMN_SUPPIER_PLACE,etPlace.getText().toString().trim());

        db1.update(TABLE_SUPPLIER, values, selection, selectionArgs);

        db.close();
    }

    /*---------------------API Call-----------------------------*/
    private void initAPIResources(String gstin, String name, String email, String mobileno, String usertype, String place
            , String pincode, String state,String userid){
        UserSupplierRegistrationPresenterImpl userRegistrationPresenter = new UserSupplierRegistrationPresenterImpl(SupplierActivity.this);
        userRegistrationPresenter.callApi(AppConstants.SUPPLIER_REGISTRATION,gstin,name,email,mobileno,usertype,place,pincode,state,userid);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:

                if (TextUtils.isEmpty(etGSTIN.getText().toString()))
                {
                    Snackbar.make(relativeLayout, getString(R.string.EnterGstinNO), Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(etName.getText().toString()))
                {
                    Snackbar.make(relativeLayout, getString(R.string.Entername), Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(etMobileNo.getText().toString()))
                {
                    Snackbar.make(relativeLayout, getString(R.string.EnterMobileNo), Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(etEmailId.getText().toString()))
                {
                    Snackbar.make(relativeLayout, getString(R.string.EnterEmailAddress), Snackbar.LENGTH_LONG).show();
                }
                else
                if (!etEmailId.getText().toString().matches(emailPattern) && !TextUtils.isEmpty(etEmailId.getText().toString()))
                {

                    Snackbar.make(relativeLayout, getString(R.string.EntervalidEmailAddress), Snackbar.LENGTH_LONG).show();
                }

                else {
                    postDataToSQLite();
                }
                //finish();;
                // btnSave.setEnabled(false);
                break;
          /*  case R.id.btnNext:
              //  if(userType.equalsIgnoreCase("Supplier")) {
                    Intent i = new Intent(SupplierActivity.this, EwayBillRegistration.class);
                    i.putExtra("TYPE", userType);
                    startActivity(i);
               // }
                break;*/

        }
    }



    @Override
    public void onUserSupplierRegistrationSuccess(UserSupplierRegistrationResponse userSupplierRegistrationresponse) {
        Toast.makeText(SupplierActivity.this,userSupplierRegistrationresponse.getMsg(),Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent();
        // intent.putExtra("supplierList", (Parcelable)userSupplierRegistrationresponse);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onUserSupplierRegistrationFailure(String msg) {
        Toast.makeText(SupplierActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}

