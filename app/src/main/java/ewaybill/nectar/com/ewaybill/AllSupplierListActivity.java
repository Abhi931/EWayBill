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
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserSupplierRegistrationResponse;
import ewaybill.nectar.com.ewaybill.localDB.Constants;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.GetAllSuppliersPresenterImpl;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.MyDividerItemDecoration;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.SupplierView;

public class AllSupplierListActivity extends Activity implements SupplierView {

    private ImageButton btnSupplierAdd;
    private String loginUserId;

    // @BindView(R.id.supplierRecyclerView)
    RecyclerView mSupplierRecyclerView;
    private ArrayList<SupplierData> mSupplierList;
    private AllSuppliersAdapter mSuppliersAdapter;
    private ConstraintLayout mainLayout;
    private ImageButton homebtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_supplier_list_activity);
        // ButterKnife.bind(this);

        Intent i=getIntent();
      //  loginUserId= i.getStringExtra("USERID");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        loginUserId=pref.getString("userid", null);


        mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        btnSupplierAdd=(ImageButton)findViewById(R.id.btnSupplierAdd);
        mSupplierRecyclerView=(RecyclerView)findViewById(R.id.supplierRecyclerView);

        homebtn=(ImageButton)findViewById(R.id.homeBtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (AllSupplierListActivity.this,EwayBillRegistration.class);
                startActivity(intent);
            }
        });

        btnSupplierAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllSupplierListActivity.this,SupplierActivity.class);
                intent.putExtra("USERID",loginUserId);
                startActivityForResult(intent, AppConstants.REQUEST_ADD_SUPPLIER_CODE);
            }
        });

        initXMLResources();
        getSupplierList();
    }

    private void initXMLResources(){
        mSupplierList  = new ArrayList<SupplierData>();
        mSupplierRecyclerView.setHasFixedSize(true);
        mSupplierRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSupplierRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSupplierRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        mSuppliersAdapter = new AllSuppliersAdapter(this,mSupplierList);
        mSupplierRecyclerView.setAdapter(mSuppliersAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == AppConstants.REQUEST_ADD_SUPPLIER_CODE){
            if(resultCode == RESULT_OK){
                if (data != null){
                    getSupplierList();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*------------------------ API Resources-----------------------------*/
    private void initGetSupplierAPIResources(String userid) {
        GetAllSuppliersPresenterImpl getAllSuppliersPresenter = new GetAllSuppliersPresenterImpl(this);
        getAllSuppliersPresenter.callApi(AppConstants.SUPPLIER_LIST, userid);

    }

    private void getSupplierList() {
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
        getSupplierList();
    }

    @Override
    public void onGetSupplierListSuccess(SupplierResponse supplierResponse) {
        try {
            if (mSupplierList == null) {
                mSupplierList = new ArrayList<>();
            }
            mSupplierList.clear();
            mSupplierList.addAll(supplierResponse.getSupplierData());
            mSuppliersAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.d("TAG",e.getMessage());}
    }

    @Override
    public void onGetSupplierListFailure(String msg) {
        Snackbar.make(mainLayout, "No Record Found", Snackbar.LENGTH_LONG).show();
    }
}
