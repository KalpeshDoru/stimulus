package com.imkalpesh.stimulus.models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class SettingModel implements Parcelable {

    private Drawable imgResource;

    public Drawable getImgResource() {
        return imgResource;
    }

    public void setImgResource(Drawable imgResource) {
        this.imgResource = imgResource;
    }

    public static Creator<SettingModel> getCREATOR() {
        return CREATOR;
    }

    private String settingTitle;

    public SettingModel(Drawable imgResource, String settingTitle) {
        this.imgResource = imgResource;
        this.settingTitle = settingTitle;
    }

    protected SettingModel(Parcel in) {
        settingTitle = in.readString();
    }

    public static final Creator<SettingModel> CREATOR = new Creator<SettingModel>() {
        @Override
        public SettingModel createFromParcel(Parcel in) {
            return new SettingModel(in);
        }

        @Override
        public SettingModel[] newArray(int size) {
            return new SettingModel[size];
        }
    };

    @BindingAdapter({"bind"})
    public static void setImageViewResource(ImageView imageView, Drawable resource) {
        imageView.setImageDrawable(resource);
    }

    public String getSettingTitle() {
        return settingTitle;
    }

    public void setSettingTitle(String settingTitle) {
        this.settingTitle = settingTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(settingTitle);
    }
}
