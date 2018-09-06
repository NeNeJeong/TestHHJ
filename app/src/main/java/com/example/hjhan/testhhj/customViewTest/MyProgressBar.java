package com.example.hjhan.testhhj.customViewTest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.os.Handler;

import com.example.hjhan.testhhj.R;

/**
 * ProgressBarLayout
 *
 * @author hjhan
 * @since 2018-09-04
 */

public class MyProgressBar extends View {
    private int curValue;
    private int maxValue;
    private int lineColor;
    private Handler mHandler = new Handler();
    private int mProgress = 0;

    public MyProgressBar(Context context) {
        super(context);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyProgressBar, 0, 0);

        curValue = a.getInteger(R.styleable.MyProgressBar_curValue, 50);
        maxValue = a.getInteger(R.styleable.MyProgressBar_maxValue, 100);
        lineColor = a.getInteger(R.styleable.MyProgressBar_lineColor, 0xff000000);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = this.getMeasuredWidth();
        int height = this.getMeasuredHeight();

        Paint circle = getPaintObject();

        RectF rectF = new RectF();
        rectF.left = 0;
        rectF.right = canvas.getWidth();
        rectF.top = 0;
        rectF.bottom = canvas.getHeight();
//        canvas.drawArc(rectF, -90, (curValue / maxValue * 360), false, circle);
        canvas.drawArc(rectF, -90, ((float) curValue / (float)maxValue * 360), true, circle);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (curValue < maxValue){
                    curValue = curValue + 1;
                    invalidate();
                }
            }
        }, 100);

    }

    private Paint getPaintObject() {
        Paint circle = new Paint();
        circle.setColor(lineColor);
        circle.setAntiAlias(true);
        circle.setStyle(Paint.Style.FILL);

        return circle;
    }
}
