package com.imkalpesh.stimulus.models;

import android.os.Parcel;
import android.os.Parcelable;


public class QuotesModel implements Parcelable {

    private String quotes;

    private int quotes_Id;

    private boolean isUserAddedQuotes;

    public QuotesModel(String quotes, boolean isUserAddedQuotes) {
        this.quotes = quotes;
        this.isUserAddedQuotes = isUserAddedQuotes;
    }

    public QuotesModel(String quotes) {
        this.quotes = quotes;
    }

    public QuotesModel() {
    }


    protected QuotesModel(Parcel in) {
        quotes = in.readString();
        quotes_Id = in.readInt();
        isUserAddedQuotes = in.readByte() != 0;
    }

    public static final Creator<QuotesModel> CREATOR = new Creator<QuotesModel>() {
        @Override
        public QuotesModel createFromParcel(Parcel in) {
            return new QuotesModel(in);
        }

        @Override
        public QuotesModel[] newArray(int size) {
            return new QuotesModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(quotes);
        parcel.writeInt(quotes_Id);
        parcel.writeByte((byte) (isUserAddedQuotes ? 1 : 0));
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public int getQuotes_Id() {
        return quotes_Id;
    }

    public void setQuotes_Id(int quotes_Id) {
        this.quotes_Id = quotes_Id;
    }

    public boolean isUserAddedQuotes() {
        return isUserAddedQuotes;
    }

    public void setUserAddedQuotes(boolean userAddedQuotes) {
        isUserAddedQuotes = userAddedQuotes;
    }

    public static Creator<QuotesModel> getCREATOR() {
        return CREATOR;
    }
}