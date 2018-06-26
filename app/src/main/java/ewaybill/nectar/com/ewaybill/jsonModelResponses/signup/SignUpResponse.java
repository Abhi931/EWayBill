
package ewaybill.nectar.com.ewaybill.jsonModelResponses.signup;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse implements Parcelable
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
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("GSTIN")
    @Expose
    private String gSTIN;
    @SerializedName("emailid")
    @Expose
    private String emailid;
    @SerializedName("contactno")
    @Expose
    private String contactno;
    @SerializedName("user_type")
    @Expose
    private String usertype;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("pincode")
    @Expose
    private String pincode;



    public final static Creator<SignUpResponse> CREATOR = new Creator<SignUpResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SignUpResponse createFromParcel(Parcel in) {
            return new SignUpResponse(in);
        }

        public SignUpResponse[] newArray(int size) {
            return (new SignUpResponse[size]);
        }

    }
    ;

    protected SignUpResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.gSTIN = ((String) in.readValue((String.class.getClassLoader())));
        this.emailid = ((String) in.readValue((String.class.getClassLoader())));
        this.contactno = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.pincode = ((String) in.readValue((String.class.getClassLoader())));
        this.usertype = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SignUpResponse() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGSTIN() {
        return gSTIN;
    }

    public void setGSTIN(String gSTIN) {
        this.gSTIN = gSTIN;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(msg);
        dest.writeValue(userId);
        dest.writeValue(name);
        dest.writeValue(username);
        dest.writeValue(gSTIN);
        dest.writeValue(emailid);
        dest.writeValue(contactno);
        dest.writeValue(password);
        dest.writeValue(address);
        dest.writeValue(state);
        dest.writeValue(pincode);
        dest.writeValue(usertype);
    }

    public int describeContents() {
        return  0;
    }

}
