package ewaybill.nectar.com.ewaybill.presenter.presenterImpl;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.ForgotInteractorImpl;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.SignUpInteractorImpl;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.forgot.ForgotResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.signup.SignUpResponse;
import ewaybill.nectar.com.ewaybill.presenter.Presenter;
import ewaybill.nectar.com.ewaybill.viewstate.ForgotView;
import ewaybill.nectar.com.ewaybill.viewstate.SignUpView;

public class ForgotPresenterImpl implements Presenter,ApiInteractor {

    private static final String TAG = ForgotPresenterImpl.class.getSimpleName();
    private final ForgotView mView;
    private Interactor mForgotInteractor;

    public ForgotPresenterImpl(ForgotView view) {
        this.mView = view;
        mForgotInteractor = new ForgotInteractorImpl();
    }


    @Override
    public void callApi(Object... args) {
        mForgotInteractor.callApi(this,args);
    }


    @Override
    public void onSuccess(JsonObject json) {

        try {
            if (!TextUtils.isEmpty(json.toString())) {

                ForgotResponse forgotResponse = new Gson().fromJson(json, ForgotResponse.class);

                if (forgotResponse != null && forgotResponse.getStatus().equalsIgnoreCase("1")) {
                    mView.onForgotViewSuccess(forgotResponse);
                    return;
                }else {
                    mView.onForgotViewFailure(forgotResponse.getMsg());
                    return;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage().toString());
        }
    }

    @Override
    public void onFailure(String value) {
        mView.onForgotViewFailure("");
        Log.d("fail",value);

    }
}
