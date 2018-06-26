package ewaybill.nectar.com.ewaybill.viewstate;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserClientRegistrationResponse;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserTransporterRegistrationResponse;

public interface UserTransporterRegistrationView {
    public void onUserTransportRegistrationSuccess(UserTransporterRegistrationResponse userTransporterRegistrationresponse);
    public void onUserTransporterRegistrationFailure(String msg);
}
