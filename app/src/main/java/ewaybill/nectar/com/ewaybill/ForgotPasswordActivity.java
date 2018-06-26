package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.forgot.ForgotResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.signup.SignUpResponse;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.ForgotPresenterImpl;

import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.viewstate.ForgotView;


import static android.view.View.GONE;

public class ForgotPasswordActivity extends Activity implements View.OnClickListener,ForgotView{

    private Button btnForgotPwd;
    private EditText editEmailAddress;
    private Button cancelBtn;
    private Button resetBtn;
    private String emailId;
    private RelativeLayout forgotRelativeLayout;
    private EditText editEnterOTP;
    private Button otpEnterBtn;
    private Button closeBtn;
    private LinearLayout otpLinearLayout;
    private LinearLayout forgotEmailLayout;
    private String mOTP;
    private String otpServer;
    private LinearLayout newConfirmLayout;
    private EditText editpassword;
    private EditText editconfirmPassword;
    private Button confirmBtn;
    private String userId;
    private String mPwd;
    private String mCnfrmPwd;
     ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_layout);
        btnForgotPwd=(Button)findViewById(R.id.btnForgotPwd);

            initView();
            initListiner();

    }

    private void initForgetPasswordResource() {

        ForgotPresenterImpl forgotPresenter = new ForgotPresenterImpl(ForgotPasswordActivity.this);
        forgotPresenter.callApi(AppConstants.RESET_PASSWORD, emailId);

    }

 /*   private void initOTPVerificationResource() {
        ForgotPresenterImpl forgotPresenter = new ForgotPresenterImpl(ForgotPasswordActivity.this);
        forgotPresenter.callApi(AppConstants.OTP_VALUE, mOTP);
    }*/


    private void initView() {

        editEmailAddress=(EditText)findViewById(R.id.editEmailAddress);
        cancelBtn=(Button)findViewById(R.id.cancelBtn);
        resetBtn=(Button)findViewById(R.id.resetBtn);
        forgotRelativeLayout=(RelativeLayout)findViewById(R.id.forgotRelativeLayout);
        editEnterOTP=(EditText)findViewById(R.id.editEnterOTP);
        otpEnterBtn=(Button)findViewById(R.id.otpEnterBtn);
      //  closeBtn=(Button)findViewById(R.id.closeBtn);

        otpLinearLayout=(LinearLayout)findViewById(R.id.otpLinearLayout);
        forgotEmailLayout=(LinearLayout)findViewById(R.id.forgotEmailLayout);
        //newConfirmLayout=(LinearLayout)findViewById(R.id.newConfirmLayout);

    }

    private void initListiner() {
        cancelBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        otpEnterBtn.setOnClickListener(this);
        //closeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancelBtn:
                finish();
                break;
            case R.id.resetBtn:
                checkValidation();

                break;
            case R.id.otpEnterBtn:
                if (!TextUtils.isEmpty(editEnterOTP.getText().toString().trim())) {
                    mOTP = editEnterOTP.getText().toString().trim();
                    otpValidation();
                    //initOTPVerificationResource();
                }
                break;
            /*   case R.id.closeBtn:
                   finish();
                break;*/

        }
    }



    private void checkValidation() {

        if (!TextUtils.isEmpty(editEmailAddress.getText().toString().trim())){
             emailId = editEmailAddress.getText().toString().trim();

            if(emailId.length()<2)
            {
                Snackbar.make(forgotRelativeLayout, "Please Enter valid Email Address", Snackbar.LENGTH_LONG).show();
            }
            else{
                dialog=new ProgressDialog(ForgotPasswordActivity.this,R.style.AppCompatAlertDialogStyle);
                dialog.setMessage("loading...");
                dialog.show();
                initForgetPasswordResource();
            }
        }

    }

    private void otpValidation() {

        if (otpLinearLayout.getVisibility() == View.VISIBLE) {
            if (mOTP.equalsIgnoreCase(otpServer)) {
                Snackbar.make(forgotRelativeLayout, "Email Verified", Snackbar.LENGTH_LONG).show();
                Intent intent=new Intent(ForgotPasswordActivity.this, ResetOldPasswardActivity.class);
                intent.putExtra("USERID",userId);
                startActivity(intent);
               // newConfirmLayout.setVisibility(View.VISIBLE);
               // otpLinearLayout.setVisibility(GONE);
            } else {
                Snackbar.make(forgotRelativeLayout, "Wrong OTP, Please enter valid 4 digit OTP number", Snackbar.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onForgotViewSuccess(ForgotResponse forgotViewResponse) {
        otpServer=forgotViewResponse.getOtp();
        userId=forgotViewResponse.getUserId();
        Snackbar.make(forgotRelativeLayout, forgotViewResponse.getMsg(), Snackbar.LENGTH_LONG).show();
        forgotEmailLayout.setVisibility(GONE);
        otpLinearLayout.setVisibility(View.VISIBLE);
        dialog.dismiss();
    }

    @Override
    public void onForgotViewFailure(String msg) {
        Snackbar.make(forgotRelativeLayout, msg, Snackbar.LENGTH_LONG).show();
        Log.d("fail1111",msg);
        dialog.dismiss();
    }
}
