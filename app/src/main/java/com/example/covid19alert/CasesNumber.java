package com.example.covid19alert;

public class CasesNumber {

    private String mCountry;
    private int mRecovered;
    private int mDeath;
    private int mActivecases;

    public CasesNumber(){}

    public String getmCountry() {
        return mCountry;
    }

    public int getmRecovered() {
        return mRecovered;
    }

    public int getmDeath() {
        return mDeath;
    }

    public int getmActivecases() {
        return mActivecases;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public void setmRecovered(int mRecovered) {
        this.mRecovered = mRecovered;
    }

    public void setmDeath(int mDeath) {
        this.mDeath = mDeath;
    }

    public void setmActivecases(int mActivecases) {
        this.mActivecases = mActivecases;
    }
}
