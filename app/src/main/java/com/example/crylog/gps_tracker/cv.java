package com.example.crylog.gps_tracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by crylog on 21/04/16.
 */
public class cv extends View {
int width = 1;
   int index = 0;
    Double av_speed = 1.0*width;
    gps_data [] gps_arr = new gps_data[100];
    Double cur_x = 0.0;
    Double cur_y = width * 1.0;
    Double next_y = 0.0;
    public cv(Context context, AttributeSet att) {
        super(context, att);
        init();
    }

    private void init() {

    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint pt = new Paint();
        pt.setStyle(Paint.Style.FILL);
        pt.setColor(Color.BLACK);
        canvas.drawPaint(pt);
        pt.setColor(Color.WHITE);
        for(int i = 0; i < 6; i++) {
            canvas.drawLine(0, i*width / 6, width, i*width / 6, pt);
        }


        pt.setStyle(Paint.Style.STROKE);

        pt.setColor(Color.GREEN);
        av_speed = 0.0;
        for(int i = 0; i < index ; i++)
        {
                next_y = (gps_arr[i].speed / 60) * width;
                canvas.drawLine(cur_x.floatValue(), cur_y.floatValue(), i * (width / index), width - next_y.floatValue(), pt);
                cur_x = i * (width / (index)) * 1.0;
                cur_y = width - next_y;
                av_speed+=gps_arr[i].speed;
        }
        cur_x = 0.0;
        cur_y = width * 1.0;
        av_speed = av_speed/(index+1);
        Double temp = width - (av_speed/60)*width;
        pt.setColor(Color.RED);
        canvas.drawLine(0,temp.floatValue(),width,temp.floatValue(),pt);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        width = w;
        super.onSizeChanged(w,h,oldw,oldh);
        Log.d("cv", "onSizeChanged: " + width/6);
    }
}
