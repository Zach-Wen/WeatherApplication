package com.main.zach_haowen.weatherapplication.model;

/**
 * Created by zach_haowen on 10/31/16.
 */

public class Settings {

    int mDay, mMethod, mDegree, mLanguage;

    public Settings(int Day, int Method, int Degree, int Language) {
        this.mDay = Day;
        this.mMethod = Method;
        this.mDegree = Degree;
        this.mLanguage = Language;
    }

    public int getDay () {
        return this.mDay;
    }

    public int getMethod () {
        return this.mMethod;
    }

    public int getDegree () {
        return this.mDegree;
    }

    public int getLanguage () {
        return this.mLanguage;
    }

}
