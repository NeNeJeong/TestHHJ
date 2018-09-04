package com.example.hjhan.testhhj;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * GestureActivity
 *
 * @author hjhan
 * @since 2018-08-28
 */

public class GestureActivity extends Activity {

    TextView textView1 = null;
    TextView textView2 = null;
    TextView textView3 = null;
    TextView textView4 = null;
    TextView textView5 = null;
    TextView textView6 = null;
    TextView textView7 = null;
    TextView textView8 = null;
    TextView textView9 = null;
    GestureDetector detector = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gesture);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);

        View touchView = findViewById(R.id.touchView);
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float currentX = event.getX();
                float currentY = event.getX();

                if(action == MotionEvent.ACTION_DOWN){
                    textView1.setText("Touch Down: [" + currentX + " * " +  currentY + "]");
                } else if(action == MotionEvent.ACTION_MOVE){
                    textView2.setText("Touch Move: [" + currentX + " * " +  currentY + "]");
                } else if(action == MotionEvent.ACTION_UP){
                    textView3.setText("Touch Up: [" + currentX + " * " +  currentY + "]");
                }


                return true;
            }
        });

        View gestureView = findViewById(R.id.gestureView);
        gestureView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                textView4.setText("Gesture Detector: onDown Call");
                return true;
            }
            @Override
            public void onShowPress(MotionEvent motionEvent) {
                textView5.setText("Gesture Detector: onShowPress Call");
            }
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                textView6.setText("Gesture Detector: onSingleTapUp Call");
                return true;
            }
            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                textView7.setText("Gesture Detector: onScroll Call [" + v + " * " +  v1 + "]");
                return true;
            }
            @Override
            public void onLongPress(MotionEvent motionEvent) {
                textView8.setText("Gesture Detector: onLongPress Call");
            }
            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                textView9.setText("Gesture Detector: onFling Call [" + v + " * " +  v1 + "]");
                return true;
            }
        });
    }
}
