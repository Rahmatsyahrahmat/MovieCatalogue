package com.rahmatsyah.moviecatalogue.view.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.service.notification.AlarmReceiver;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private SharedPreferences sharedPreferences;
    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        TextView tvLanguageSetting = findViewById(R.id.tvLanguageSetting);
        Switch switchDailyReminder = findViewById(R.id.switchDailyReminder);
        Switch switchReleaseTodayReminder = findViewById(R.id.switchReleaseTodayReminder);
        sharedPreferences = getSharedPreferences(getString(R.string.shared_preference_key),MODE_PRIVATE);
        alarmReceiver = new AlarmReceiver();

        if (String.valueOf(getResources().getConfiguration().locale).equals("en_US")){
            tvLanguageSetting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.united_states, 0, 0, 0);
        }
        else if (String.valueOf(getResources().getConfiguration().locale).equals("in_ID")){
            tvLanguageSetting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.indonesia, 0, 0, 0);
        }

        if (sharedPreferences.getBoolean(getString(R.string.daily_reminder_preference),false)){
            switchDailyReminder.setChecked(true);
        }
        if (sharedPreferences.getBoolean(getString(R.string.release_today_reminder_preference),false)){
            switchReleaseTodayReminder.setChecked(true);
        }


        tvLanguageSetting.setOnClickListener(this);
        switchDailyReminder.setOnCheckedChangeListener(this);
        switchReleaseTodayReminder.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(mIntent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (compoundButton.getId()){
            case R.id.switchDailyReminder:
                if (b){
                    alarmReceiver.setRepeatingAlarm(this,AlarmReceiver.TYPE_DAILY_REMINDER,getString(R.string.daily_reminder_time),getString(R.string.daily_reminder_message));
                }
                else {
                    alarmReceiver.cancelAlarm(this,AlarmReceiver.TYPE_DAILY_REMINDER);
                }
                editor.putBoolean(getString(R.string.daily_reminder_preference),b);
                break;
            case R.id.switchReleaseTodayReminder:
                if (b){
                    alarmReceiver.setRepeatingAlarm(this,AlarmReceiver.TYPE_RELEASE_TODAY_REMINDER,getString(R.string.release_reminder_time),"");
                }
                else {
                    alarmReceiver.cancelAlarm(this,AlarmReceiver.TYPE_RELEASE_TODAY_REMINDER);
                }
                editor.putBoolean(getString(R.string.release_today_reminder_preference),b);
                break;
        }
        editor.apply();
    }
}
