package com.threebeardsmobile.fitquest.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.threebeardsmobile.fitquest.R;

/**
 * Created by bob on 5/29/16.
 */
public class WalkingView extends View{

    Paint p ;
    Rect src;
    RectF dest;
    Bitmap background;
    Bitmap sprite;
    SurfaceHolder holder;

    int height, width;
    int steps, goal;
    Paint textPaint;
    String message;

    public WalkingView(Context context, int stepGoal) {
        super(context);

        height = this.getHeight();
        width = this.getWidth();
        Log.d("!!BOB!!", "WalkingView: WxH: " + width + "x" + height);

        p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.GRAY);

        background = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.testbg);
        src = new Rect(1,1,100, 100);
        dest = new RectF(src.left, src.top, src.right, src.bottom);
        //this.setBackgroundResource(R.drawable.testbg);

        steps = 0;

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(60);
        textPaint.setTextAlign(Paint.Align.CENTER);
        message = "This is a test message";

        goal = stepGoal;

    }


    public void animateSprite(int prevSteps, int newSteps, int goal) {
        final int dx = (int) (Math.round((newSteps - prevSteps) * (double)(getWidth()/goal)));

        new Thread(
                new Runnable() {
                @Override
                public void run() {
                    for (int i = steps; i < getWidth(); i+=3){
                        steps = i;
                        try {
                            Thread.sleep(10);
                            postInvalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
        }}).start();
        steps = 0;


    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        // Draw background, text View and initial state
        float w, h, cx, cy;
        w = getWidth();
        h = getHeight();
        cx = steps;
        cy = h / 1.5f;

        c.drawRect(10, 10, w - 10, (h / 4) - 10, p);
        c.drawText(message, w/2, h/8, textPaint);

        Bitmap myBitmap = BitmapFactory.decodeResource(
                getResources(),
                R.drawable.sticksprite);
        c.drawBitmap(myBitmap, cx, cy, p);

        c.drawRect(10, h-40, 15, h-20, p);
        c.drawRect(w-15, h-40, w-10, h-20, p);
        c.drawRect(10, h-20, w-10, h-10, p);


    }

}
