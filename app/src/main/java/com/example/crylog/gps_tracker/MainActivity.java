package com.example.crylog.gps_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
Button Start_stop;
    TextView cs;
    TextView as;
    TextView time;
    cv custom_v;
    Boolean st = false;
    gps mygps;
    Stack<gps_data> gps_stack = new Stack<gps_data>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        custom_v = (cv)findViewById(R.id.cv);
        cs = (TextView)findViewById(R.id.cur_speed);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        custom_v.setLayoutParams(new LinearLayout.LayoutParams(width, width));
        Start_stop = (Button)findViewById(R.id.tracking);
        Start_stop.setOnClickListener(Start_or_stop);
    }
    View.OnClickListener Start_or_stop = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(st){
                st = !st;
                mygps.enable(1,1);
                Start_stop.setText("START TRACKING");
            }else{
                st = !st;
                mygps.desable();
                Start_stop.setText("STOP TRACKING");
            }
        }


    };
}
