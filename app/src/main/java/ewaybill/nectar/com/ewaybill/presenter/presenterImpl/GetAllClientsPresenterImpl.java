package ewaybill.nectar.com.ewaybill.presenter.presenterImpl;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.GetAllSuppiersInteractorImpl;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.client.ClientResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierResponse;
import ewaybill.nectar.com.ewaybill.presenter.Presenter;
import ewaybill.nectar.com.ewaybill.viewstate.ClientView;
import ewaybill.nectar.com.ewaybill.viewstate.SupplierView;

public class GetAllClientsPresenterImpl implements Presenter,ApiInteractor {

    private static final String TAG = GetAllClientsPresenterImpl.class.getSimpleName();
    private final ClientView mView;
    private Interactor mSupplierInteractor;

    public GetAllClientsPresenterImpl(ClientView view) {
        this.mView = view;
        mSupplierInteractor = new GetAllSuppiersInteractorImpl();
    }


    @Override
    public void callApi(Object... args) {
        mSupplierInteractor.callApi(this,args);
    }


    @Override
    public void onSuccess(JsonObject json) {

        try {
            if (!TextUtils.isEmpty(json.toString())) {

                ClientResponse clientResponse = new Gson().fromJson(json, ClientResponse.class);

                if (clientResponse != null && clientResponse.getStatus().equalsIgnoreCase("1")) {
                    mView.onGetClientListSuccess(clientResponse);
                    return;
                }else {
                    mView.onGetClientListFailure(clientResponse.getMsg());
                    return;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage().toString());
        }
    }

    @Override
    public void onFailure(String value) {
        mView.onGetClientListFailure("");
    }
}
