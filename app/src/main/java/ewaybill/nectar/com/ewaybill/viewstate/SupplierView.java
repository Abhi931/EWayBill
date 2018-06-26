package ewaybill.nectar.com.ewaybill.viewstate;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierResponse;

public interface SupplierView {

    void onGetSupplierListSuccess(SupplierResponse supplierResponse);
    void onGetSupplierListFailure(String msg);
}
