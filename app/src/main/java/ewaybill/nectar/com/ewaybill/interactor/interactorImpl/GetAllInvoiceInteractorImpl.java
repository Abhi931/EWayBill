package ewaybill.nectar.com.ewaybill.interactor.interactorImpl;

import android.util.Log;

import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.EWayBillApplication;
import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.retrofit.CallbackWithRetry;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import retrofit2.Call;
import retrofit2.Response;

public class GetAllInvoiceInteractorImpl implements Interactor {
    private static final String TAG = GetAllInvoiceInteractorImpl.class.getSimpleName();

    @Override
    public void callApi(ApiInteractor apiInteractor, Object... args) {
        String title = (String)args[0];
        if(title.equalsIgnoreCase(AppConstants.INVOICE_LIST)) {
            callGetAllInvoiceAPI((String)args[1],apiInteractor);
        }

    }

    public void callGetAllInvoiceAPI(String userID,final ApiInteractor mListener) {

        Call<JsonObject> call = EWayBillApplication.mRetroClient.callInvoiceProcessListAPI(userID);
        requestCall(call,mListener);
    }



    private void requestCall(Call<JsonObject> call, final ApiInteractor mListener){
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
                    mListener.onFailure("");
                }
            }
        });
    }
}