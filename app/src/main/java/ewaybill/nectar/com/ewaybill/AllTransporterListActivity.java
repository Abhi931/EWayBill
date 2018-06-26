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

import ewaybill.nectar.com.ewaybill.adapter.AllSuppliersAdapter;
import ewaybill.nectar.com.ewaybill.adapter.AllTransportersAdapter;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.transporter.TransporterData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.transporter.TransporterResponse;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.GetAllSuppliersPresenterImpl;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.GetAllTransportersPresenterImpl;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.MyDividerItemDecoration;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.SupplierView;
import ewaybill.nectar.com.ewaybill.viewstate.TransporterView;

public class AllTransporterListActivity extends Activity implements TransporterView,AllTransportersAdapter.OnItemClickListener {

    private ImageButton btnTransporterAdd;
    private String loginUserId;
    private RecyclerView mtransporterRecyclerView;
    private ConstraintLayout mainLayout;
    private ArrayList<TransporterData> mTransporterList;
    private AllTransportersAdapter mTransportersAdapter;
    private ImageButton homebtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_transporter_list_activity);

        Intent i=getIntent();
     //   loginUserId= i.getStringExtra("USERID");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        loginUserId=pref.getString("userid", null);


        mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        mtransporterRecyclerView=(RecyclerView)findViewById(R.id.transporterRecyclerView);

        homebtn=(ImageButton)findViewById(R.id.homeBtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (AllTransporterListActivity.this,EwayBillRegistration.class);
                startActivity(intent);
            }
        });

        btnTransporterAdd = (ImageButton) findViewById(R.id.btnTransporterAdd);
        btnTransporterAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTransporterListActivity.this, TransporterActivity.class);
                intent.putExtra("USERID",loginUserId);
                startActivity(intent);
            }
        });
        initXMLResources();
        getTransporterList();
    }
    private void initXMLResources(){
        mTransporterList  = new ArrayList<TransporterData>();
        mtransporterRecyclerView.setHasFixedSize(true);
        mtransporterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mtransporterRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mtransporterRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        mTransportersAdapter = new AllTransportersAdapter(this,mTransporterList);
        mtransporterRecyclerView.setAdapter(mTransportersAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == AppConstants.REQUEST_ADD_SUPPLIER_CODE){
            if(resultCode == RESULT_OK){
                if (data != null){
                    getTransporterList();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*------------------------ API Resources-----------------------------*/
    private void initGetTransportersAPIResources(String userid) {
        GetAllTransportersPresenterImpl getAllTransportersPresenter = new GetAllTransportersPresenterImpl(this);
        getAllTransportersPresenter.callApi(AppConstants.TRANSPORTER_LIST, userid);

    }

    private void getTransporterList() {
        if (!TextUtils.isEmpty(loginUserId) && NetworkUtil.isOnline(this)){
            initGetTransportersAPIResources(loginUserId);

        }else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(mainLayout, "Please Check Internet Connection", Snackbar.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        initXMLResources();
        getTransporterList();
    }


    @Override
    public void onGetTransporterListSuccess(TransporterResponse transporterResponse) {
        try {
            if (mTransporterList == null) {
                mTransporterList = new ArrayList<>();
            }
            mTransporterList.clear();
            mTransporterList.addAll(transporterResponse.getTransporterData());
            mTransportersAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.d("TAG",e.getMessage());}
    }

    @Override
    public void onGetTransporterListFailure(String msg) {
        Snackbar.make(mainLayout, "No Record Found", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(TransporterData transporterData) {
        if(transporterData != null){
            Intent intent = new Intent();
            intent.putExtra("data",transporterData);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
