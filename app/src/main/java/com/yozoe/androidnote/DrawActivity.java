package com.yozoe.androidnote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yozoe.pattern.command.DrawCanvas;

public class DrawActivity extends AppCompatActivity {

    DrawCanvas mCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        mCanvas = (DrawCanvas) findViewById(R.id.ac_draw_canvas);

        mCanvas.setOnTouchListener(new DrawTouchListener());
    }

    private class DrawTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d("hehe", "down");
            }
            else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                Log.d("hehe", "move");
            }
            else if (event.getAction() == MotionEvent.ACTION_UP) {
                Log.d("hehe", "up");
            }

            return true;
        }
    }
}
