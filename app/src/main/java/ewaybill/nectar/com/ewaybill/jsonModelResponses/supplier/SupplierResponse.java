
package ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupplierResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("supplier_list")
    @Expose
    private List<SupplierData> supplierData = null;
    public final static Creator<SupplierResponse> CREATOR = new Creator<SupplierResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SupplierResponse createFromParcel(Parcel in) {
            return new SupplierResponse(in);
        }

        public SupplierResponse[] newArray(int size) {
            return (new SupplierResponse[size]);
        }

    }
            ;

    protected SupplierResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.supplierData, (SupplierData.class.getClassLoader()));
    }

    public SupplierResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SupplierData> getSupplierData() {
        return supplierData;
    }

    public void setSupplierData(List<SupplierData> supplierData) {
        this.supplierData = supplierData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(msg);
        dest.writeList(supplierData);
    }

    public int describeContents() {
        return  0;
    }

}
