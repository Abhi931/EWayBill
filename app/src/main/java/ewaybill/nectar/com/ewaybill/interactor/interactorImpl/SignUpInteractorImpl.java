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


  public class SignUpInteractorImpl implements Interactor {
    private static final String TAG = SignUpInteractorImpl.class.getSimpleName();

    @Override
    public void callApi(ApiInteractor apiInteractor, Object... args) {
        String title = (String)args[0];
        if(title.equalsIgnoreCase(AppConstants.SIGNUP)) {
            callSignUpAPI((String) args[1], (String) args[2], (String) args[3], (String) args[4], (String) args[5], (String) args[6]
                    ,(String) args[7], (String) args[8], (String) args[9],(String) args[10], apiInteractor);

        }else if(title.equalsIgnoreCase(AppConstants.LOGIN)){
            callLoginAPI((String) args[1], (String) args[2],apiInteractor);

        }else if(title.equalsIgnoreCase(AppConstants.EMAIL_REGISTRATION)){
            callEmailRegistrationAPI((String) args[1], (String) args[2],apiInteractor);

        }else if(title.equalsIgnoreCase(AppConstants.RESET_PASSWORD)){
            callRequestForForgotPwdAPI((String) args[1],apiInteractor);
        }
    }

      public void callEmailRegistrationAPI(String emailid, String rusertype, final ApiInteractor mListener) {

          Call<JsonObject> call = EWayBillApplication.mRetroClient.callEmailRegistrationAPI(emailid,rusertype);
          requestCall(call,mListener);
      }

      public void callRequestForForgotPwdAPI(String emailid, final ApiInteractor mListener) {

          Call<JsonObject> call = EWayBillApplication.mRetroClient.callRequestForForgotPwdAPI(emailid);
          requestCall(call,mListener);
      }


      public void callLoginAPI(String name, String password, final ApiInteractor mListener) {

        Call<JsonObject> call = EWayBillApplication.mRetroClient.callLoginAPI(name,password);
        requestCall(call,mListener);
    }

    public void callSignUpAPI(String name, String username, String password,
                              String emailid, String GSTIN, String contactno,String rusertype,String address, String state, String pincode, final ApiInteractor mListener) {

        Call<JsonObject> call = EWayBillApplication.mRetroClient.callSignUpAPI(name, username,password,
                emailid,GSTIN,contactno,rusertype,address,state,pincode);

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


/*
public class SignUpInteractorImpl implements Interactor {
    private static final String TAG = SignUpInteractorImpl.class.getSimpleName();

    @Override
    public void callApi(ApiInteractor apiInteractor, Object... args) {
        callSignUpAPI((String)args[0],(String)args[1],(String)args[2],(String)args[3],(String)args[4],(String)args[5],apiInteractor);
    }

    public void callSignUpAPI(String name, String username, String password,
                              String emailid, String GSTIN, String contactno, final ApiInteractor mListener) {


        Call<JsonObject> call = EWayBillApplication.mRetroClient.callSignUpAPI(name, username,password,
                emailid,GSTIN,contactno);
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

}*/
