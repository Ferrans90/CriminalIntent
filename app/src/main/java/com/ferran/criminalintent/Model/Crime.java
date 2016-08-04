package com.ferran.criminalintent.Model;

import android.content.ContentValues;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Crime {
    private UUID mID;
    private String mTitle;
    private GregorianCalendar mDate;
    private boolean mSolved;

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mID = id;
        mDate = new GregorianCalendar();
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public GregorianCalendar getDate() {
        return mDate;
    }

    public void setDate(GregorianCalendar mDate) {
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

}
