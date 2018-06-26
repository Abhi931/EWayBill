package ewaybill.nectar.com.ewaybill.viewstate;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserClientRegistrationResponse;

public interface UserClientRegistrationView {
    public void onUserClientRegistrationSuccess(UserClientRegistrationResponse userClientRegistrationresponse);
    public void onUserClientRegistrationFailure(String msg);
}
