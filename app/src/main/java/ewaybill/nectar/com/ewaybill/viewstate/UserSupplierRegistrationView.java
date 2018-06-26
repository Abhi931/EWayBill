package ewaybill.nectar.com.ewaybill.viewstate;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserSupplierRegistrationResponse;

public interface UserSupplierRegistrationView {
    public void onUserSupplierRegistrationSuccess(UserSupplierRegistrationResponse userSupplierRegistrationresponse);
    public void onUserSupplierRegistrationFailure(String msg);
}
