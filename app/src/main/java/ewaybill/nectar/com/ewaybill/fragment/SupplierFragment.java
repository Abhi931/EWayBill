package ewaybill.nectar.com.ewaybill.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.model.Client;
import ewaybill.nectar.com.ewaybill.sql.DatabaseHelper;

/**
 * Created by Abhishek on 4/10/2018.
 */

public class SupplierFragment extends Fragment implements View.OnClickListener {

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.supplier_registration_layout, container, false);

        relativeLayout = (RelativeLayout) rootView.findViewById(R.id.relativeLayout);
        unRegisteredLayout=(LinearLayout)rootView.findViewById(R.id.unRegisteredLayout);
        registeredLayout=(RelativeLayout)rootView.findViewById(R.id.registeredLayout);
        unRegisteredLayout.setVisibility(View.VISIBLE);
        btnUnRegisteredDetails=(Button)rootView.findViewById(R.id.btnUnRegisteredDetails);
        etGSTIN=(EditText)rootView.findViewById(R.id.etGSTIN);
        etName=(EditText)rootView.findViewById(R.id.etName);
        etMobileNo=(EditText)rootView.findViewById(R.id.etMobileNo);
        etEmailId=(EditText)rootView.findViewById(R.id.etEmailId);
        etPlace=(EditText)rootView.findViewById(R.id.etPlace);
        etPincode=(EditText)rootView.findViewById(R.id.etPincode);
        etState=(EditText)rootView.findViewById(R.id.etState);

        btnSave=(Button)rootView.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnClear=(Button)rootView.findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnUnRegisteredDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unRegisteredLayout.setVisibility(View.VISIBLE);
                registeredLayout.setVisibility(View.GONE);
            }
        });


        btnRegisteredDetails=(Button)rootView.findViewById(R.id.btnRegisteredDetails);
        btnRegisteredDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unRegisteredLayout.setVisibility(View.GONE);
                registeredLayout.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }

    private void postDataToSQLite() {

        //   if (!databaseHelper.checkUser(etGSTIN.getText().toString().trim())) {
        Client client=new Client();
        databaseHelper = new DatabaseHelper(getActivity());

        if (!(etGSTIN.getText().toString().trim().isEmpty() )) {
            client.setGstin(etGSTIN.getText().toString().trim());
            client.setName(etName.getText().toString().trim());
            client.setMobileno(etMobileNo.getText().toString().trim());
            client.setEmail(etEmailId.getText().toString().trim());
            client.setPlace(etPlace.getText().toString().trim());
            client.setPincode(etPincode.getText().toString().trim());
            client.setState(etState.getText().toString().trim());

            databaseHelper.addSupplier(client);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(registeredLayout, getString(R.string.supplier_success_message), Snackbar.LENGTH_LONG).show();
            //  emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(registeredLayout, getString(R.string.supplier_failure_message), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                postDataToSQLite();
                break;



        }
    }
}

