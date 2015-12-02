/**
 * Created by Brenna Pavlinchak on 11/27/15.
 */

package com.example.ravenmargret.java2project1;

import java.io.Serializable;

public class Song implements Serializable
{
    private static final long serialVersionUID = 8736847634070552888L;
    String mType;
    String mArtist;
    String mURI;
    String mDate;
    String mLocation;
    String mEventName;

    public Song(String mType, String mArtist, String mURI, String mDate, String mLocation, String mEventName)
    {
        this.mType = mType;
        this.mArtist = mArtist;
        this.mURI = mURI;
        this.mDate = mDate;
        this.mLocation = mLocation;
        this.mLocation = mEventName;
    }

    public String getmType() {return mType;}
    public String getmArtist() {return mArtist;}
    public String getmURI() {return mURI;}
    public String getmDate() {return mDate;}
    public String getmLocation() {return mLocation;}
    public String getmEventName() {return mEventName;}

    public void setmType(String mType) {this.mType = mType;}
    public void setmArtist(String mArtist) {this.mArtist = mArtist;}
    public void setmURI(String mURI) {this.mURI = mURI;}
    public void setmDate(String mDate) {this.mDate = mDate;}
    public void setmLocation(String mLocation) {this.mLocation = mLocation;}
    public void setmEventName(String mEventName) {this.mEventName = mEventName;}

    @Override
    public String toString()
    {
        return mArtist;
    }
}
