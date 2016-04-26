package com.example.crylog.gps_tracker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements LocationListener {
    Button Start_stop;
    TextView cs;
    TextView as;
    TextView time;
    TextView tv_gpserino;
    cv custom_v;
    int current_time = 0;
    Boolean st = false;
    gps_data gpsd;
    boolean b = true;
    private LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        custom_v = (cv) findViewById(R.id.cv);
        as = (TextView)findViewById(R.id.av_speed);
        cs = (TextView) findViewById(R.id.cur_speed);
        tv_gpserino = (TextView)findViewById(R.id.tv_gps);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        custom_v.setLayoutParams(new LinearLayout.LayoutParams(width, width));
        Start_stop = (Button) findViewById(R.id.tracking);
        Start_stop.setOnClickListener(Start_or_stop);
        time = (TextView) findViewById(R.id.ov_time);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    View.OnClickListener Start_or_stop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (st) {
                st = false;
                desable_gps();
                Start_stop.setText("START TRACKING");
            } else {
                st = true;
                enable_gps();
                Start_stop.setText("STOP TRACKING");
            }
            Log.d("rekt", "onClick: not poney");
        }


    };

    private void desable_gps() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.removeUpdates(MainActivity.this);
        tv_gpserino.setText("GPS inactive");
    }

    private void enable_gps() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(lm.GPS_PROVIDER, 1000, 1, this);
        tv_gpserino.setText("GPS active");
    }

    @Override
    public void onLocationChanged(Location location) {
        //String str = "Latitude: "+location.getLatitude()+" Longitude: "+location.getLongitude() + "poney : " + custom_v.gps_stack.size() + "Speed : " + location.getSpeed();
        gpsd = new gps_data();
        time.setText(current_time + "s");
        current_time++;
        //gpsd.latitude = location.getLatitude();
        //gpsd.longitude = location.getLongitude();
        gpsd.speed = location.getSpeed()*(3.6);
        if(b) {
            if(custom_v.index != 99){
            custom_v.gps_arr[custom_v.index] = gpsd;
            custom_v.index ++;
            }else {
                custom_v.gps_arr[custom_v.index] = gpsd;
                b = false;
            }

        }else{
            for (int i = 0; i < 99; i++)
            {
                custom_v.gps_arr[i] = custom_v.gps_arr[i+1];
            }
            custom_v.gps_arr[99] = gpsd;
        }
        cs.setText("Current Speed: " + gpsd.speed);
        as.setText("Average Speed: " + custom_v.av_speed);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }
}
