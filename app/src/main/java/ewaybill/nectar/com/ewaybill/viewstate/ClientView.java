package ewaybill.nectar.com.ewaybill.viewstate;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.client.ClientResponse;

public interface ClientView {

    void onGetClientListSuccess(ClientResponse clientResponse);
    void onGetClientListFailure(String msg);
}
