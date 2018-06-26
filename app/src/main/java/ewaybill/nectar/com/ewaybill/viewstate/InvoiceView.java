package ewaybill.nectar.com.ewaybill.viewstate;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.Invoice.*;

public interface InvoiceView {

    void onGetInvoiceListSuccess(InvoiceResponse invoiceResponse);
    void onGetInvoiceListFailure(String msg);
}
