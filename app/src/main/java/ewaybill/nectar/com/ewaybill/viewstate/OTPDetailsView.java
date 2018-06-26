package ewaybill.nectar.com.ewaybill.viewstate;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.forgot.ForgotResponse;

public interface OTPDetailsView {

    public void onOTPDetailsViewSuccess(ForgotResponse mOTPDetailsViewResponse);
    public void onOTPDetailsViewFailure(String msg);
}
