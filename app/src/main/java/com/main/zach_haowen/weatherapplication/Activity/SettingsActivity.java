package com.main.zach_haowen.weatherapplication.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.main.zach_haowen.weatherapplication.R;
import com.main.zach_haowen.weatherapplication.Util.SharedPrefsUtil;

public class SettingsActivity extends AppCompatActivity {

    private Context mContext;
    private Spinner mDaySpinner;
    private ArrayAdapter mDayAdapter;
    private Spinner mZipcodeLocationSpinner;
    private ArrayAdapter mZipcodeLocationAdapter;
    private Spinner mCelsiusFahrenheitSpinner;
    private ArrayAdapter mCelsiusFahrenheitAdapter;
    private Spinner mLanguageSpinner;
    private ArrayAdapter mLanguageAdapter;
    private Button mCommitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mContext = this;

        mDaySpinner = (Spinner) findViewById(R.id.day_spinner);
        mDayAdapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
        mDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDaySpinner.setAdapter(mDayAdapter);
        mDaySpinner.setVisibility(View.VISIBLE);
        int day = SharedPrefsUtil.getValue(this, "days", 2);
        mDaySpinner.setSelection(day, true);
        mDaySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SharedPrefsUtil.putValue(mContext, "days", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SharedPrefsUtil.putValue(mContext, "days", 2);
            }
        });

        mZipcodeLocationSpinner = (Spinner) findViewById(R.id.zipcode_location_spinner);
        mZipcodeLocationAdapter = ArrayAdapter.createFromResource(this, R.array.method, android.R.layout.simple_spinner_item);
        mZipcodeLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mZipcodeLocationSpinner.setAdapter(mZipcodeLocationAdapter);
        mZipcodeLocationSpinner.setVisibility(View.VISIBLE);
        int method = SharedPrefsUtil.getValue(mContext, "method", 0);
        mZipcodeLocationSpinner.setSelection(method, true);
        mZipcodeLocationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SharedPrefsUtil.putValue(mContext, "method", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SharedPrefsUtil.putValue(mContext, "method", 0);
            }
        });

        mCelsiusFahrenheitSpinner = (Spinner) findViewById(R.id.celsius_fahrenheit_spinner);
        mCelsiusFahrenheitAdapter = ArrayAdapter.createFromResource(this, R.array.degree, android.R.layout.simple_spinner_item);
        mCelsiusFahrenheitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCelsiusFahrenheitSpinner.setAdapter(mCelsiusFahrenheitAdapter);
        mCelsiusFahrenheitSpinner.setVisibility(View.VISIBLE);
        int degree = SharedPrefsUtil.getValue(this, "degree", 0);
        mCelsiusFahrenheitSpinner.setSelection(degree, true);
        mCelsiusFahrenheitSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SharedPrefsUtil.putValue(mContext, "degree", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SharedPrefsUtil.putValue(mContext, "degree", 0);
            }
        });

        mLanguageSpinner = (Spinner) findViewById(R.id.language_spinner);
        mLanguageAdapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        mLanguageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguageSpinner.setAdapter(mLanguageAdapter);
        mLanguageSpinner.setVisibility(View.VISIBLE);
        int language = SharedPrefsUtil.getValue(this, "language", 0);
        mLanguageSpinner.setSelection(language, true);
        mLanguageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SharedPrefsUtil.putValue(mContext, "language", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SharedPrefsUtil.putValue(mContext, "language", 0);
            }
        });


    }
}
