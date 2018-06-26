package ewaybill.nectar.com.ewaybill.retrofit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

/**
 * Created by aashita on 25/07/17.
 */

public abstract class RetrofitResponseHandler<T> {

    private static final String TAG = RetrofitResponseHandler.class.getSimpleName();

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private final Context context;

    public RetrofitResponseHandler() {
        this(null);
    }

    public RetrofitResponseHandler(final Context context) {
        this.context = context;
    }


    public abstract void onResponse(final T response);

    /*public void onError(final ErrorResponse errorResponse) {
        if (context == null) {
            return;
        }
        Log.e(TAG, "An error occurred while invoking service. Error Code: " + errorResponse.getErrorCode() + LINE_SEPARATOR + "Message: " + errorResponse.getMessage() + LINE_SEPARATOR);
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
      //  alertBuilder.setTitle(R.string.title_server_error_dialog);
       // alertBuilder.setMessage(R.string.network_error_message);
        alertBuilder.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }
        });
        alertBuilder.show();
    }*/

    public void onFailure(Throwable throwable) {
        if (context == null) {
            return;
        }
        Log.e(TAG, "An error occurred while invoking service", throwable);
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        //alertBuilder.setTitle(R.string.title_network_error_dialog);
        //alertBuilder.setMessage(R.string.network_error_message);
        alertBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }
        });
        alertBuilder.show();
    }


    /*
     * Create a method to handle json response
    *//*

    protected <T> void handleResponse(Call<T> call, final RetrofitResponseHandler<T> responseHandler) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(final Call<T> call, final Response<T> response) {
                if (response.isSuccessful()) {
                    if (responseHandler != null ) {
                        responseHandler.onResponse(response.body());
                    }
                } else {
                    //final ErrorResponse errorResponse = parseError(response);
                    if (responseHandler != null) {
                        //responseHandler.onError(errorResponse);
                    }
                }
            }

            @Override
            public void onFailure(final Call<T> call, final Throwable throwable) {
                if (responseHandler != null) {
                    responseHandler.onFailure(throwable);
                }
            }
        });
    }*/


}
