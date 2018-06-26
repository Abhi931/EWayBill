package ewaybill.nectar.com.ewaybill.viewstate;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.signup.SignUpResponse;

public interface SignUpView {
    public void onSignUpSuccess(SignUpResponse signUpResponse);
    public void onSignUpFailure(String msg);
}
