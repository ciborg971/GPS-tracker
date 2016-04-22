package com.example.crylog.gps_tracker;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by crylog on 22/04/16.
 */
public class gps extends Activity implements LocationListener {
private LocationManager lm;
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void enable (boolean b)
    {
        if(b)
        {
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            createDisplayContext()
        }else
        {

        }
    }
}
