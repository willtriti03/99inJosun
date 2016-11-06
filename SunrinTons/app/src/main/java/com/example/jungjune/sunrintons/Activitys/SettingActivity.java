package com.example.jungjune.sunrintons.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jungjune.sunrintons.R;

public class SettingActivity extends AppCompatActivity {
    protected ImageButton mHomeBtn;
    protected ImageButton mAdd;
    protected ImageButton mSetting;
    protected TextView mAlarmBtn;
    protected TextView mShowMyPostBtn;
    protected TextView mCallBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }
}
