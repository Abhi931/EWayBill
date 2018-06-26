package ewaybill.nectar.com.ewaybill.jsonModelResponses.transporter;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransporterData implements Parcelable
{

    @SerializedName("transporter_id")
    @Expose
    private String transporterId;
    @SerializedName("transporter_name")
    @Expose
    private String transporterName;
    @SerializedName("transporter_GSTIN")
    @Expose
    private String transporterGSTIN;
    @SerializedName("transporter_contactno")
    @Expose
    private String transporterContactno;
    @SerializedName("transporter_emailid")
    @Expose
    private String transporterEmailid;
    @SerializedName("transporter_address")
    @Expose
    private String transporterAddress;
    @SerializedName("transporter_state")
    @Expose
    private String transporterState;
    @SerializedName("transporter_pincode")
    @Expose
    private String transporterPincode;
    public final static Parcelable.Creator<TransporterData> CREATOR = new Creator<TransporterData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TransporterData createFromParcel(Parcel in) {
            return new TransporterData(in);
        }

        public TransporterData[] newArray(int size) {
            return (new TransporterData[size]);
        }

    }
            ;

    protected TransporterData(Parcel in) {
        this.transporterId = ((String) in.readValue((String.class.getClassLoader())));
        this.transporterName = ((String) in.readValue((String.class.getClassLoader())));
        this.transporterGSTIN = ((String) in.readValue((String.class.getClassLoader())));
        this.transporterContactno = ((String) in.readValue((String.class.getClassLoader())));
        this.transporterEmailid = ((String) in.readValue((String.class.getClassLoader())));
        this.transporterAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.transporterState = ((String) in.readValue((String.class.getClassLoader())));
        this.transporterPincode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TransporterData() {
    }

    public String getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(String transporterId) {
        this.transporterId = transporterId;
    }

    public String getTransporterName() {
        return transporterName;
    }

    public void setTransporterName(String transporterName) {
        this.transporterName = transporterName;
    }

    public String getTransporterGSTIN() {
        return transporterGSTIN;
    }

    public void setTransporterGSTIN(String transporterGSTIN) {
        this.transporterGSTIN = transporterGSTIN;
    }

    public String getTransporterContactno() {
        return transporterContactno;
    }

    public void setTransporterContactno(String transporterContactno) {
        this.transporterContactno = transporterContactno;
    }

    public String getTransporterEmailid() {
        return transporterEmailid;
    }

    public void setTransporterEmailid(String transporterEmailid) {
        this.transporterEmailid = transporterEmailid;
    }

    public String getTransporterAddress() {
        return transporterAddress;
    }

    public void setTransporterAddress(String transporterAddress) {
        this.transporterAddress = transporterAddress;
    }

    public String getTransporterState() {
        return transporterState;
    }

    public void setTransporterState(String transporterState) {
        this.transporterState = transporterState;
    }

    public String getTransporterPincode() {
        return transporterPincode;
    }

    public void setTransporterPincode(String transporterPincode) {
        this.transporterPincode = transporterPincode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(transporterId);
        dest.writeValue(transporterName);
        dest.writeValue(transporterGSTIN);
        dest.writeValue(transporterContactno);
        dest.writeValue(transporterEmailid);
        dest.writeValue(transporterAddress);
        dest.writeValue(transporterState);
        dest.writeValue(transporterPincode);
    }

    public int describeContents() {
        return 0;
    }

}
