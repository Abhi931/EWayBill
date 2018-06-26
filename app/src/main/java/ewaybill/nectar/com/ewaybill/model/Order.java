package ewaybill.nectar.com.ewaybill.model;

/**
 * Created by Abhishek on 4/17/2018.
 */

public class Order  {
    private int orderId;
    private String clientName;
    private String clientGSTINNo;
    private String supplierName;
    private String supplierGSTINNo;
    private String transporterName;
    private String transporterGSTINNo;
    private String productDescription;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientGSTINNo() {
        return clientGSTINNo;
    }

    public void setClientGSTINNo(String clientGSTINNo) {
        this.clientGSTINNo = clientGSTINNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierGSTINNo() {
        return supplierGSTINNo;
    }

    public void setSupplierGSTINNo(String supplierGSTINNo) {
        this.supplierGSTINNo = supplierGSTINNo;
    }

    public String getTransporterName() {
        return transporterName;
    }

    public void setTransporterName(String transporterName) {
        this.transporterName = transporterName;
    }

    public String getTransporterGSTINNo() {
        return transporterGSTINNo;
    }

    public void setTransporterGSTINNo(String transporterGSTINNo) {
        this.transporterGSTINNo = transporterGSTINNo;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
