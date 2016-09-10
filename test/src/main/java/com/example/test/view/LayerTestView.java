package com.example.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangdong on 16/9/9.
 */
public class LayerTestView extends View {

    private static final int LAYER_FLAGS =
                    Canvas.MATRIX_SAVE_FLAG |
                    Canvas.CLIP_SAVE_FLAG |
                    Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                    Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                    Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    public LayerTestView(Context context) {
        super(context);
    }

    public LayerTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, paint);

        canvas.saveLayerAlpha(0, 0 ,400, 400, 127, LAYER_FLAGS);
        paint.setColor(Color.RED);
        canvas.drawCircle(200, 200, 100, paint);
        canvas.restore();
    }
}
