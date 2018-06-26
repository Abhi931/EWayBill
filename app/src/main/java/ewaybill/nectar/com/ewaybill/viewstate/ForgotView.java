package ewaybill.nectar.com.ewaybill.viewstate;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.forgot.ForgotResponse;

public interface ForgotView {

    public void onForgotViewSuccess(ForgotResponse forgotViewResponse);
    public void onForgotViewFailure(String msg);
}
