package ewaybill.nectar.com.ewaybill.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class Product implements Parcelable,Saleable {

    private int pId;
    private String pName;
    private BigDecimal pPrice;
    private String pDescription;
    private String pImageName;

    public Product(Parcel in) {
        pId = in.readInt();
        pName = in.readString();
        pDescription = in.readString();
        pImageName = in.readString();
    }

    public Product(int pID, String samsung_galaxy_s6, BigDecimal bigDecimal, String s, String samsung_galaxy_s61) {
        pId = pID;
        pName = samsung_galaxy_s6;
        pDescription = s;
        pPrice = bigDecimal;
        pImageName = samsung_galaxy_s61;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pId);
        dest.writeString(pName);
        dest.writeString(pDescription);
        dest.writeString(pImageName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpImageName() {
        return pImageName;
    }

    public void setpImageName(String pImageName) {
        this.pImageName = pImageName;
    }

    @Override
    public BigDecimal getPrice() {
        return this.pPrice;
    }

    @Override
    public String getName() {
        return this.pName;
    }
}
/*public class Product implements Saleable, Serializable {
    private static final long serialVersionUID = -4073256626483275668L;

    private int pId;
    private String pName;
    private BigDecimal pPrice;
    private String pDescription;
    private String pImageName;

    public Product() {
        super();
    }

    public Product(int pId, String pName, BigDecimal pPrice, String pDescription, String pImageName) {
        setId(pId);
        setName(pName);
        setPrice(pPrice);
        setDescription(pDescription);
        setImageName(pImageName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Product)) return false;

        return (this.pId == ((Product) o).getId());
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + pId;
        hash = hash * prime + (pName == null ? 0 : pName.hashCode());
        hash = hash * prime + (pPrice == null ? 0 : pPrice.hashCode());
        hash = hash * prime + (pDescription == null ? 0 : pDescription.hashCode());

        return hash;
    }


    public int getId() {
        return pId;
    }

    public void setId(int id) {
        this.pId = id;
    }

    @Override
    public BigDecimal getPrice() {
        return pPrice;
    }

    @Override
    public String getName() {
        return pName;
    }

    public void setPrice(BigDecimal price) {
        this.pPrice = price;
    }

    public void setName(String name) {
        this.pName = name;
    }

    public String getDescription() {
        return pDescription;
    }

    public void setDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getImageName() {
        return pImageName;
    }

    public void setImageName(String imageName) {
        this.pImageName = imageName;
    }
}*/
