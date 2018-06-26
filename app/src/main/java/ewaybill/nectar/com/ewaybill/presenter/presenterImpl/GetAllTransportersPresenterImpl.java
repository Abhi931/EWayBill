package ewaybill.nectar.com.ewaybill.presenter.presenterImpl;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.GetAllSuppiersInteractorImpl;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.transporter.TransporterResponse;
import ewaybill.nectar.com.ewaybill.presenter.Presenter;
import ewaybill.nectar.com.ewaybill.viewstate.SupplierView;
import ewaybill.nectar.com.ewaybill.viewstate.TransporterView;

public class GetAllTransportersPresenterImpl implements Presenter,ApiInteractor {

    private static final String TAG = GetAllTransportersPresenterImpl.class.getSimpleName();
    private final TransporterView mView;
    private Interactor mSupplierInteractor;

    public GetAllTransportersPresenterImpl(TransporterView view) {
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

                TransporterResponse transporterResponse = new Gson().fromJson(json, TransporterResponse.class);

                if (transporterResponse != null && transporterResponse.getStatus().equalsIgnoreCase("1")) {
                    mView.onGetTransporterListSuccess(transporterResponse);
                    return;
                }else {
                    mView.onGetTransporterListFailure(transporterResponse.getMsg());
                    return;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage().toString());
        }
    }

    @Override
    public void onFailure(String value) {
        mView.onGetTransporterListFailure("");
    }
}
