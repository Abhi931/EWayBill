
package ewaybill.nectar.com.ewaybill.jsonModelResponses.client;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierData;

public class ClientResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("client_list")
    @Expose
    private List<ClientData> clientData = null;
    public final static Creator<ClientResponse> CREATOR = new Creator<ClientResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ClientResponse createFromParcel(Parcel in) {
            return new ClientResponse(in);
        }

        public ClientResponse[] newArray(int size) {
            return (new ClientResponse[size]);
        }

    };

    protected ClientResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.clientData, (ClientData.class.getClassLoader()));
    }

    public ClientResponse() {
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

    public List<ClientData> getClientData() {
        return clientData;
    }

    public void setClientData(List<ClientData> clientData) {
        this.clientData = clientData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(msg);
        dest.writeList(clientData);
    }

    public int describeContents() {
        return  0;
    }

}
