package com.example.hjhan.testhhj.testLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hjhan.testhhj.R;

import java.lang.reflect.Field;

/**
 * TabViewPagerActivity
 *
 * @author hjhan
 * @since 2017-11-16
 */

public class TabViewPagerActivity extends FragmentActivity {

    TabLayout tabLayout;
    ViewPager viewpager;
    LinearLayout menuLayout;
    Button button;
    boolean isPageOpen;
    TextView drawerTextView;

    Animation translateLeftAnim;
    Animation translateRightAnim;

    int mLastPosition = 0; //마지막 position 값

    int positionSum = 0; //position 합계

    boolean mIsZeroTabSwipe = false;//swipe 가능한지 여부 확인
    boolean mIsFirstTabl = true;//첫번쨰 탭인지 여부 확인

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_viewpager_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        menuLayout = (LinearLayout) findViewById(R.id.menu_Layout);
        button = (Button) findViewById(R.id.button);
        drawerTextView = (TextView) findViewById(R.id.drawer_TextView);

        //region 첫번째 viewpager에서 옆으로 당겼을때 나오는 레이아웃 애니메이션 set
        translateLeftAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ani_test_translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ani_test_translate_right);

        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animationListener);
        translateRightAnim.setAnimationListener(animationListener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPageOpen) {
                    //애니메이션 시작
                    menuLayout.startAnimation(translateRightAnim);
                }
                //열기
                else {
                    menuLayout.setVisibility(View.VISIBLE);
                    menuLayout.startAnimation(translateLeftAnim);
                }

            }
        });

        //endregion


        //region tab레이아웃 설정
        tabLayout.addTab(tabLayout.newTab().setIcon(getApplicationContext().getResources().getDrawable(R.drawable.ac_ic_noti)));
        tabLayout.addTab(tabLayout.newTab().setIcon(getApplicationContext().getResources().getDrawable(R.drawable.ac_ic_back)));
        tabLayout.addTab(tabLayout.newTab().setIcon(getApplicationContext().getResources().getDrawable(R.drawable.ac_ic_close)));

        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //endregion

        viewpager.setAdapter(tabViewPagerAdapter);

        //페이지 이동될때 스크롤 이벤트
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.e("test","onPageScrolled");

                StringBuilder sb = new StringBuilder();
                sb.append(" position : ");
                sb.append("" + position);
                sb.append(" positionOffset : ");
                sb.append("" + positionOffset);
                sb.append(" positionOffsetPixels : ");
                sb.append("" + positionOffsetPixels);
                //Log.e("onPageScrolled " , sb.toString());

                //첫번째 탭이고 모든 포지션 값이 0일 경우에 swipe 가능
                if (mIsFirstTabl) {
                    if ((position == 0) &&
                            (positionOffset == 0) &&
                            (positionOffsetPixels == 0))
                        mIsZeroTabSwipe = true;
                    else
                        mIsZeroTabSwipe = false;
                }

                //첫번째 탭일때 0, 두번째 탭일때 1, 세번쨰 탭일때 2
                //마지막에 onPageScrollStateChanged 호출될때 사용됨
                positionSum += position + positionOffset + positionOffsetPixels;
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("position", position + "");

                //현재 position 과 변경될 position 비교
                if (mLastPosition > position) {
                    Log.e("Swiped Right", "Swiped Right");
                } else {
                    Log.e("Swiped Left", "Swiped Left");
                }

                //position 이 0 이고 오른쪽으로 당겼을때
                if (position == 0 && mLastPosition > position) {
                    mIsFirstTabl = true;
                } else {
                    mIsFirstTabl = false;
                }

                mLastPosition = position;//현재 position 저장
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Log.e("test","onPageScrollStateChanged");
                /*
                0 : SCROLL_STATE_IDLE
                1 : SCROLL_STATE_DRAGGING
                2 : SCROLL_STATE_SETTLING

                IDLE = 뜻은 가동되지 않은 (즉 종료시점이라고 볼 수 있겠죠)
                DRAGGING = 뜻 그대로 드래그 되고 있다 (Swipe 될 때 호출됩니다. 조금 있다가 확인)
                SETTLING = 뜻은 고정(정해졌다)이라는 뜻이죠

                출처: http://itpangpang.xyz/290 [ITPangPang]*/

                Log.e("state ", "" + state);
                if (mIsFirstTabl && positionSum == 0 && state == 0) {
                    //swipe 애니메이션 실행
//                    Toast.makeText(getApplicationContext(),"!!!!!!!",Toast.LENGTH_LONG).show();
                } else {
                    //첫번째 탭이 아닌 다른 페이지일때 다시 초기화
                    mIsZeroTabSwipe = false;
                    positionSum = 0;
                }

            }
        });

        //region DrawerLayout 터치영역을 넓혀준다

        try {
            DrawerLayout mDrawerLayout = (CustomDrawerLayout) findViewById(R.id.drawer);
            Field mDragger = findUnderlying(mDrawerLayout.getClass(), "mLeftDragger");//상위 클래스에 fieldName을 찾아야하는 경우 findUnderlying함수 사용
            mDragger.setAccessible(true);

            ViewDragHelper draggerObj = (ViewDragHelper) mDragger.get(mDrawerLayout);
            Field mEdgeSize = draggerObj.getClass().getDeclaredField("mEdgeSize");//클래스에서 fieldName 찾을떄
            mEdgeSize.setAccessible(true);
            int edge = mEdgeSize.getInt(draggerObj);

            mEdgeSize.setInt(draggerObj, edge * 5); //optimal value as for me, you may set any constant in dp
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //endregion
    }

    //상위 클래스에 fieldName을 찾아야하는 경우
    public Field findUnderlying(Class<?> clazz, String fieldName) {
        Class<?> current = clazz;
        do {
            try {
                return current.getDeclaredField(fieldName);
            } catch (Exception e) {
            }
        } while ((current = current.getSuperclass()) != null);
        return null;
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationEnd(Animation animation) {
            //슬라이드 열기->닫기
            if (isPageOpen) {
                menuLayout.setVisibility(View.INVISIBLE);
                button.setText("Open");
                isPageOpen = false;
            }
            //슬라이드 닫기->열기
            else {
                button.setText("Close");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationStart(Animation animation) {

        }
    }

}
