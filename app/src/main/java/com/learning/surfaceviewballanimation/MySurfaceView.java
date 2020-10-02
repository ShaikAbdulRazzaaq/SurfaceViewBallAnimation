package com.learning.surfaceviewballanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

class MySurfaceView extends SurfaceView implements Runnable {
    private final SurfaceHolder surfaceHolder;
    int width, height;
    Canvas canvas;
    int halfWidth, halfHeight, valueX;
    boolean running = false;
    Thread thread;
    private Paint paint = new Paint();

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                setWillNotDraw(false);
                width = getWidth();
                height = getHeight();
                halfWidth = width / 2;
                halfHeight = height / 2;
                valueX = halfWidth;
                workOnAnimations();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });
    }

    void workOnAnimations() {
        if (running) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (surfaceHolder.getSurface().isValid()) {
            paint.setColor(getResources().getColor(R.color.colorAccent));
            canvas.drawCircle(valueX, halfHeight, (float) halfWidth / 3, paint);
            invalidate();
        }
    }

    @Override
    public void run() {
        while (running) {
            canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                draw(canvas);
                valueX += 20;
                if (valueX >= width)
                    valueX = 0;
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}