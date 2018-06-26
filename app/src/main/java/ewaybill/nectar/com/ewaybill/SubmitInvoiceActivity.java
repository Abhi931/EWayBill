package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RecoverySystem;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;

import ewaybill.nectar.com.ewaybill.adapter.AllTransportersAdapter;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.transporter.TransporterData;
import ewaybill.nectar.com.ewaybill.retrofit.CallbackWithRetry;
import ewaybill.nectar.com.ewaybill.utils.MyDividerItemDecoration;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitInvoiceActivity extends Activity implements ProgressRequestBody.UploadCallbacks {
    private static final String TAG = SubmitInvoiceActivity.class.getSimpleName();
    private static final int REQUEST_ALL_TRANSPORT_LIST = 001;
    private File mFile;
    private Button mSelectTransporterButton;
    private ArrayList<TransporterData> mTransporterList;
    private RecyclerView mtransporterRecyclerView;
    private AllTransportersAdapter mTransportersAdapter;
    private Button mSubmitButton;
    private LinearLayout constraintLayout;
    private TransporterData mTransporterData;
    private EditText mDurationEditText;
    private EditText mRemarkEditText;
    private ProgressBar loadingProgressBar;
    TextView txtPercentage;
    ProgressDialog dialog;
    String filename;
    boolean isupload=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transporter_invoice_details);
//  style="@style/Widget.AppCompat.ProgressBar"
        initXMLResources();
        mSelectTransporterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmitInvoiceActivity.this,AllTransporterListActivity.class);
                intent.putExtra("USERID",PrefManager.getActiveInstance(SubmitInvoiceActivity.this).getUserId()+"");
                startActivityForResult(intent,REQUEST_ALL_TRANSPORT_LIST);
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (int i=0;i<InvoiceEWayBillActivity.imagesEncodedList.size();i++) {
                    String filevalue=InvoiceEWayBillActivity.imagesEncodedList.get(i);
                    File file = new File(filevalue);
                    filename=file.getName();
                    Log.d("file111",filename);

                        uploadFileInMultipartForm(PrefManager.getActiveInstance(SubmitInvoiceActivity.this).getUserId(),
                                Integer.parseInt(mTransporterData.getTransporterId()),
                                mTransporterData.getTransporterName(),
                                mDurationEditText.getText().toString(),
                                mRemarkEditText.getText().toString(), file);

                }

            }
        });

    }

    private void initXMLResources(){
        constraintLayout=(LinearLayout)findViewById(R.id.constraintLayout);
        mSelectTransporterButton = (Button) findViewById(R.id.selectTransporterButton);
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mDurationEditText = (EditText) findViewById(R.id.durationEditText);
        mRemarkEditText = (EditText) findViewById(R.id.remarkEditText);
        mtransporterRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        txtPercentage=(TextView)findViewById(R.id.txtPercentage);
        loadingProgressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);
        mFile = (File) getIntent().getSerializableExtra("file");

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
        if(requestCode == REQUEST_ALL_TRANSPORT_LIST){
            if(resultCode == RESULT_OK){
                if(data != null){
                    mTransporterData = data.getParcelableExtra("data");
                    if(mTransporterList != null)
                        mTransporterList.clear();
                    mTransporterList.add(mTransporterData);
                    mTransportersAdapter.notifyDataSetChanged();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadFileInMultipartForm(int userID, int transporterID, String title, String duartion, String remark, File file) {
        try {
            //com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 1 path $
            if (loadingProgressBar != null) loadingProgressBar.setVisibility(View.VISIBLE);

            MultipartBody.Part fileToUpload = null;
             if (file != null) {
                 dialog = new ProgressDialog(SubmitInvoiceActivity.this, R.style.AppCompatAlertDialogStyle);
                 dialog.setMessage("Uloading file "+filename);
                 dialog.setIndeterminate(false);

                 dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                 dialog.setCancelable(false);

                 dialog.setMax(100);
                 dialog.show();
              /*  RequestBody mFile = RequestBody.create(MediaType.parse("image*//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//*"), file);
                fileToUpload = MultipartBody.Part.createFormData("uploaded_invoice_copy", file.getName(), mFile);*/

            /*   ProgressRequestBody fileBody = new ProgressRequestBody(file, this);
                fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), fileBody);*/



                 ProgressRequestBody fileBody = new ProgressRequestBody(file, this);
                 fileToUpload = MultipartBody.Part.createFormData("uploaded_invoice_copy", file.getName(), fileBody);

             }
            RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), !TextUtils.isEmpty(userID + "")
                    ? userID + "" : "");
            RequestBody transporterId = RequestBody.create(MediaType.parse("text/plain"), transporterID + "");
            RequestBody invoiceTitle = RequestBody.create(MediaType.parse("text/plain"), title + "");
            RequestBody duration = RequestBody.create(MediaType.parse("text/plain"), duartion + "");
            RequestBody invoiceRemark = RequestBody.create(MediaType.parse("text/plain"), remark + "");
//
            Log.d("filenname",file.getName()+userID+transporterID+title+duartion+remark);
            Call<JsonObject> call = EWayBillApplication.mRetroClient.callUploadInvoiceAPi(userId,
                    transporterId, invoiceTitle, duration, invoiceRemark, fileToUpload);


            call.enqueue(new CallbackWithRetry<JsonObject>(call) {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    dialog.dismiss();
                    if (loadingProgressBar != null) loadingProgressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {

                        Log.d(TAG, "onResponse: " + response.body());
                        Snackbar.make(constraintLayout, "File Uploaded Successfully", Snackbar.LENGTH_LONG).show();
                    } else {

                        Snackbar.make(constraintLayout, "Please Try Again", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    if (loadingProgressBar != null) loadingProgressBar.setVisibility(View.GONE);
                    Snackbar.make(constraintLayout, t.toString(), Snackbar.LENGTH_LONG).show();
                    Log.d(TAG, "toString(): " + t.toString());
                    dialog.dismiss();
                }
            });

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onProgressUpdate(int percentage) {
        dialog.setProgress(percentage);
        Log.d("progress",""+percentage);
        txtPercentage.setText(""+percentage+ " %");
    }

    @Override
    public void onError() {

    }

    @Override
    public void onFinish() {
        dialog.setProgress(100);
    }

   /* private void uploadMultiFile(int userID, int transporterID, String title, String duartion, String remark, File file) {


        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        builder.addFormDataPart("user_id", ""+userID);
        builder.addFormDataPart("transporter_id", ""+transporterID);
        builder.addFormDataPart("invoice_title", title);
        builder.addFormDataPart("e_way_duration", duartion);
        builder.addFormDataPart("remark", remark);


        // Map is used to multipart the file using okhttp3.RequestBody
        // Multiple Images
        for (int i = 0; i < InvoiceEWayBillActivity.imagesEncodedList.size(); i++) {
            File file11 = new File( InvoiceEWayBillActivity.imagesEncodedList.get(i));
            builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }

        File file11 = new File("");
        MultipartBody requestBody = builder.build();
        Call<ResponseBody> call = uploadMultiFile(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(MainActivity.this, "Success " + response.message(), Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Success " + response.body().toString(), Toast.LENGTH_LONG).show();



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, "Error " + t.getMessage());
            }
        });


    }*/}