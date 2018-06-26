package ewaybill.nectar.com.ewaybill.presenter.presenterImpl;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.UserSupplierRegistrationInteractorImpl;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserSupplierRegistrationResponse;
import ewaybill.nectar.com.ewaybill.presenter.Presenter;
import ewaybill.nectar.com.ewaybill.viewstate.UserClientRegistrationView;
import ewaybill.nectar.com.ewaybill.viewstate.UserSupplierRegistrationView;

public class UserSupplierRegistrationPresenterImpl implements Presenter,ApiInteractor {

    private static final String TAG = UserSupplierRegistrationPresenterImpl.class.getSimpleName();
    private final UserSupplierRegistrationView mView;
    private Interactor mUserSupplierRegistrationInteractor;

    public UserSupplierRegistrationPresenterImpl(UserSupplierRegistrationView view) {
        this.mView = view;
        mUserSupplierRegistrationInteractor = new UserSupplierRegistrationInteractorImpl();
    }


    @Override
    public void callApi(Object... args) {
        mUserSupplierRegistrationInteractor.callApi(this,args);
    }


    @Override
    public void onSuccess(JsonObject json) {

        try {
            if (!TextUtils.isEmpty(json.toString())) {

                UserSupplierRegistrationResponse userRegistrationResponse = new Gson().fromJson(json, UserSupplierRegistrationResponse.class);

                if (userRegistrationResponse != null && userRegistrationResponse.getStatus().equalsIgnoreCase("1")) {
                    mView.onUserSupplierRegistrationSuccess(userRegistrationResponse);
                    return;
                }else {
                    mView.onUserSupplierRegistrationFailure(userRegistrationResponse.getMsg());
                    return;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage().toString());
        }
    }

    @Override
    public void onFailure(String value) {
        mView.onUserSupplierRegistrationFailure("");
    }
}
