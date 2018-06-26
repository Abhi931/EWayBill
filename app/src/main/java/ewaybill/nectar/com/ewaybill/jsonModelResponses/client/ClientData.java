
package ewaybill.nectar.com.ewaybill.jsonModelResponses.client;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientData implements Parcelable
{

    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("client_GSTIN")
    @Expose
    private String clientGSTIN;
    @SerializedName("client_contactno")
    @Expose
    private String clientContactno;
    @SerializedName("client_emailid")
    @Expose
    private String clientEmailid;
    @SerializedName("client_address")
    @Expose
    private String clientAddress;
    @SerializedName("client_state")
    @Expose
    private String clientState;
    @SerializedName("client_pincode")
    @Expose
    private String clientPincode;
    public final static Creator<ClientData> CREATOR = new Creator<ClientData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ClientData createFromParcel(Parcel in) {
            return new ClientData(in);
        }

        public ClientData[] newArray(int size) {
            return (new ClientData[size]);
        }

    }
    ;

    protected ClientData(Parcel in) {
        this.clientId = ((String) in.readValue((String.class.getClassLoader())));
        this.clientName = ((String) in.readValue((String.class.getClassLoader())));
        this.clientGSTIN = ((String) in.readValue((String.class.getClassLoader())));
        this.clientContactno = ((String) in.readValue((String.class.getClassLoader())));
        this.clientEmailid = ((String) in.readValue((String.class.getClassLoader())));
        this.clientAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.clientState = ((String) in.readValue((String.class.getClassLoader())));
        this.clientPincode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ClientData() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientGSTIN() {
        return clientGSTIN;
    }

    public void setClientGSTIN(String clientGSTIN) {
        this.clientGSTIN = clientGSTIN;
    }

    public String getClientContactno() {
        return clientContactno;
    }

    public void setClientContactno(String clientContactno) {
        this.clientContactno = clientContactno;
    }

    public String getClientEmailid() {
        return clientEmailid;
    }

    public void setClientEmailid(String clientEmailid) {
        this.clientEmailid = clientEmailid;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public String getClientPincode() {
        return clientPincode;
    }

    public void setClientPincode(String clientPincode) {
        this.clientPincode = clientPincode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(clientId);
        dest.writeValue(clientName);
        dest.writeValue(clientGSTIN);
        dest.writeValue(clientContactno);
        dest.writeValue(clientEmailid);
        dest.writeValue(clientAddress);
        dest.writeValue(clientState);
        dest.writeValue(clientPincode);
    }

    public int describeContents() {
        return  0;
    }

}
