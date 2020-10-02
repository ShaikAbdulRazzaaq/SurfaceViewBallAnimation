package com.learning.surfaceviewballanimation;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

class MyThread extends Thread {
    final SurfaceHolder surfaceHolder;
    boolean stop = false;
    MySurfaceView mySurfaceView;

    public MyThread(SurfaceHolder surfaceHolder, MySurfaceView mySurfaceView) {
        this.surfaceHolder = surfaceHolder;
        this.mySurfaceView = mySurfaceView;
    }

    @Override
    public void run() {
        while (!stop) {
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    mySurfaceView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}

