package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

import ewaybill.nectar.com.ewaybill.adapter.AllClientsAdapter;
import ewaybill.nectar.com.ewaybill.adapter.AllSuppliersAdapter;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.client.ClientData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.client.ClientResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierResponse;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.GetAllClientsPresenterImpl;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.GetAllSuppliersPresenterImpl;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.MyDividerItemDecoration;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.ClientView;
import ewaybill.nectar.com.ewaybill.viewstate.SupplierView;

public class AllClientListActivity extends Activity implements ClientView {

    private ImageButton btnClientAdd;
    private String loginUserId;
    private RecyclerView mclientRecyclerView;
    private ConstraintLayout mainLayout;
    private AllClientsAdapter mClientsAdapter;
    private ArrayList<ClientData> mClientList;
    private ImageButton homebtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_client_list_activity);

        homebtn=(ImageButton)findViewById(R.id.homeBtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (AllClientListActivity.this,EwayBillRegistration.class);
                startActivity(intent);
            }
        });
        mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        mclientRecyclerView=(RecyclerView)findViewById(R.id.clientRecyclerView);
        Intent i=getIntent();
     //   loginUserId= i.getStringExtra("USERID");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        loginUserId=pref.getString("userid", null);

        btnClientAdd=(ImageButton)findViewById(R.id.btnClientAdd);
        btnClientAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllClientListActivity.this,ClientActivity.class);
                intent.putExtra("USERID",loginUserId);
                startActivity(intent);
            }
        });

        initXMLResources();
        getClientList();
    }
    private void initXMLResources(){
        mClientList  = new ArrayList<ClientData>();
        mclientRecyclerView.setHasFixedSize(true);
        mclientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mclientRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mclientRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        mClientsAdapter = new AllClientsAdapter(this,mClientList);
        mclientRecyclerView.setAdapter(mClientsAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == AppConstants.REQUEST_ADD_SUPPLIER_CODE){
            if(resultCode == RESULT_OK){
                if (data != null){
                    getClientList();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*------------------------ API Resources-----------------------------*/
    private void initGetSupplierAPIResources(String userid) {
        GetAllClientsPresenterImpl getAllClientsPresenter = new GetAllClientsPresenterImpl(this);
        getAllClientsPresenter.callApi(AppConstants.CLIENT_LIST, userid);

    }

    private void getClientList() {
        if (!TextUtils.isEmpty(loginUserId) && NetworkUtil.isOnline(this)){
            initGetSupplierAPIResources(loginUserId);

        }else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(mainLayout, "Please Check Internet Connection", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        initXMLResources();
        getClientList();
    }

    @Override
    public void onGetClientListSuccess(ClientResponse clientResponse) {
        try {
            if (mClientList == null) {
                mClientList = new ArrayList<>();
            }
            mClientList.clear();
            mClientList.addAll(clientResponse.getClientData());
            mClientsAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.d("TAG",e.getMessage());}
    }

    @Override
    public void onGetClientListFailure(String msg) {

    }
}
