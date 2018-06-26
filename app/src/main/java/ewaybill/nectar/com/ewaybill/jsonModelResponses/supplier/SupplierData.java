
package ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupplierData implements Parcelable
{

    @SerializedName("supplier_id")
    @Expose
    private String supplierId;
    @SerializedName("supplier_name")
    @Expose
    private String supplierName;
    @SerializedName("supplier_GSTIN")
    @Expose
    private String supplierGSTIN;
    @SerializedName("supplier_contactno")
    @Expose
    private String supplierContactno;
    @SerializedName("supplier_emailid")
    @Expose
    private String supplierEmailid;
    @SerializedName("supplier_address")
    @Expose
    private String supplierAddress;
    @SerializedName("supplier_state")
    @Expose
    private String supplierState;
    @SerializedName("supplier_pincode")
    @Expose
    private String supplierPincode;
    public final static Creator<SupplierData> CREATOR = new Creator<SupplierData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SupplierData createFromParcel(Parcel in) {
            return new SupplierData(in);
        }

        public SupplierData[] newArray(int size) {
            return (new SupplierData[size]);
        }

    }
    ;

    protected SupplierData(Parcel in) {
        this.supplierId = ((String) in.readValue((String.class.getClassLoader())));
        this.supplierName = ((String) in.readValue((String.class.getClassLoader())));
        this.supplierGSTIN = ((String) in.readValue((String.class.getClassLoader())));
        this.supplierContactno = ((String) in.readValue((String.class.getClassLoader())));
        this.supplierEmailid = ((String) in.readValue((String.class.getClassLoader())));
        this.supplierAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.supplierState = ((String) in.readValue((String.class.getClassLoader())));
        this.supplierPincode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SupplierData() {
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierGSTIN() {
        return supplierGSTIN;
    }

    public void setSupplierGSTIN(String supplierGSTIN) {
        this.supplierGSTIN = supplierGSTIN;
    }

    public String getSupplierContactno() {
        return supplierContactno;
    }

    public void setSupplierContactno(String supplierContactno) {
        this.supplierContactno = supplierContactno;
    }

    public String getSupplierEmailid() {
        return supplierEmailid;
    }

    public void setSupplierEmailid(String supplierEmailid) {
        this.supplierEmailid = supplierEmailid;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierState() {
        return supplierState;
    }

    public void setSupplierState(String supplierState) {
        this.supplierState = supplierState;
    }

    public String getSupplierPincode() {
        return supplierPincode;
    }

    public void setSupplierPincode(String supplierPincode) {
        this.supplierPincode = supplierPincode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(supplierId);
        dest.writeValue(supplierName);
        dest.writeValue(supplierGSTIN);
        dest.writeValue(supplierContactno);
        dest.writeValue(supplierEmailid);
        dest.writeValue(supplierAddress);
        dest.writeValue(supplierState);
        dest.writeValue(supplierPincode);
    }

    public int describeContents() {
        return  0;
    }

}
