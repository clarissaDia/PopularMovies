package com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {
    private String mTitle;
    private String mSynopsis;
    private String mReleaseDate;
    private String mAverageVote;
    private String mImage;

    public Movies(String title, String synopsis, String releaseDate, String averageVote, String image) {

        this.mTitle = title;
        this.mSynopsis = synopsis;
        this.mReleaseDate = releaseDate;
        this.mAverageVote = averageVote;
        this.mImage = image;
    }

    private Movies(Parcel parcel) {
        mTitle = parcel.readString();
        mSynopsis = parcel.readString();
        mReleaseDate = parcel.readString();
        mAverageVote = parcel.readString();
        mImage = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mTitle);
        parcel.writeString(mSynopsis);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mAverageVote);
        parcel.writeString(mImage);
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel parcel) {
            return new Movies(parcel);
        }

        @Override
        public Movies[] newArray(int i) {
            return new Movies[i];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public void setTile(String title) {
        this.mTitle = title;
    }

    public String getOverView() {
        return mSynopsis;
    }

    public void setOverView(String synopsis) {
        this.mSynopsis = synopsis;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.mReleaseDate = releaseDate;
    }

    public String getVote() {
        return mAverageVote;
    }

    public void setVote(String averageVote) {
        this.mAverageVote = averageVote;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }
}