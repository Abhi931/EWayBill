package ewaybill.nectar.com.ewaybill.interactor.interactorImpl;

import android.util.Log;

import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.EWayBillApplication;
import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.retrofit.CallbackWithRetry;
import retrofit2.Call;
import retrofit2.Response;

import static ewaybill.nectar.com.ewaybill.utils.AppConstants.TAG;

/**
 * Created by Abhishek on 4/8/2018.
 */

public class LoginInteractorImpl implements Interactor {
    private static final String TAG = LoginInteractorImpl.class.getSimpleName();

    @Override
    public void callApi(ApiInteractor apiInteractor, Object... args) {
        callLoginAPI((String)args[0],(String)args[1],apiInteractor);
    }

    public void callLoginAPI(String emailID, String password, final ApiInteractor mListener) {


        Call<JsonObject> call = EWayBillApplication.mRetroClient.callLoginAPI(emailID, password);
        Log.d(TAG, call.request().toString());

        call.enqueue(new CallbackWithRetry<JsonObject>(call) {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());
                    mListener.onSuccess(response.body());
                } else {
                    if (response.errorBody() != null) {
                        mListener.onFailure("");
                    } else {
                        onFailure(call, new Throwable());
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (!onFailureResponse(call, t)) {
                }
            }
        });
    }

}