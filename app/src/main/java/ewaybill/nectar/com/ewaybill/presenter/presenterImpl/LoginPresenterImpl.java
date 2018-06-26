package ewaybill.nectar.com.ewaybill.presenter.presenterImpl;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.LoginInteractorImpl;
import ewaybill.nectar.com.ewaybill.presenter.Presenter;
import ewaybill.nectar.com.ewaybill.viewstate.LoginView;

/**
 * Created by Abhishek on 4/9/2018.
 */

public class LoginPresenterImpl implements Presenter,ApiInteractor {

    private static final String TAG = LoginPresenterImpl.class.getSimpleName();
    private final LoginView mView;
    private Interactor mLoginInteractor;

    public LoginPresenterImpl(LoginView view) {
        this.mView = view;
        mLoginInteractor = new LoginInteractorImpl();
    }


    @Override
    public void callApi(Object... args) {
        mLoginInteractor.callApi(this,args);
    }


    @Override
    public void onSuccess(JsonObject json) {

        try {
            if (!TextUtils.isEmpty(json.toString())) {

                /*LoginResponse loginResponse = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create()
                        .fromJson(json, LoginResponse.class);

                if (!TextUtils.isEmpty(loginResponse.getToken())) {
                    mView.onLoginSuccess(false);
                    return;
                }

                mView.onLoginFailure(loginResponse.getError().toString());*/
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage().toString());
        }
    }

    @Override
    public void onFailure(String value) {
        if(value.equalsIgnoreCase("invalid_credentials")){
            value = "Email and Password did not match";
        }
        mView.onLoginFailure();
    }
}
