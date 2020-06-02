package com.example.covid19alert;

public class CasesNumber {

    private String mCountry;
    private String mRecovered;

    private String mDeath;
    private String mActivecases;

    public CasesNumber(){}

    public String getmCountry() {
        return mCountry;
    }

    public String getmRecovered() {
        return mRecovered;
    }

    public String getmDeath() {
        return mDeath;
    }

    public String getmActivecases() {
        return mActivecases;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public void setmRecovered(String mRecovered) {
        this.mRecovered = mRecovered;
    }

    public void setmDeath(String mDeath) {
        this.mDeath = mDeath;
    }

    public void setmActivecases(String mActivecases) {
        this.mActivecases = mActivecases;
    }
}
