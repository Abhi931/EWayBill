package ewaybill.nectar.com.ewaybill.presenter.presenterImpl;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.GetAllSuppiersInteractorImpl;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierResponse;
import ewaybill.nectar.com.ewaybill.presenter.Presenter;
import ewaybill.nectar.com.ewaybill.viewstate.SupplierView;

public class GetAllSuppliersPresenterImpl implements Presenter,ApiInteractor {

    private static final String TAG = GetAllSuppliersPresenterImpl.class.getSimpleName();
    private final SupplierView mView;
    private Interactor mSupplierInteractor;

    public GetAllSuppliersPresenterImpl(SupplierView view) {
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

                SupplierResponse supplierResponse = new Gson().fromJson(json, SupplierResponse.class);

                if (supplierResponse != null && supplierResponse.getStatus().equalsIgnoreCase("1")) {
                    mView.onGetSupplierListSuccess(supplierResponse);
                    return;
                }else {
                    mView.onGetSupplierListFailure(supplierResponse.getMsg());
                    return;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage().toString());
        }
    }

    @Override
    public void onFailure(String value) {
        mView.onGetSupplierListFailure("");
    }
}
