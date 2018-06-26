package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import ewaybill.nectar.com.ewaybill.adapter.DownladEwayProcessListAdapter;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.Invoice.InvoiceData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.Invoice.InvoiceResponse;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.GetAllInvoicePresenterImpl;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.DownloadManagerUtils;
import ewaybill.nectar.com.ewaybill.utils.MyDividerItemDecoration;
import ewaybill.nectar.com.ewaybill.utils.NetworkUtil;
import ewaybill.nectar.com.ewaybill.viewstate.InvoiceView;

public class EwayDownloadShareListActivity extends Activity implements InvoiceView {

    private static final String TAG = EwayDownloadShareListActivity.class.getSimpleName();
    private Button btnClientAdd;
    // private String loginUserId;
    private RecyclerView mallInvoicProcessListRecyclerView;
    private RelativeLayout mainLayout;
    private DownladEwayProcessListAdapter mDownladEwayProcessListAdapter;
    private ArrayList<InvoiceData> mInvoiceList;
    private ImageButton homebtn;
    public String loginUserId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_all_ewaybill);

        homebtn=(ImageButton)findViewById(R.id.homeBtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (EwayDownloadShareListActivity.this,EwayBillRegistration.class);
                startActivity(intent);
            }
        });
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        mallInvoicProcessListRecyclerView=(RecyclerView)findViewById(R.id.invoiceRecyclerView);
       /* Intent i=getIntent();
        loginUserId= i.getStringExtra("USERID");*/

        final String loginUserId= PrefManager.getActiveInstance(this).getUserId()+"";
        //btnClientAdd=(Button)findViewById(R.id.btnClientAdd);
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
        mDownladEwayProcessListAdapter = new DownladEwayProcessListAdapter(this,mInvoiceList);
        mallInvoicProcessListRecyclerView.setAdapter(mDownladEwayProcessListAdapter);
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

    private void getClientList(String userId) {
        //userId = "3"; // Please comment this line before going live.
        if (!TextUtils.isEmpty(userId) && NetworkUtil.isOnline(this)){
            initGetInvoiceAPIResources(userId);

        }else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(mainLayout, "Please Check Internet Connection", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (onDownloadFileCompleteReceiver != null) {
            registerReceiver(onDownloadFileCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
    }

    @Override
    public void onGetInvoiceListSuccess(InvoiceResponse invoiceResponse) {
        try {
            if (mInvoiceList == null) {
                mInvoiceList = new ArrayList<>();
            }
            mInvoiceList.clear();
            mInvoiceList.addAll(invoiceResponse.getInvoiceData());
            mDownladEwayProcessListAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.d("TAG",e.getMessage());}
    }

    @Override
    public void onGetInvoiceListFailure(String msg) {
        Snackbar.make(mainLayout, msg, Snackbar.LENGTH_LONG).show();
    }

    BroadcastReceiver onDownloadFileCompleteReceiver = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {
            try {
                long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

                if (DownloadManagerUtils.list != null) {
                    DownloadManagerUtils.list.remove(referenceId);
                    if (DownloadManagerUtils.list.isEmpty()) {
                        DownloadManagerUtils.openDownloadedAttachment(EwayDownloadShareListActivity.this, referenceId);
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (onDownloadFileCompleteReceiver != null) {
            unregisterReceiver(onDownloadFileCompleteReceiver);
        }
    }
}
