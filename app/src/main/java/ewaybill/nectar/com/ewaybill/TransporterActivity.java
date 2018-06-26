package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserClientRegistrationResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserTransporterRegistrationResponse;
import ewaybill.nectar.com.ewaybill.model.Supplier;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.UserClientRegistrationPresenterImpl;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.UserSupplierRegistrationPresenterImpl;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.UserTransporterRegistrationPresenterImpl;
import ewaybill.nectar.com.ewaybill.sql.DatabaseHelper;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.UserClientRegistrationView;
import ewaybill.nectar.com.ewaybill.viewstate.UserTransporterRegistrationView;


/**
 * Created by Abhishek on 4/12/2018.
 */

public class TransporterActivity  extends Activity implements View.OnClickListener,UserTransporterRegistrationView {


    LinearLayout basicDetailsTranporterView;
    LinearLayout addressTranporterView;
    LinearLayout loginTranporterView;
    Button btnBasicDetails;
    Button btnAddressDetails;
    Button btnLoginDetails;
    private EditText etTransporterId;
    private EditText etTransporterName;
    private EditText etTransporterAddress;
    private EditText etTransporterEmailId;
    private EditText etTransporterPlace;
    private EditText etTransporterState;
    private EditText etTransporterPincode;
    private EditText etTransportercontactNo;
    private Button btnTransporterCancel;
    private Button btnTransporterRegister;
    private DatabaseHelper databaseHelper;
    private RelativeLayout registeredLayout;
    private String nameU;
    private String emailU;
    private String userType;
    private String gstnU;
    private Button btnUpdate;
    private static final String TABLE_TRANSPORTER = "transporter_details";
    private static final String COLUMN_TRANS_ID = "transporter_id";
    private static final String COLUMN_ID_TRANSPORTER = "idTrans";
    private static final String COLUMN_TRANSPORTER_ADDRESS = "transporter_address";
    private static final String COLUMN_TRANSPORTER_EMAILID = "transporter_emailid";
    private static final String COLUMN_TRANSPORTER_NAME = "transporter_name";
    private static final String COLUMN_TRANSPORTER_MOBILENO = "transporter_mobileno";
    private static final String COLUMN_TRANSPORTER_PINCODE = "transporter_pincode";
    private static final String COLUMN_TRANSPORTER_STATE = "transporter_state";
    private static final String COLUMN_TRANSPORTER_PLACE = "transporter_place";
    private Button btnNext;
    private EditText userTypeEditText;
    private String loginUserId;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter);


        etTransporterId = (EditText) findViewById(R.id.etTransporterId);
        etTransporterName = (EditText) findViewById(R.id.etTransporterName);
        etTransportercontactNo = (EditText) findViewById(R.id.etTransportercontactNo);
        etTransporterEmailId = (EditText) findViewById(R.id.etTransporterEmailId);
        etTransporterAddress = (EditText) findViewById(R.id.etTransporterAddress);
        etTransporterPlace = (EditText) findViewById(R.id.etTransporterPlace);
        etTransporterPincode = (EditText) findViewById(R.id.etTransporterPincode);
        etTransporterState = (EditText) findViewById(R.id.etTransporterState);
        userTypeEditText=(EditText)findViewById(R.id.userTypeEditText);

        Intent i=getIntent();
       // loginUserId= i.getStringExtra("USERID");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        loginUserId=pref.getString("userid", null);
       /* Intent intent=getIntent();

        nameU= intent.getStringExtra("NAME");
        emailU= intent.getStringExtra("EMAIL");
        userType= intent.getStringExtra("USERTYPE");
        gstnU= intent.getStringExtra("GSTIN");
        etTransporterName.setText(nameU);
        etTransporterEmailId.setText(emailU);
        etTransporterId.setText(gstnU);*/

        registeredLayout = (RelativeLayout) findViewById(R.id.registeredLayout);

        btnTransporterRegister = (Button) findViewById(R.id.btnTransporterRegister);
        btnTransporterRegister.setOnClickListener(this);

     /*   btnTransporterCancel = (Button) findViewById(R.id.btnTransporterCancel);
        btnTransporterCancel.setOnClickListener(this);*/
      /*  btnNext=(Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trnsporterPostDataToSQLite();
                btnTransporterRegister.setEnabled(false);


              //  updateUser(etTransporterName.getText().toString().trim(),etTransporterId.getText().toString().trim());
              //  Snackbar.make(registeredLayout, getString(R.string.transporter_success_update_message), Snackbar.LENGTH_LONG).show();
            }
        });

        btnUpdate.setVisibility(View.GONE);
        btnTransporterRegister.setVisibility(View.VISIBLE);

        if(!(etTransporterId.getText().toString().trim()).isEmpty()){
            btnTransporterRegister.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
        }*/


/*
        basicDetailsTranporterView=(LinearLayout)rootView.findViewById(R.id.basicDetailsTranporterView);
        basicDetailsTranporterView.setOnClickListener(this);
        basicDetailsTranporterView.setOnTouchListener(this);


        addressTranporterView=(LinearLayout)rootView.findViewById(R.id.addressTranporterView);
        addressTranporterView.setOnClickListener(this);
        addressTranporterView.setOnTouchListener(this);

        loginTranporterView=(LinearLayout)rootView.findViewById(R.id.loginTranporterView);
        loginTranporterView.setOnClickListener(this);
        loginTranporterView.setOnTouchListener(this);

        btnBasicDetails=(Button) rootView.findViewById(R.id.btnBasicDetails);
        btnBasicDetails.setOnClickListener(this);
        btnBasicDetails.setOnTouchListener(this);

        btnAddressDetails=(Button)rootView.findViewById(R.id.btnAddressDetails);
        btnAddressDetails.setOnClickListener(this);
        btnAddressDetails.setOnTouchListener(this);

        btnLoginDetails=(Button)rootView.findViewById(R.id.btnLoginDetails);
        btnLoginDetails.setOnClickListener(this);
        btnLoginDetails.setOnTouchListener(this);
        basicDetailsTranporterView.setVisibility(View.VISIBLE);
*/

    }

    /*---------------------API Call-----------------------------*/
    private void initAPIResources(String gstin, String name, String email, String mobileno, String usertype, String place
            , String pincode, String state,String userid){
        UserTransporterRegistrationPresenterImpl userRegistrationPresenter = new UserTransporterRegistrationPresenterImpl(TransporterActivity.this);
        userRegistrationPresenter.callApi(AppConstants.TRANSPORTER_REGISTRATION,gstin,name,email,mobileno,usertype,place,pincode,state,userid);
    }

    /*------------------------ API Resources-----------------------------*/
    private void initGetTransporterAPIResources(String userid) {
        UserTransporterRegistrationPresenterImpl userRegistrationPresenter = new UserTransporterRegistrationPresenterImpl(TransporterActivity.this);
        userRegistrationPresenter.callApi(AppConstants.CLIENT_LIST, userid);

    }

    private void getTransporterList() {
        if (!TextUtils.isEmpty(loginUserId)){
            initGetTransporterAPIResources(loginUserId);

        }else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(registeredLayout, "Please Check Internet Connection", Snackbar.LENGTH_LONG).show();
        }
    }




    public void updateUser(String name, String gstin) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_TRANS_ID,
                COLUMN_ID_TRANSPORTER,
                COLUMN_TRANSPORTER_ADDRESS,
                COLUMN_TRANSPORTER_EMAILID,
                COLUMN_TRANSPORTER_NAME,
                COLUMN_TRANSPORTER_MOBILENO,
                COLUMN_TRANSPORTER_PINCODE,
                COLUMN_TRANSPORTER_STATE,
                COLUMN_TRANSPORTER_PLACE
        };
        DatabaseHelper db=new DatabaseHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_TRANSPORTER_NAME + " = ?" + " OR " + COLUMN_ID_TRANSPORTER + " = ?";

        // selection arguments
        String[] selectionArgs = {name, gstin};

        // SQLiteDatabase db1 = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_TRANSPORTER, etTransporterId.getText().toString().trim());
        values.put(COLUMN_TRANSPORTER_EMAILID,etTransporterEmailId.getText().toString().trim());
        values.put(COLUMN_TRANSPORTER_NAME, etTransporterName.getText().toString().trim());
        values.put(COLUMN_TRANSPORTER_MOBILENO,etTransportercontactNo.getText().toString().trim());
        values.put(COLUMN_TRANSPORTER_PINCODE,etTransporterPincode.getText().toString().trim());
        values.put(COLUMN_TRANSPORTER_STATE,etTransporterState.getText().toString().trim());
        values.put(COLUMN_TRANSPORTER_PLACE,etTransporterPlace.getText().toString().trim());

        db1.update(TABLE_TRANSPORTER, values, selection, selectionArgs);

        db.close();
    }

    private void trnsporterPostDataToSQLite() {

            if (NetworkUtil.isOnline(TransporterActivity.this)) {

                String gstin = etTransporterId.getText().toString().trim();
                String name = etTransporterName.getText().toString().trim();
                String email = etTransporterEmailId.getText().toString().trim();
                String mobileno = etTransportercontactNo.getText().toString().trim();
                String usertype = userTypeEditText.getText().toString().trim();
                String place = etTransporterPlace.getText().toString().trim();
                String pincode = etTransporterPincode.getText().toString().trim();
                String state = etTransporterState.getText().toString().trim();
                String userid = loginUserId;

                initAPIResources(gstin, name, email, mobileno, usertype, place, pincode, state, userid);
                //  Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();

            } else {
                databaseHelper = new DatabaseHelper(this);
                if (!databaseHelper.checkTransporter(etTransporterName.getText().toString().trim(),
                        etTransporterId.getText().toString().trim())) {
                    Supplier transporter = new Supplier();

                    //  if (!(etTransporterId.getText().toString().trim().isEmpty())) {
                    transporter.setTransporterId(etTransporterId.getText().toString().trim());
                    transporter.setTransporterName(etTransporterName.getText().toString().trim());
                    transporter.setTransporterAddress(etTransporterAddress.getText().toString().trim());
                    transporter.setTransporterEmail(etTransporterEmailId.getText().toString().trim());
                    transporter.setTransporterMobileno(etTransportercontactNo.getText().toString().trim());
                    transporter.setTransporterPincode(etTransporterPincode.getText().toString().trim());
                    transporter.setTransporterPlace(etTransporterPlace.getText().toString().trim());
                    transporter.setTransporterState(etTransporterState.getText().toString().trim());

                    databaseHelper.addTransporter(transporter);

                    // Snack Bar to show success message that record saved successfully
                    Snackbar.make(registeredLayout, getString(R.string.transporter_success_message), Snackbar.LENGTH_LONG).show();
                    //  emptyInputEditText();


                } else {
                    // Snack Bar to show error message that record already exists
                    Snackbar.make(registeredLayout, getString(R.string.account_already_exists), Snackbar.LENGTH_LONG).show();
                }
            }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTransporterRegister:

                if (TextUtils.isEmpty(etTransporterId.getText().toString()))
                {
                    Snackbar.make(registeredLayout, getString(R.string.EnterGstinNO), Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(etTransporterName.getText().toString()))
                {
                    Snackbar.make(registeredLayout, getString(R.string.Entername), Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(etTransportercontactNo.getText().toString()))
                {
                    Snackbar.make(registeredLayout, getString(R.string.EnterMobileNo), Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(etTransporterEmailId.getText().toString()))
                {
                    Snackbar.make(registeredLayout, getString(R.string.EnterEmailAddress), Snackbar.LENGTH_LONG).show();
                }
                else
                if (!etTransporterEmailId.getText().toString().matches(emailPattern) && !TextUtils.isEmpty(etTransporterEmailId.getText().toString()))
                {

                    Snackbar.make(registeredLayout, getString(R.string.EntervalidEmailAddress), Snackbar.LENGTH_LONG).show();
                }

                else {
                    trnsporterPostDataToSQLite();
                    finish();
                }
              //  btnTransporterRegister.setEnabled(false);
                break;
           /* case R.id.btnNext:
                Intent i = new Intent(TransporterActivity.this,EwayBillRegistration.class);
               // i.putExtra("TYPE", userType);
                startActivity(i);
                break;*/
        }

/*
        if( v == btnBasicDetails){
            basicDetailsTranporterView.setVisibility(View.VISIBLE);
            addressTranporterView.setVisibility(View.GONE);
            loginTranporterView.setVisibility(View.GONE);

        }else if( v == btnAddressDetails  ){
            addressTranporterView.setVisibility(View.VISIBLE);
            basicDetailsTranporterView.setVisibility(View.GONE);
            loginTranporterView.setVisibility(View.GONE);

        } else if( v == btnLoginDetails ){
            loginTranporterView.setVisibility(View.VISIBLE);
            basicDetailsTranporterView.setVisibility(View.GONE);
            addressTranporterView.setVisibility(View.GONE);
        }*/

    }

    @Override
    public void onUserTransportRegistrationSuccess(UserTransporterRegistrationResponse userTransporterRegistrationresponse) {
        Toast.makeText(TransporterActivity.this,userTransporterRegistrationresponse.getMsg(),Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onUserTransporterRegistrationFailure(String msg) {

    }
}