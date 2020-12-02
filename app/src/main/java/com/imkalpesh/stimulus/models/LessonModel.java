package com.imkalpesh.stimulus.models;

import android.os.Parcel;
import android.os.Parcelable;

public class LessonModel implements Parcelable {


    public static final Creator<LessonModel> CREATOR = new Creator<LessonModel>() {
        @Override
        public LessonModel createFromParcel(Parcel in) {
            return new LessonModel(in);
        }

        @Override
        public LessonModel[] newArray(int size) {
            return new LessonModel[size];
        }
    };
    private String lessonTitle;
    private String lessonDescription;
    private boolean lessonLayout;

    public LessonModel() {
    }

    public LessonModel(String lessonTitle, String lessonDescription ) {
        this.lessonTitle = lessonTitle;
        this.lessonDescription = lessonDescription;

    }

    protected LessonModel(Parcel in) {
        lessonTitle = in.readString();
        lessonDescription = in.readString();
        lessonLayout = in.readByte() != 0;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public boolean isLessonLayout() {
        return lessonLayout;
    }

    public void setLessonLayout(boolean lessonLayout) {
        this.lessonLayout = lessonLayout;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lessonTitle);
        parcel.writeString(lessonDescription);
        parcel.writeByte((byte) (lessonLayout ? 1 : 0));
    }
}
