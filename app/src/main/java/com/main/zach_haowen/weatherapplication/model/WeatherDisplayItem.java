package com.main.zach_haowen.weatherapplication.model;

import android.graphics.drawable.Drawable;

/**
 * Created by zach_haowen on 10/31/16.
 */

public class WeatherDisplayItem {

    Drawable mImage;
    String mTemperature, mDescription, mHumidity;

    public WeatherDisplayItem (WeatherInfoOfDay weatherInfoOfDay, int degree){
        double HighTemp = weatherInfoOfDay.getHighTemp();
        double LowTemp = weatherInfoOfDay.getLowTemp();
        if (degree == 0) {
            mTemperature = Math.round(LowTemp) + " ~ " + Math.round(HighTemp) + "ºC";
        }
        else {
            mTemperature = Math.round(LowTemp * 1.8 + 32) + " ~ " + Math.round(HighTemp * 1.8 + 32) + "ºF";
        }
        mDescription = weatherInfoOfDay.getDescription();
        mHumidity = weatherInfoOfDay.getHumidity() + "%";
    }

}
