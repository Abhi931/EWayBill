package ewaybill.nectar.com.ewaybill.viewstate;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.transporter.TransporterResponse;

public interface TransporterView {

    void onGetTransporterListSuccess(TransporterResponse transporterResponse);
    void onGetTransporterListFailure(String msg);
}
