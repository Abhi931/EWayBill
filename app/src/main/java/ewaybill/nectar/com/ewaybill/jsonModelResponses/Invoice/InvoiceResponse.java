package ewaybill.nectar.com.ewaybill.jsonModelResponses.Invoice;




import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class InvoiceResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("e_ticket_lists")
    @Expose
    private List<InvoiceData> invoiceData = null;
    public final static Creator<InvoiceResponse> CREATOR = new Creator<InvoiceResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public InvoiceResponse createFromParcel(Parcel in) {
            return new InvoiceResponse(in);
        }

        public InvoiceResponse[] newArray(int size) {
            return (new InvoiceResponse[size]);
        }

    };
    protected InvoiceResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.invoiceData, (InvoiceData.class.getClassLoader()));
    }

    public InvoiceResponse() {
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

    public List<InvoiceData> getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(List<InvoiceData> invoiceData) {
        this.invoiceData = invoiceData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(msg);
        dest.writeList(invoiceData);
    }

    public int describeContents() {
        return  0;
    }

}
