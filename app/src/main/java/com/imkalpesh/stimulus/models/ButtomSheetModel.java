package com.imkalpesh.stimulus.models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class ButtomSheetModel implements Parcelable {

    public static final Creator<ButtomSheetModel> CREATOR = new Creator<ButtomSheetModel>() {
        @Override
        public ButtomSheetModel createFromParcel(Parcel in) {
            return new ButtomSheetModel(in);
        }

        @Override
        public ButtomSheetModel[] newArray(int size) {
            return new ButtomSheetModel[size];
        }
    };
    private Drawable imgResource;
    private String buttomSheetTitle;

    public ButtomSheetModel(Drawable imgResource, String buttomSheetTitle) {
        this.imgResource = imgResource;
        this.buttomSheetTitle = buttomSheetTitle;
    }

    protected ButtomSheetModel(Parcel in) {
        buttomSheetTitle = in.readString();
    }

    public Drawable getImgResource() {
        return imgResource;
    }

    public void setImgResource(Drawable imgResource) {
        this.imgResource = imgResource;
    }

    public String getButtomSheetTitle() {
        return buttomSheetTitle;
    }

    public void setButtomSheetTitle(String buttomSheetTitle) {
        this.buttomSheetTitle = buttomSheetTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(buttomSheetTitle);
    }
}
