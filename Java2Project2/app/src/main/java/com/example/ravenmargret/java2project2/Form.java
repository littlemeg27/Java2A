/**
 * Created by Brenna Pavlinchak on 12/6/15.
 */

package com.example.ravenmargret.java2project2;

import java.io.Serializable;

public class Form implements Serializable
{
    private static final long serialVersionUID = 8736847634070552888L;
    String mFirstName;
    String mLastName;
    String mJob;
    String mAge;

    public Form(String mFirstName, String mLastName, String mJob, String mAge)
    {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mJob = mJob;
        this.mAge = mAge;
    }

    public String getmFirstName() {return mFirstName;}
    public String getmLastName() {return mLastName;}
    public String getmJob() {return mJob;}
    public String getmAge() {return mAge;}


    public void setmFirstName(String mFirstName) {this.mFirstName = mFirstName;}
    public void setmLastName(String mLastName) {this.mLastName = mLastName;}
    public void setmJob(String mJob) {this.mJob = mJob;}
    public void setmAge(String mAge) {this.mAge = mAge;}

    @Override
    public String toString()
    {
        return mFirstName;
    }
}
