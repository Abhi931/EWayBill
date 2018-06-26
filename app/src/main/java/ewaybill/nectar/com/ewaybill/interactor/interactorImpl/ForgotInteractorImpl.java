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


public class ForgotInteractorImpl implements Interactor {
  private static final String TAG = ForgotInteractorImpl.class.getSimpleName();

  @Override
  public void callApi(ApiInteractor apiInteractor, Object... args) {
      String title = (String)args[0];

      if(title.equalsIgnoreCase(AppConstants.OTP_VALUE)) {
          callOTPAPI((String) args[1], apiInteractor);

      }else if(title.equalsIgnoreCase(AppConstants.RESET_PASSWORD)){
          callRequestForForgotPwdAPI((String) args[1],apiInteractor);

      }else if(title.equalsIgnoreCase(AppConstants.NEW_PASSWORD)){
          callRequestForUpdateNewPasswordPwdAPI((String) args[1],(String) args[2],apiInteractor);
      }
  }



    public void callOTPAPI(String otp, final ApiInteractor mListener) {

        Call<JsonObject> call = EWayBillApplication.mRetroClient.callOTPValueAPI(otp);
        requestCall(call,mListener);
    }

    public void callRequestForForgotPwdAPI(String emailid, final ApiInteractor mListener) {

        Call<JsonObject> call = EWayBillApplication.mRetroClient.callRequestForForgotPwdAPI(emailid);
        requestCall(call,mListener);
    }
    public void callRequestForUpdateNewPasswordPwdAPI(String userID, String password, final ApiInteractor mListener) {

        Call<JsonObject> call = EWayBillApplication.mRetroClient.callRequestForSetNewPwdAPI(userID,password);
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

