package com.imkalpesh.stimulus.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DashboardParentModel implements Parcelable {
    private String ParentItemTitle;

    private ArrayList<DashboardChildModel> ChildItemList;
    private boolean isViewMore;


    public DashboardParentModel(String ParentItemTitle, ArrayList<DashboardChildModel> ChildItemList)
    {
        this.ParentItemTitle = ParentItemTitle;
        this.ChildItemList = ChildItemList;
    }

    protected DashboardParentModel(Parcel in) {
        ParentItemTitle = in.readString();
        ChildItemList = in.createTypedArrayList(DashboardChildModel.CREATOR);
        isViewMore = in.readByte() != 0;
    }

    public static final Creator<DashboardParentModel> CREATOR = new Creator<DashboardParentModel>() {
        @Override
        public DashboardParentModel createFromParcel(Parcel in) {
            return new DashboardParentModel(in);
        }

        @Override
        public DashboardParentModel[] newArray(int size) {
            return new DashboardParentModel[size];
        }
    };

    public String getParentItemTitle() {
        return ParentItemTitle;
    }

    public void setParentItemTitle(String parentItemTitle) {
        ParentItemTitle = parentItemTitle;
    }

    public ArrayList<DashboardChildModel> getChildItemList() {
        return ChildItemList;
    }

    public void setChildItemList(ArrayList<DashboardChildModel> childItemList) {
        ChildItemList = childItemList;
    }

    public boolean isViewMore() {
        return isViewMore;
    }

    public void setViewMore(boolean viewMore) {
        isViewMore = viewMore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ParentItemTitle);
        parcel.writeTypedList(ChildItemList);
        parcel.writeByte((byte) (isViewMore ? 1 : 0));
    }

}
