package com.imkalpesh.stimulus.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.imkalpesh.stimulus.BR;


public class DashboardChildModel extends BaseObservable implements Parcelable {

    private String ChildItemTitle;

    private int childItemId;

    private int childImageResource;

    public DashboardChildModel(String childItemTitle, int childImageResource, int lessonLayout) {
        ChildItemTitle = childItemTitle;
        this.childImageResource = childImageResource;
        this.lessonLayout = lessonLayout;
    }

    public int getLessonLayout() {
        return lessonLayout;
    }

    public void setLessonLayout(int lessonLayout) {
        this.lessonLayout = lessonLayout;
    }

    private int lessonLayout;

    public static Creator<DashboardChildModel> getCREATOR() {
        return CREATOR;
    }


    private boolean showDeleteButton;

    public DashboardChildModel() {
    }

    public DashboardChildModel(String childItemTitle, int childImageResource, boolean showDeleteButton) {
        this.ChildItemTitle = childItemTitle;
        this.childImageResource = childImageResource;
        this.showDeleteButton = showDeleteButton;
    }

    public DashboardChildModel(String childItemTitle, int childImageResource) {
        ChildItemTitle = childItemTitle;
        this.childImageResource = childImageResource;
    }

    @Bindable
    public String getChildItemTitle() {
        return ChildItemTitle;
    }

    public void setChildItemTitle(String childItemTitle) {
        ChildItemTitle = childItemTitle;
        notifyPropertyChanged(BR.childItemTitle);
    }

    @Bindable
    public int getChildItemId() {
        return childItemId;
    }

    @Bindable
    public int getChildImageResource() {
        return childImageResource;
    }

    public void setChildImageResource(int childImageResource) {
        this.childImageResource = childImageResource;
        notifyPropertyChanged(BR.childImageResource);
    }

    @Bindable
    public boolean isShowDeleteButton() {
        return showDeleteButton;
    }

    public void setShowDeleteButton(boolean showDeleteButton) {
        this.showDeleteButton = showDeleteButton;
        notifyPropertyChanged(BR.showDeleteButton);
    }

    public void setChildItemId(int childItemId) {
        this.childItemId = childItemId;
        notifyPropertyChanged(BR.childItemId);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ChildItemTitle);
        dest.writeInt(this.childItemId);
        dest.writeInt(this.childImageResource);
        dest.writeInt(this.lessonLayout);
        dest.writeByte(this.showDeleteButton ? (byte) 1 : (byte) 0);
    }

    protected DashboardChildModel(Parcel in) {
        this.ChildItemTitle = in.readString();
        this.childItemId = in.readInt();
        this.childImageResource = in.readInt();
        this.lessonLayout = in.readInt();
        this.showDeleteButton = in.readByte() != 0;
    }

    public static final Creator<DashboardChildModel> CREATOR = new Creator<DashboardChildModel>() {
        @Override
        public DashboardChildModel createFromParcel(Parcel source) {
            return new DashboardChildModel(source);
        }

        @Override
        public DashboardChildModel[] newArray(int size) {
            return new DashboardChildModel[size];
        }
    };
}
