package com.threebeards.ad340.fitquestlayouts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by bob on 5/29/16.
 */
public class WalkingSurfaceView extends SurfaceView implements Runnable {

    Paint p ;
    Rect src;
    RectF dest;
    Bitmap background;
    Bitmap sprite;


    public WalkingSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        sprite = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.sticksprite);
       // background = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.testbg);
        src = new Rect(0,0,1280, 720);
        dest = new RectF(src.left, src.top, src.right, src.bottom);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            dest.left += i;
            invalidate();
            try {
                this.wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDraw(Canvas c){
        super.onDraw(c);
        c.drawRect(src, p);
        c.drawBitmap(sprite, src, dest,p);
       // c.drawRect(new Rect(0,0,c.getWidth()/2, c.getHeight()/2),p );
    }
}
