package com.yozoe.pattern.command;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by wangdong on 16/7/10.
 */
public class DrawCanvas extends SurfaceView implements SurfaceHolder.Callback {

    public boolean isDrawing, isRunning;

    private Bitmap mBitmap;
    private DrawInvoker mInvoker;
    private DrawThread mThread;

    public DrawCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        mInvoker = new DrawInvoker();
        mThread = new DrawThread();

        getHolder().addCallback(this);
    }

    public void add(DrawPath path) {
        mInvoker.add(path);
    }

    public void redo() {
        isDrawing = true;
        mInvoker.redo();
    }

    public void undo() {
        isDrawing = true;
        mInvoker.undo();
    }

    public boolean canUndo() {
        return mInvoker.canUndo();
    }

    public boolean canRedo() {
        return mInvoker.canRedo();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = false;
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        isRunning = false;
        while (retry) {
            try {
                mThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class DrawThread extends Thread {

        @Override
        public void run() {
            Canvas canvas = null;
            while (isRunning) {
                canvas = getHolder().lockCanvas();
                if (mBitmap == null) {
                    mBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
                }
                Canvas c = new Canvas(mBitmap);
                c.drawColor(0, PorterDuff.Mode.CLEAR);
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                mInvoker.execute(canvas);
                canvas.drawBitmap(mBitmap, 0, 0, null);
            }
        }
    }
}
