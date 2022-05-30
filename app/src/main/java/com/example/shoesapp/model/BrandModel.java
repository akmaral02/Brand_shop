package com.example.shoesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BrandModel implements Parcelable {

    private String name;
    private String slogan;
    private String image;
    private float delivery_charge;
    private Hours hours;
    private List<Shoe> shoes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String address) {
        this.slogan = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(float delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public List<Shoe> getShoes() {
        return shoes;
    }

    public void setShoes(List<Shoe> shoes) {
        this.shoes = shoes;
    }

    protected BrandModel(Parcel in) {
        name = in.readString();
        slogan = in.readString();
        image = in.readString();
        delivery_charge = in.readFloat();
        shoes = in.createTypedArrayList(Shoe.CREATOR);
    }

    public static final Creator<BrandModel> CREATOR = new Creator<BrandModel>() {
        @Override
        public BrandModel createFromParcel(Parcel in) {
            return new BrandModel(in);
        }

        @Override
        public BrandModel[] newArray(int size) {
            return new BrandModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(slogan);
        dest.writeString(image);
        dest.writeFloat(delivery_charge);
        dest.writeTypedList(shoes);
    }


}
