package ewaybill.nectar.com.ewaybill.retrofit;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonParser;


import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by aashita on 25/07/17.
 * This abstract class send requests to server upto total retries.
 */

public abstract class CallbackWithRetry<T> implements Callback<T> {

    private static final int TOTAL_RETRIES = 3;
    private static final String TAG = CallbackWithRetry.class.getSimpleName();
    private final Call<T> call;
    private RetrofitResponseHandler<T> mHandler = null;
    private int retryCount = 0;

    public CallbackWithRetry(Call<T> call, RetrofitResponseHandler<T> responseHandler) {
        this.call = call;
        // this.mHandler = responseHandler;
    }

    public CallbackWithRetry(Call<T> call) {
        this.call = call;
    }

    private void retry() {
        call.clone().enqueue(this);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e(TAG, t.getLocalizedMessage());
        if (retryCount++ < TOTAL_RETRIES) {
            Log.v(TAG, "Retrying... (" + retryCount + " out of " + TOTAL_RETRIES + ")");
            retry();
        }

    }

    public boolean onFailureResponse(Call<T> call, Throwable t) {
        // Log.e(TAG, t.getLocalizedMessage());
        if (retryCount++ < TOTAL_RETRIES) {
            Log.v(TAG, "Retrying... (" + retryCount + " out of " + TOTAL_RETRIES + ")");
            retry();
            return true;
        }
        return false;

    }

  /*  public String onErrorResponse(ResponseBody error) {
        try {
            if (TextUtils.isEmpty(error.toString())) {
                return "";
            }


            JsonParser parser = new JsonParser();
            String retVal = parser.parse(error.toString()).getAsString();
            JSONObject obj = new JSONObject(error.string());
            //LoggerUtils.logE(TAG,"onReponse "+obj);
            LoggerUtils.logD(TAG, "onResponse: " + obj.getString("error"));

            if (obj.getString("error").equals("token_expired")) {
                PreferenceManager.getActiveInstance(null).logoutUser();
            }
            if (obj.getString("error").equals("incompatible_version")) {
                Context mContext = TagTasteApplicationInitializer.getmCurrentContext();
                AlertDialogUtils.showForceAppVersionDialog(mContext, mContext.getString(R.string.new_update_found),
                        mContext.getString(R.string.new_update_found_msg),
                        mContext.getString(R.string.update_now), null);
            }
            return obj.getString("error");

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }*/
}
