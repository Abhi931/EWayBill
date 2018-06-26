package ewaybill.nectar.com.ewaybill.presenter.presenterImpl;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ewaybill.nectar.com.ewaybill.interactor.ApiInteractor;
import ewaybill.nectar.com.ewaybill.interactor.Interactor;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.GetAllInvoiceInteractorImpl;
import ewaybill.nectar.com.ewaybill.interactor.interactorImpl.GetAllSuppiersInteractorImpl;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.Invoice.InvoiceResponse;
import ewaybill.nectar.com.ewaybill.presenter.Presenter;
import ewaybill.nectar.com.ewaybill.viewstate.ClientView;
import ewaybill.nectar.com.ewaybill.viewstate.InvoiceView;

public class GetAllInvoicePresenterImpl implements Presenter,ApiInteractor {

    private static final String TAG = GetAllInvoicePresenterImpl.class.getSimpleName();
    private final InvoiceView mView;
    private Interactor mInvoiceInteractor;

    public GetAllInvoicePresenterImpl(InvoiceView view) {
        this.mView = view;
        mInvoiceInteractor = new GetAllInvoiceInteractorImpl();
    }


    @Override
    public void callApi(Object... args) {
        mInvoiceInteractor.callApi(this,args);
    }


    @Override
    public void onSuccess(JsonObject json) {

        try {
            if (!TextUtils.isEmpty(json.toString())) {

                InvoiceResponse invoiceResponse = new Gson().fromJson(json, InvoiceResponse.class);

                if (invoiceResponse != null && invoiceResponse.getStatus().equalsIgnoreCase("1")) {
                    mView.onGetInvoiceListSuccess(invoiceResponse);
                    return;
                }else {
                    mView.onGetInvoiceListFailure(invoiceResponse.getMsg());
                    return;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage().toString());
        }
    }

    @Override
    public void onFailure(String value) {
        mView.onGetInvoiceListFailure("");
    }
}
