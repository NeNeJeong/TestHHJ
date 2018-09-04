package com.example.hjhan.testhhj.playerTest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.CheckBox;

import com.example.hjhan.testhhj.R;

/**
 * PlayerLayoutActivity
 *
 * @author hjhan
 * @since 2017-11-15
 */

public class PlayerLayoutActivity extends Activity {

    CoordinatorLayout.Behavior mBehavior;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_layout);


        NestedScrollView scroll_view = (NestedScrollView)findViewById(R.id.scroll_view);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)scroll_view.getLayoutParams();
        mBehavior = params.getBehavior();

    }

    public void openHandle(View target){

        CheckBox toggle_ChekBox = (CheckBox)findViewById(R.id.toggle_ChekBox);
        if(toggle_ChekBox.isChecked()){
            ((View)findViewById(R.id.toggle_Layout)).setVisibility(View.VISIBLE);
        }
        else{
            ((View)findViewById(R.id.toggle_Layout)).setVisibility(View.GONE);
        }


    }

    public void testToggle(View target){
        NestedScrollView scroll_view = (NestedScrollView)findViewById(R.id.scroll_view);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)scroll_view.getLayoutParams();
        params.setBehavior(null);


        CollapsingToolbarLayout collapsingToolbarLayout   = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbarLayout01);
        collapsingToolbarLayout.setVisibility(View.GONE);

    }



}
