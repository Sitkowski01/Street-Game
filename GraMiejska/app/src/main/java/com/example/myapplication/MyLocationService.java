package com.example.myapplication;
import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

public class MyLocationService extends Service implements LocationListener {

    private LocationManager locationManager;
    private final long MIN_TIME_BETWEEN_UPDATES = 1000; // minimalny czas między aktualizacjami lokalizacji
    private final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // minimalna odległość między aktualizacjami lokalizacji

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startGettingLocation();
        return START_STICKY;
    }

    private void startGettingLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BETWEEN_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
    }

    private void stopGettingLocation() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        // obsługa zmiany lokalizacji
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopGettingLocation();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}