
package ewaybill.nectar.com.ewaybill.jsonModelResponses.Invoice;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoiceData implements Parcelable
{

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("eway_ticket_id")
    @Expose
    private String ewayTicketId;
    @SerializedName("invoice_title")
    @Expose
    private String invoiceTitle;
    @SerializedName("uploaded_invoice_data")
    @Expose
    private String uploaded_invoice_data;

    protected InvoiceData(Parcel in) {
        userId = in.readString();
        ewayTicketId = in.readString();
        invoiceTitle = in.readString();
        uploaded_invoice_data = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(ewayTicketId);
        dest.writeString(invoiceTitle);
        dest.writeString(uploaded_invoice_data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InvoiceData> CREATOR = new Creator<InvoiceData>() {
        @Override
        public InvoiceData createFromParcel(Parcel in) {
            return new InvoiceData(in);
        }

        @Override
        public InvoiceData[] newArray(int size) {
            return new InvoiceData[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEwayTicketId() {
        return ewayTicketId;
    }

    public void setEwayTicketId(String ewayTicketId) {
        this.ewayTicketId = ewayTicketId;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getUploaded_invoice_data() {
        return uploaded_invoice_data;
    }

    public void setUploaded_invoice_data(String uploaded_invoice_data) {
        this.uploaded_invoice_data = uploaded_invoice_data;
    }
}
