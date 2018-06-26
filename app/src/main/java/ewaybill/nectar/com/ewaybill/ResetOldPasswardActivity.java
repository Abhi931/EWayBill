package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.forgot.ForgotResponse;
import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.ForgotPresenterImpl;
import ewaybill.nectar.com.ewaybill.testSqlDatabase.TLoginActivity;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.viewstate.ForgotView;

public class ResetOldPasswardActivity extends Activity implements View.OnClickListener,ForgotView {
    private EditText editpassword;
    private EditText editconfirmPassword;
    private Button confirmBtn;
    private String mPwd;
    private String mCnfrmPwd;
    private RelativeLayout forgotRelativeLayout;
    private String id;
    private String pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_reset_password_layout);
        initView();
        initListiner();

         id =getIntent().getStringExtra("USERID");

    }

    private void initListiner() {
        confirmBtn.setOnClickListener(this);
    }

    private void initView() {
        editpassword=(EditText)findViewById(R.id.editpassword);
        editconfirmPassword=(EditText)findViewById(R.id.editconfirmPassword);
        confirmBtn=(Button)findViewById(R.id.confirmBtn);
        forgotRelativeLayout=(RelativeLayout)findViewById(R.id.forgotRelativeLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmBtn:
                if(!TextUtils.isEmpty(editpassword.getText().toString()) &&
                        (!TextUtils.isEmpty(editconfirmPassword.getText().toString()))) {
                    mPwd = editpassword.getText().toString().trim();
                    mCnfrmPwd = editconfirmPassword.getText().toString().trim();
                    if (mPwd.equalsIgnoreCase(mCnfrmPwd)) {
                        initResetNewPasswordAPICall();
                    } else {
                        Snackbar.make(forgotRelativeLayout, "Both fields are not matched", Snackbar.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    private void initResetNewPasswordAPICall() {
        ForgotPresenterImpl forgotPresenter = new ForgotPresenterImpl(ResetOldPasswardActivity.this);
        forgotPresenter.callApi(AppConstants.NEW_PASSWORD, id, mPwd);
    }

    @Override
    public void onForgotViewSuccess(ForgotResponse forgotViewResponse) {
        Snackbar.make(forgotRelativeLayout, forgotViewResponse.getMsg(), Snackbar.LENGTH_LONG).show();
        Intent i=new Intent(ResetOldPasswardActivity.this, TLoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onForgotViewFailure(String msg) {
        Snackbar.make(forgotRelativeLayout,msg, Snackbar.LENGTH_LONG).show();
    }
}
