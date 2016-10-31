package com.main.zach_haowen.weatherapplication.Sensor;

import com.main.zach_haowen.weatherapplication.model.CityWeatherInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAPIInformation {

    private String mAPPID = "c12478cbb26c3fa4e94f98b912903645";
//    private int mDay;
    private CityWeatherInfo mCityWeatherInfo;
//    private HttpURLConnection mHttpURLConnection;

    public GetAPIInformation(double longitude, double latitude) {
        this.mCityWeatherInfo = new CityWeatherInfo(this.queryJSONOnLocation(longitude, latitude));
    }

    public GetAPIInformation (int day, String Zipcode) {
        this.mCityWeatherInfo = new CityWeatherInfo(this.queryJSONOnZipcode(Zipcode));
    }

    private String getmAPIID() {
        return this.mAPPID;
    }

    private String queryJSONOnLocation( double longitude, double latitude ) {
        String request = "";
        request += "http://api.openweathermap.org/data/2.5/forecast/daily?";
        request += "lat="+ latitude + "&";
        request += "lon="+ longitude + "&";
        request += "appid=" + this.getmAPIID();
        return this.queryURL(request);
    }

    private String queryJSONOnZipcode(String Zipcode) {
        String request = "";
        request += "http://api.openweathermap.org/data/2.5/forecast/daily?";
        request += "q="+ Zipcode + ",us&";
        request += "appid=" + this.getmAPIID();
        return this.queryURL(request);
    }

    private String queryURL(String request) {
        try {
            URL url = new URL(request);
            HttpURLConnection connection;
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String rst = "";
            String temp = "";
            while(temp != null) {
                temp = bufferedReader.readLine();
                rst += temp;
            }
            return rst;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public CityWeatherInfo queryCityWeatherInfo() {
        return this.mCityWeatherInfo;
    }

    public String output() {
        return this.mCityWeatherInfo.getRst();
    }

}