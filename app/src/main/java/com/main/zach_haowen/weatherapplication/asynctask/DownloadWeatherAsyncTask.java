package com.main.zach_haowen.weatherapplication.asynctask;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;

import com.main.zach_haowen.weatherapplication.Sensor.GetAPIInformation;
import com.main.zach_haowen.weatherapplication.model.CityWeatherInfo;


public class DownloadWeatherAsyncTask extends AsyncTask<Location, Integer, CityWeatherInfo>{

    private static final String TAG = "LocationFinder";
    private Context mContext;
    private int mDay;
    private CityWeatherInfoListener mCityWeatherInfoListener;


    public interface CityWeatherInfoListener{
        void InfoFound(CityWeatherInfo cityWeatherInfo);
        void InfoNotFound();
    }

    public DownloadWeatherAsyncTask(Context context, CityWeatherInfoListener cityWeatherInfoListener) {
        this.mContext = context;
        this.mCityWeatherInfoListener = cityWeatherInfoListener;
    }

//    public void setCompletionListener(CityWeatherInfoListener listener) {
//        mCityWeatherInfoListener = listener;
//    }

    @Override
    protected CityWeatherInfo doInBackground(Location... params) {
        double longitude = params[0].getLongitude();
        double latitude = params[0].getLatitude();
        return new GetAPIInformation(longitude, latitude).queryCityWeatherInfo();
    }

    @Override
    protected void onPostExecute(CityWeatherInfo cityWeatherInfo) {
        super.onPostExecute(cityWeatherInfo);
        if(mCityWeatherInfoListener != null) {
            mCityWeatherInfoListener.InfoFound(cityWeatherInfo);
        }
        else {
            mCityWeatherInfoListener.InfoNotFound();
        }
    }

}
