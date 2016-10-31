package com.main.zach_haowen.weatherapplication.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.main.zach_haowen.weatherapplication.R;
import com.main.zach_haowen.weatherapplication.Sensor.LocationFinder;
import com.main.zach_haowen.weatherapplication.Util.SharedPrefsUtil;
import com.main.zach_haowen.weatherapplication.asynctask.DownloadWeatherAsyncTask;
import com.main.zach_haowen.weatherapplication.model.CityWeatherInfo;
import com.main.zach_haowen.weatherapplication.model.Settings;


public class WeatherActivity extends AppCompatActivity implements LocationFinder.LocationDetector, DownloadWeatherAsyncTask.CityWeatherInfoListener {

    private final String TAG = "WeatherActivity";
    private Settings mSettings;
    TextView textview;
    Button button;
    private final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;
    CityWeatherInfo mCityWeatherInfo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        mSettings = getSettings();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath().build());

        button = (Button)findViewById(R.id.button);

        View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postWeather();
            }
        };

        button.setOnClickListener(buttonOnClickListener);

        textview = (TextView) findViewById(R.id.output);
    }

    void postWeather() {
        LocationFinder locationFinder = new LocationFinder(this,this);
        locationFinder.detectLocation();
        this.button.setEnabled(false);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Log.d(TAG,"settings button pressed");
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.i(TAG, "ACCESS_FINE_LOCATION enabled");
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_ACCESS_FINE_LOCATION);
            }
        }
    }

    public Settings getSettings() {
        int day = SharedPrefsUtil.getValue(this, "day", 3);
        int method = SharedPrefsUtil.getValue(this, "method", 0);
        int degree = SharedPrefsUtil.getValue(this, "degree", 0);
        int language = SharedPrefsUtil.getValue(this, "language", 0);
        return new Settings(day, method, degree, language);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "ACCESS_FINE_LOCATION granted");
                } else {
                }
                break;
            }
        }
    }

    @Override
    public void InfoFound(CityWeatherInfo cityWeatherInfo) {
        mCityWeatherInfo = cityWeatherInfo;
        textview.setText(mCityWeatherInfo.getRst());
        this.button.setEnabled(true);
    }

    @Override
    public void InfoNotFound() {
        Log.i(TAG, "weathers not found");
        Toast.makeText(this, "weathers not found", Toast.LENGTH_SHORT).show();
        this.button.setEnabled(true);
    }

    @Override
    public void locationFound(Location location) {
        DownloadWeatherAsyncTask task = new DownloadWeatherAsyncTask(this, this);
        Log.i(TAG, "location found");
        task.execute(location);
        this.button.setEnabled(false);
    }

    @Override
    public void locationNotFound(LocationFinder.FailureReason failureReason) {
        Log.i(TAG, "location not found");
        Toast.makeText(this, "location not found", Toast.LENGTH_SHORT).show();
    }
}
