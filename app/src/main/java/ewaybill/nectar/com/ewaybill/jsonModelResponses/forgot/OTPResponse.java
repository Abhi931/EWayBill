
package ewaybill.nectar.com.ewaybill.jsonModelResponses.forgot;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPResponse implements Parcelable
{


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("OTP")
    @Expose
    private String otp;




    public final static Creator<OTPResponse> CREATOR = new Creator<OTPResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OTPResponse createFromParcel(Parcel in) {
            return new OTPResponse(in);
        }

        public OTPResponse[] newArray(int size) {
            return (new OTPResponse[size]);
        }

    }
            ;

    protected OTPResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.otp = ((String) in.readValue((String.class.getClassLoader())));

    }

    public OTPResponse() {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }



    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(msg);
        dest.writeValue(userId);
        dest.writeValue(otp);
    }

    public int describeContents() {
        return  0;
    }

}
