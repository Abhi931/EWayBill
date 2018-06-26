package ewaybill.nectar.com.ewaybill;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import ewaybill.nectar.com.ewaybill.model.Client;
import ewaybill.nectar.com.ewaybill.model.Supplier;
import ewaybill.nectar.com.ewaybill.sql.DatabaseHelper;

/**
 * Created by Abhishek on 4/6/2018.
 */

public class TransportersFragment extends Fragment implements View.OnClickListener,View.OnTouchListener{
    private View rootView;
    private static final String TAG = TransportersFragment.class.getSimpleName();

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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_transporter, container, false);
        ButterKnife.bind(this, rootView);

        etTransporterId=(EditText)rootView.findViewById(R.id.etTransporterId);
        etTransporterName=(EditText)rootView.findViewById(R.id.etTransporterName);
        etTransportercontactNo=(EditText)rootView.findViewById(R.id.etTransportercontactNo);
        etTransporterEmailId=(EditText)rootView.findViewById(R.id.etTransporterEmailId);
        etTransporterAddress=(EditText)rootView.findViewById(R.id.etTransporterAddress);
        etTransporterPlace=(EditText)rootView.findViewById(R.id.etTransporterPlace);
        etTransporterPincode=(EditText)rootView.findViewById(R.id.etTransporterPincode);
        etTransporterState=(EditText)rootView.findViewById(R.id.etTransporterState);

        registeredLayout=(RelativeLayout)rootView.findViewById(R.id.registeredLayout);

        btnTransporterRegister=(Button)rootView.findViewById(R.id.btnTransporterRegister);
        btnTransporterRegister.setOnClickListener(this);

        btnTransporterCancel=(Button)rootView.findViewById(R.id.btnTransporterCancel);
        btnTransporterCancel.setOnClickListener(this);


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

        return rootView;
    }


    private void trnsporterPostDataToSQLite() {

        //   if (!databaseHelper.checkUser(etGSTIN.getText().toString().trim())) {

        Supplier transporter=new Supplier();
        databaseHelper = new DatabaseHelper(getActivity());

        if (!(etTransporterId.getText().toString().trim().isEmpty() )) {
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
            Snackbar.make(registeredLayout, getString(R.string.transporter_failure_message), Snackbar.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnTransporterRegister:
                trnsporterPostDataToSQLite();
                break;


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
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
