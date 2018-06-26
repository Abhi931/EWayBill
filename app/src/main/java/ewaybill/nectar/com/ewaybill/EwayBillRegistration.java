package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ewaybill.nectar.com.ewaybill.adapter.AllInvoicProcessListAdapter;
import ewaybill.nectar.com.ewaybill.adapter.DownladEwayProcessListAdapter;

/**
 * Created by Abhishek on 4/13/2018.
 */

public class EwayBillRegistration extends Activity implements View.OnClickListener {

    private Button btnClientRegistration;
    private Button btnSupplierRegistration;
    private Button btnTransporterRegistration;
    private Intent intent;
    private Button btnProduct;
    private Button btnGenerateEwayBill;
    private String userIdValue,username,email,gstinno;
    private LinearLayout layoutSupplierRegistration;
    private LinearLayout layoutClientRegistration;
    private LinearLayout layoutTransporterRegistration;
    private TextView userNametxt;
    private TextView emailIdtxt;
    private TextView mobilenotxt;
    private TextView gstintxt;
    private Button dashboardButton;
    private Button MasterDataButton;
    private Button paymentButton;
    private Button supportButton;
    private Button buyerRegistrationButton;
    private Button supplierRegistrationButton;
    private Button transporterRegistrationButton;
    private Button ewaystatusButton;
    private Button ewayFinalStatusButton;
    private String emailIdValue;
    private String gstinValue;
    private String userNameValue;
    private Button invoiceUploadButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_summary_layout);

        initView();
        initListener();

        intent=getIntent();
        String userTypeValue= intent.getStringExtra("USERTYPE");
     //   userIdValue= intent.getStringExtra("USERID");
        userNameValue= intent.getStringExtra("NAME");
        emailIdValue= intent.getStringExtra("EMAIL");
        gstinValue= intent.getStringExtra("GSTIN");


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
         username=pref.getString("name", null);
         email=pref.getString("emailid", null);
         gstinno=pref.getString("gstin", null);
        userIdValue=pref.getString("userid", null);



      /*  btnClientRegistration=(Button)findViewById(R.id.btnClientRegistration);
        btnClientRegistration.setOnClickListener(this);

        btnSupplierRegistration=(Button)findViewById(R.id.btnSupplierRegistration);
        btnSupplierRegistration.setOnClickListener(this);

        btnTransporterRegistration=(Button)findViewById(R.id.btnTransporterRegistration);
        btnTransporterRegistration.setOnClickListener(this);

        layoutClientRegistration=(LinearLayout)findViewById(R.id.layoutClientRegistration);
        layoutSupplierRegistration=(LinearLayout)findViewById(R.id.layoutSupplierRegistration);
        layoutTransporterRegistration=(LinearLayout)findViewById(R.id.layoutTransporterRegistration);

        *//*
        btnProduct=(Button)findViewById(R.id.btnProduct);
        btnProduct.setOnClickListener(this);*//*

        btnGenerateEwayBill=(Button)findViewById(R.id.btnGenerateEwayBill);
        btnGenerateEwayBill.setOnClickListener(this);

       // btnProduct.setEnabled(true);

        intent=getIntent();
       String userTypeValue= intent.getStringExtra("USERTYPE");
        userIdValue= intent.getStringExtra("USERID");
        if(userTypeValue.equalsIgnoreCase("Client")){
            //btnClientRegistration.setEnabled(false);
            layoutClientRegistration.setVisibility(View.GONE);
            layoutSupplierRegistration.setVisibility(View.VISIBLE);
            layoutTransporterRegistration.setVisibility(View.VISIBLE);
        }else if (userTypeValue.equalsIgnoreCase("Supplier")){
            layoutSupplierRegistration.setVisibility(View.GONE);
            layoutClientRegistration.setVisibility(View.VISIBLE);
            layoutTransporterRegistration.setVisibility(View.VISIBLE);
        }else if(userTypeValue.equalsIgnoreCase("Transporter")){
            layoutTransporterRegistration.setVisibility(View.GONE);
            layoutClientRegistration.setVisibility(View.VISIBLE);
            layoutSupplierRegistration.setVisibility(View.VISIBLE);
        }else if (!btnClientRegistration.isEnabled() && !btnSupplierRegistration.isEnabled()
                && !btnTransporterRegistration.isEnabled()){
           // btnProduct.setEnabled(true);
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnClientRegistration:
                intent=new Intent(EwayBillRegistration.this,AllClientListActivity.class );
                intent.putExtra("USERID",userIdValue);
                startActivity(intent);
                break;

            case R.id.btnSupplierRegistration:
                intent=new Intent(EwayBillRegistration.this,AllSupplierListActivity.class );
                intent.putExtra("USERID",userIdValue);
                startActivity(intent);
                break;

            case R.id.btnTransporterRegistration:
                 intent=new Intent(EwayBillRegistration.this,AllTransporterListActivity.class);
                intent.putExtra("USERID",userIdValue);
                startActivity(intent);

                break;

           *//* case R.id.btnProduct:
                intent=new Intent(EwayBillRegistration.this, ProductListActivity.class);

                startActivity(intent);
                break;*//*

            case R.id.btnGenerateEwayBill:
                intent=new Intent(EwayBillRegistration.this, EWayBillGenerationOptionActivity.class);
                startActivity(intent);
                break;


        }*/

      /*  userNametxt.setText("Name : " + userNameValue);
        emailIdtxt.setText("Email Id : " + emailIdValue);
        gstintxt.setText("GSTIN : " + gstinValue);*/


        userNametxt.setText("Name : " + username);
        emailIdtxt.setText("Email Id : " + email);
        gstintxt.setText("GSTIN : " + gstinno);

    }


    private void initView()
    {

        userNametxt=(TextView)findViewById(R.id.userNametxt);
        emailIdtxt=(TextView)findViewById(R.id.emailIdtxt);
       // mobilenotxt=(TextView)findViewById(R.id.mobilenotxt);
        gstintxt=(TextView)findViewById(R.id.gstintxt);
        dashboardButton=(Button)findViewById(R.id.dashboardButton);
        paymentButton=(Button)findViewById(R.id.paymentButton);
        supportButton=(Button)findViewById(R.id.supportButton);
        buyerRegistrationButton=(Button) findViewById(R.id.buyerRegistrationButton);
        supplierRegistrationButton=(Button) findViewById(R.id.supplierRegistrationButton);
        transporterRegistrationButton=(Button) findViewById(R.id.transporterRegistrationButton);
        ewaystatusButton=(Button) findViewById(R.id.ewaystatusButton);
        ewayFinalStatusButton=(Button) findViewById(R.id.ewayFinalStatusButton);
        invoiceUploadButton=(Button) findViewById(R.id.invoiceUploadButton);
    }

    private void initListener(){

        dashboardButton.setOnClickListener(this);
       // MasterDataButton.setOnClickListener(this);
        paymentButton.setOnClickListener(this);
        buyerRegistrationButton.setOnClickListener(this);
        supplierRegistrationButton.setOnClickListener(this);
        transporterRegistrationButton.setOnClickListener(this);
        supportButton.setOnClickListener(this);
        supportButton.setOnClickListener(this);
        ewaystatusButton.setOnClickListener(this);
        ewayFinalStatusButton.setOnClickListener(this);
        invoiceUploadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.dashboardButton:
            Intent i =new Intent(EwayBillRegistration.this,SummaryDashboardActivity.class);
            startActivity(i);
                break;
            case R.id.paymentButton:
                Intent pIntent=new Intent(EwayBillRegistration.this,PaymentActivity.class );
             //   intent.putExtra("USERID",userIdValue);
                startActivity(pIntent);

                break;
            case R.id.buyerRegistrationButton:
                Intent intent=new Intent(EwayBillRegistration.this,AllClientListActivity.class );
                intent.putExtra("USERID",userIdValue);
                startActivity(intent);
                break;
            case R.id.supplierRegistrationButton:
               Intent i1=new Intent(EwayBillRegistration.this,AllSupplierListActivity.class );
                i1.putExtra("USERID",userIdValue);
                startActivity(i1);
                break;
            case R.id.transporterRegistrationButton:
               Intent i3=new Intent(EwayBillRegistration.this,AllTransporterListActivity.class);
                i3.putExtra("USERID",userIdValue);
                startActivity(i3);
                break;
            case R.id.supportButton:
               /* Intent i4=new Intent(EwayBillRegistration.this,InvoiceEWayBillActivity.class);
                i4.putExtra("USERID",userIdValue);
                startActivity(i4);*/
                break;
            case R.id.ewaystatusButton:
                Intent statusIntent=new Intent(EwayBillRegistration.this,EwayProcessInvoiceListActivity.class);
                statusIntent.putExtra("USERID",userIdValue);
                startActivity(statusIntent);
                break;
            case R.id.ewayFinalStatusButton:
                Intent finalStausIntent=new Intent(EwayBillRegistration.this,EwayDownloadShareListActivity.class);
                finalStausIntent.putExtra("USERID",userIdValue);
                startActivity(finalStausIntent);
                break;
            case R.id.invoiceUploadButton:
                Intent invoiceUploadIntent=new Intent(EwayBillRegistration.this,InvoiceEWayBillActivity.class);
                invoiceUploadIntent.putExtra("USERID",userIdValue);
                startActivity(invoiceUploadIntent);
                break;
        }
    }
}
