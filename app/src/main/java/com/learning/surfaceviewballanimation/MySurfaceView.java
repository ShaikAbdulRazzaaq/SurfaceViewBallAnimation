package com.learning.surfaceviewballanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

class MySurfaceView extends SurfaceView {
    private final SurfaceHolder surfaceHolder;
    int width, height;
    int halfWidth, halfHeight, valueX;
    MyThread myThread;
    private Paint paint = new Paint();

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        myThread = new MyThread(getHolder(), this);
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                if (myThread != null)
                    myThread.start();
                setWillNotDraw(false);
                width = getWidth();
                height = getHeight();
                halfWidth = width / 2;
                halfHeight = height / 2;
                valueX = halfWidth;
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                myThread.stop = true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (surfaceHolder.getSurface().isValid()) {
            paint.setColor(getResources().getColor(R.color.colorAccent));
            while(valueX==width){
                valueX++;
            }
            canvas.drawCircle(valueX, halfHeight, (float) halfWidth / 3, paint);
            invalidate();
        }
    }

}