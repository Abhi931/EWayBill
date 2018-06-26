package ewaybill.nectar.com.ewaybill;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ewaybill.nectar.com.ewaybill.model.User;
import ewaybill.nectar.com.ewaybill.retrofit.RetroAPIInterface;
import ewaybill.nectar.com.ewaybill.retrofit.RetrofitClient;

/**
 * Created by Abhishek on 4/8/2018.
 */

public class EWayBillApplication extends com.instamojo.android.InstamojoApplication {

    private static final String TAG = EWayBillApplication.class.getSimpleName();
    public static RetroAPIInterface mRetroClient;

    public static Context getmCurrentContext() {
        return mCurrentContext;
    }

    public static void setmCurrentContext(Context mCurrentContext) {
        EWayBillApplication.mCurrentContext = mCurrentContext;
    }

    public static Context mCurrentContext = null;
    private static EWayBillApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        setmCurrentContext(getApplicationContext());
        initializeClients();
    }

    public static synchronized EWayBillApplication getInstance() {
        return mInstance;
    }

    // Initialize sharedPref and retrofit client once.
    private void initializeClients(){
        try {
            // Fabric.with(this, new Crashlytics());
            PrefManager.getActiveInstance(this);

            mRetroClient = RetrofitClient.getClient().create(RetroAPIInterface.class);
            // googleAnalyticsInitialization();

        }
        catch (Exception e){
            Log.e(TAG,e.getMessage().toString());
        }
    }

}