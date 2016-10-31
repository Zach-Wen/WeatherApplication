package com.main.zach_haowen.weatherapplication.model;

/**
 * Created by zach_haowen on 10/31/16.
 */

public class WeatherInfoOfDay {
    private String mWeather;
    private String mDescription;
    private int mHumidity;
    private double mLowTemp, mHighTemp;

    public WeatherInfoOfDay (String Weather, String Description, int Humidity, double LowTemp, double HighTemp) {
        this.mWeather = Weather;
        this.mDescription = Description;
        this.mHumidity = Humidity;
        this.mLowTemp = LowTemp;
        this.mHighTemp = HighTemp;
    }

    public String getWeather() {
        return this.mWeather;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public double getHighTemp() {
        return this.mHighTemp;
    }

    public double getLowTemp() {
        return this.mLowTemp;
    }

    public int getHumidity() {
        return this.mHumidity;
    }

    public String getAllInformation () {
        String rst = "";
        rst += "Weather: " + this.mWeather + "\n";
        rst += "Description: " + this.mDescription + "\n";
        rst += "Humidity: " + this.mHumidity + "\n";
        rst += "temprature: " + (int)Math.round(this.mLowTemp) + " to " + (int)Math.round(this.mHighTemp) + "\n";
        return rst;
    }

}
