package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.RelativeLayout;

import java.util.ArrayList;

import ewaybill.nectar.com.ewaybill.adapter.AllInvoicProcessListAdapter;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.Invoice.InvoiceData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.Invoice.InvoiceResponse;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.GetAllInvoicePresenterImpl;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.MyDividerItemDecoration;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.InvoiceView;

public class EwayProcessInvoiceListActivity extends Activity implements InvoiceView {

    private Button btnClientAdd;
    // private String loginUserId;
    private RecyclerView mallInvoicProcessListRecyclerView;
    private RelativeLayout mainLayout;
    private AllInvoicProcessListAdapter mAllInvoicProcessListAdapter;
    private ArrayList<InvoiceData> mInvoiceList;
    private ImageButton homebtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_invoice_process_list_activity);

        homebtn=(ImageButton)findViewById(R.id.homeBtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (EwayProcessInvoiceListActivity.this,EwayBillRegistration.class);
                startActivity(intent);
            }
        });
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        mallInvoicProcessListRecyclerView=(RecyclerView)findViewById(R.id.invoiceRecyclerView);
       /* Intent i=getIntent();
        loginUserId= i.getStringExtra("USERID");*/

       // btnClientAdd=(Button)findViewById(R.id.btnClientAdd);
      /*  btnClientAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EwayProcessInvoiceListActivity.this,ClientActivity.class);
                intent.putExtra("USERID",loginUserId);
                startActivity(intent);
            }
        });*/

        initXMLResources();
       getClientList(PrefManager.getActiveInstance(this).getUserId()+"");
    }
    private void initXMLResources(){
        mInvoiceList  = new ArrayList<InvoiceData>();
        mallInvoicProcessListRecyclerView.setHasFixedSize(true);
        mallInvoicProcessListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mallInvoicProcessListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mallInvoicProcessListRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        mAllInvoicProcessListAdapter = new AllInvoicProcessListAdapter(this,mInvoiceList);
        mallInvoicProcessListRecyclerView.setAdapter(mAllInvoicProcessListAdapter);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == AppConstants.REQUEST_ADD_SUPPLIER_CODE){
            if(resultCode == RESULT_OK){
                if (data != null){
                    getClientList();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/

    /*------------------------ API Resources-----------------------------*/
    private void initGetInvoiceAPIResources(String userid) {
        GetAllInvoicePresenterImpl getAllinvoicePresenter = new GetAllInvoicePresenterImpl(this);
        getAllinvoicePresenter.callApi(AppConstants.INVOICE_LIST, userid);

    }

    private void getClientList(String loginUserId) {
        //loginUserId = "3"; // Please comment this line before going live.
        if (!TextUtils.isEmpty(loginUserId) && NetworkUtil.isOnline(this)){
            initGetInvoiceAPIResources(loginUserId);

        }else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(mainLayout, "Please Check Internet Connection", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        initXMLResources();
        getClientList(PrefManager.getActiveInstance(this).getUserId() + "");
    }

    @Override
    public void onGetInvoiceListSuccess(InvoiceResponse invoiceResponse) {
        try {
            if (mInvoiceList == null) {
                mInvoiceList = new ArrayList<>();
            }
            mInvoiceList.clear();
            mInvoiceList.addAll(invoiceResponse.getInvoiceData());
            mAllInvoicProcessListAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.d("TAG",e.getMessage());}
    }

    @Override
    public void onGetInvoiceListFailure(String msg) {

    }
}
