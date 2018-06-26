package ewaybill.nectar.com.ewaybill.interactor;


import com.google.gson.JsonObject;

import java.io.Serializable;

import retrofit2.Response;

/**
 * Created by aashita on 27-Jul-17.
 */

public interface ApiInteractor {
    void onSuccess(JsonObject json);
    void onFailure(String value);
}
