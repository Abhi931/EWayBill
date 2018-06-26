package ewaybill.nectar.com.ewaybill.model;

import android.os.Parcel;
import android.os.Parcelable;

class ProductName implements Parcelable{

    private int id;
    private String product_name;
    private String description;
    private int image;
    boolean isSelected = false;

    public ProductName(int id, String name, String description, int image){
        this.id = id;
        this.product_name = name;
        this.description = description;
        this.image = image;
    }

    protected ProductName(Parcel in) {
        id = in.readInt();
        product_name = in.readString();
        description = in.readString();
        image = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(product_name);
        dest.writeString(description);
        dest.writeInt(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductName> CREATOR = new Creator<ProductName>() {
        @Override
        public ProductName createFromParcel(Parcel in) {
            return new ProductName(in);
        }

        @Override
        public ProductName[] newArray(int size) {
            return new ProductName[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
