package com.chat.newchat.model.service;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;

import com.chat.newchat.ChatApplication;

public class LocationService extends IntentService {

    private static final String NAME = "LOCATION_SERVICE";
    private static final String USED = "USED";

    public LocationService() {
        super(NAME);
    }

    protected void onHandleIntent(Intent intent) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChatApplication.getInstance());
        if (preferences.contains(USED) && !preferences.getBoolean(USED, false)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // TODO: 2/4/17 послать на сервер? на UI? другому приложению?
                Location location = ((LocationManager) getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.GPS_PROVIDER);
                preferences.edit().putBoolean(USED, true).apply();
            }
        }
    }
}