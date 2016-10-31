package com.main.zach_haowen.weatherapplication.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zach_haowen on 10/25/16.
 * extract and construct the weather information.
 */



public class CityWeatherInfo {

    private String mCity, mCountry;
    private WeatherInfoOfDay[] mWeatherInfoOfDay;
    private String rst;

    public CityWeatherInfo(String info) {
        try {
            JSONObject jsonObject = new JSONObject(info);
            JSONObject city = jsonObject.getJSONObject("city");
            this.mCity = city.getString("name");
            this.mCountry = city.getString("country");
            JSONArray list = jsonObject.getJSONArray("list");
            this.mWeatherInfoOfDay = new WeatherInfoOfDay[5];
            for (int i = 0; i < mWeatherInfoOfDay.length; i++) {
                JSONObject line = list.getJSONObject(i);
                JSONObject temps = line.getJSONObject("temp");
                double HighTemp = temps.getDouble("max");
                double LowTemp = temps.getDouble("min");
                int Humidity = line.getInt("humidity");
                JSONObject weathers = line.getJSONArray("weather").getJSONObject(0);
                String Weather = weathers.getString("main");
                String Description = weathers.getString("description");
                this.mWeatherInfoOfDay[i] = new WeatherInfoOfDay(Weather, Description, Humidity, LowTemp - 273.15, HighTemp - 273.15);
            }
            this.output();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getRst() {
        return rst;
    }

    public void output() {
        this.rst = "";
        this.rst += "city:" + this.mCity + "\n";
        this.rst += "country:" + this.mCountry + "\n";
        for(WeatherInfoOfDay w: this.mWeatherInfoOfDay) {
            this.rst += w.getAllInformation() + "\n";
        }
    }

    public WeatherInfoOfDay getWeatherInfoOfDay (int i) {
        return this.mWeatherInfoOfDay[i];
    }

    public String getCity() {
        return this.mCity;
    }

    public String getCountry() {
        return this.mCountry;
    }

}
