package com.yozoe.androidnote;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.yozoe.pattern.command.DrawCanvas;
import com.yozoe.pattern.command.DrawPath;
import com.yozoe.pattern.command.IBrush;
import com.yozoe.pattern.command.NormalBrush;

public class DrawActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawCanvas mCanvas;
    private DrawPath mPath;
    private Paint mPaint;
    private IBrush mBrush;
    private Button btnRedo, btnUndo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);


        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(3);

        mBrush = new NormalBrush();

        mCanvas = (DrawCanvas) findViewById(R.id.ac_draw_canvas);
        mCanvas.setOnTouchListener(new DrawTouchListener());

        btnRedo = (Button) findViewById(R.id.ac_draw_operate_redo_btn);
        btnUndo = (Button) findViewById(R.id.ac_draw_operate_undo_btn);

        btnRedo.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_draw_operate_redo_btn:
                mCanvas.redo();
                if (!mCanvas.canRedo()) {
                    btnRedo.setEnabled(false);
                }
                btnUndo.setEnabled(true);
                break;
            case R.id.ac_draw_operate_undo_btn:
                mCanvas.undo();
                if (!mCanvas.canUndo()) {
                    btnUndo.setEnabled(false);
                }
                btnRedo.setEnabled(true);
                break;
        }
    }

    private class DrawTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mPath = new DrawPath();
                mPath.paint = mPaint;
                mPath.path = new Path();
                mBrush.down(mPath.path, event.getX(), event.getY());
            }
            else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                mBrush.move(mPath.path, event.getX(), event.getY());
            }
            else if (event.getAction() == MotionEvent.ACTION_UP) {
                mBrush.up(mPath.path, event.getX(), event.getY());
                mCanvas.add(mPath);
                mCanvas.isDrawing = true;

                btnUndo.setEnabled(true);
                btnRedo.setEnabled(false);
            }

            return true;
        }
    }
}
