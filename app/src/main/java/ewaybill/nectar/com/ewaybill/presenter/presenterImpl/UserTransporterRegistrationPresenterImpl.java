package ewaybill.nectar.com.ewaybill.presenter.presenterImpl;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.UserClientRegistrationInteractorImpl;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.UserTransporterRegistrationInteractorImpl;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserClientRegistrationResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserTransporterRegistrationResponse;
import ewaybill.nectar.com.ewaybill.presenter.Presenter;
import ewaybill.nectar.com.ewaybill.viewstate.UserClientRegistrationView;
import ewaybill.nectar.com.ewaybill.viewstate.UserTransporterRegistrationView;

public class UserTransporterRegistrationPresenterImpl implements Presenter,ApiInteractor {

    private static final String TAG = UserTransporterRegistrationPresenterImpl.class.getSimpleName();
    private final UserTransporterRegistrationView mView;
    private Interactor mUserRegistrationInteractor;

    public UserTransporterRegistrationPresenterImpl(UserTransporterRegistrationView view) {
        this.mView = view;
        mUserRegistrationInteractor = new UserTransporterRegistrationInteractorImpl();
    }


    @Override
    public void callApi(Object... args) {
        mUserRegistrationInteractor.callApi(this,args);
    }


    @Override
    public void onSuccess(JsonObject json) {

        try {
            if (!TextUtils.isEmpty(json.toString())) {

                UserTransporterRegistrationResponse userRegistrationResponse = new Gson().fromJson(json, UserTransporterRegistrationResponse.class);

                if (userRegistrationResponse != null && userRegistrationResponse.getStatus().equalsIgnoreCase("1")) {
                    mView.onUserTransportRegistrationSuccess(userRegistrationResponse);
                    return;
                }else {
                    mView.onUserTransporterRegistrationFailure(userRegistrationResponse.getMsg());
                    return;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage().toString());
        }
    }

    @Override
    public void onFailure(String value) {
        mView.onUserTransporterRegistrationFailure("");
    }
}
