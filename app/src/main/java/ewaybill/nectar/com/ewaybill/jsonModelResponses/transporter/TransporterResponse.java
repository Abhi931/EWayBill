
package ewaybill.nectar.com.ewaybill.jsonModelResponses.transporter;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransporterResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("transporter_list")
    @Expose
    private List<TransporterData> transporterData = null;
    public final static Parcelable.Creator<TransporterResponse> CREATOR = new Creator<TransporterResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TransporterResponse createFromParcel(Parcel in) {
            return new TransporterResponse(in);
        }

        public TransporterResponse[] newArray(int size) {
            return (new TransporterResponse[size]);
        }

    }
            ;

    protected TransporterResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.transporterData, (TransporterData.class.getClassLoader()));
    }

    public TransporterResponse() {
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

    public List<TransporterData> getTransporterData() {
        return transporterData;
    }

    public void setClientlist(List<TransporterData> clientlist) {
        this.transporterData = clientlist;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(msg);
        dest.writeList(transporterData);
    }

    public int describeContents() {
        return 0;
    }

}