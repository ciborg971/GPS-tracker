package com.example.crylog.gps_tracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by crylog on 21/04/16.
 */
public class cv extends View {
int width = 1;
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

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        width = w;
        super.onSizeChanged(w,h,oldw,oldh);
    }
}
