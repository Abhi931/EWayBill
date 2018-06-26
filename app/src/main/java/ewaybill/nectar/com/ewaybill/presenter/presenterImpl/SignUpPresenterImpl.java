package ewaybill.nectar.com.ewaybill.presenter.presenterImpl;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.PrefManager;
import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.SignUpInteractorImpl;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.signup.SignUpResponse;
import ewaybill.nectar.com.ewaybill.presenter.Presenter;
import ewaybill.nectar.com.ewaybill.testSqlDatabase.TLoginActivity;
import ewaybill.nectar.com.ewaybill.viewstate.SignUpView;

import static android.content.Context.MODE_PRIVATE;

public class SignUpPresenterImpl implements Presenter,ApiInteractor {

    private static final String TAG = SignUpPresenterImpl.class.getSimpleName();
    private final SignUpView mView;
    private Interactor mSignUpInteractor;

    public SignUpPresenterImpl(SignUpView view) {
        this.mView = view;
        mSignUpInteractor = new SignUpInteractorImpl();
    }


    @Override
    public void callApi(Object... args) {
        mSignUpInteractor.callApi(this,args);
    }


    @Override
    public void onSuccess(JsonObject json) {

        try {

            if (!TextUtils.isEmpty(json.toString())) {

                SignUpResponse signUpResponse = new Gson().fromJson(json, SignUpResponse.class);

                if (signUpResponse != null && signUpResponse.getStatus().equalsIgnoreCase("1")) {
                    mView.onSignUpSuccess(signUpResponse);
                  //  PrefManager.getActiveInstance(TLoginActivity.context).setUseremail("");
                    if(TLoginActivity.checkbox!=null)
                    {
                        SharedPreferences pref = TLoginActivity.context.getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        if(TLoginActivity.checkbox.isChecked()) {

                        editor.putString("email", TLoginActivity.textInputEditTextName.getText().toString());  // Saving string
                        editor.putString("password", TLoginActivity.textInputEditTextPassword.getText().toString());
                        // Save the changes in SharedPreferences
                        editor.commit(); // commit changes
                        }
                       else
                        {
                         editor.putString("email", null);  // Saving string
                         editor.putString("password", null);
                         editor.commit();
                        }

                    }
                    return;
                }else {
                    mView.onSignUpFailure(signUpResponse.getMsg());
                    return;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage().toString());
        }
    }

    @Override
    public void onFailure(String value) {
        mView.onSignUpFailure("");
    }
}
