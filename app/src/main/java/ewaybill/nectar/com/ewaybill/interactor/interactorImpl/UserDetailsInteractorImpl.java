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


public class UserDetailsInteractorImpl implements Interactor {
  private static final String TAG = UserDetailsInteractorImpl.class.getSimpleName();

  @Override
  public void callApi(ApiInteractor apiInteractor, Object... args) {
      String title = (String)args[0];
      if(title.equalsIgnoreCase(AppConstants.CLIENT_LIST)) {
          callClientListAPI((String) args[1],apiInteractor);

      }else if(title.equalsIgnoreCase(AppConstants.SUPPLIER_LIST)){
          callSupplierListAPI((String) args[1],apiInteractor);

      }else if(title.equalsIgnoreCase(AppConstants.TRANSPORTER_LIST)){
          callTransporterListAPI((String) args[1],apiInteractor);
      }
  }

  public void callClientListAPI(String userid, final ApiInteractor mListener) {

      Call<JsonObject> call = EWayBillApplication.mRetroClient.callClientListAPI(userid);
      requestCall(call,mListener);
  }

    public void callSupplierListAPI(String userid, final ApiInteractor mListener) {

        Call<JsonObject> call = EWayBillApplication.mRetroClient.callSupplierListAPI(userid);
        requestCall(call,mListener);
    }

  public void callTransporterListAPI(String userid, final ApiInteractor mListener) {

      Call<JsonObject> call = EWayBillApplication.mRetroClient.callTransporterListAPI(userid);

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
