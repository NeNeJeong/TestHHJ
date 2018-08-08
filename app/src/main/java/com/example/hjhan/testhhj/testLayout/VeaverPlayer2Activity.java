package com.example.hjhan.testhhj.testLayout;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjhan.testhhj.R;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VeaverPlayer2Activity extends AppCompatActivity {

    @BindView(R.id.movie_TexttureView)
    TextureView mMovieTexttureView;

    @BindView(R.id.movieTopLeft_Layout)
    RelativeLayout mMovieTopLeftLayout;
    @BindView(R.id.movieTopRight_Layout)
    View mMovieTopRightLayout;
    @BindView(R.id.movieTop_Layout)
    LinearLayout mMovieTopLayout;
    @BindView(R.id.movieBottom_Layout)
    View mMovieBottomLayout;
    @BindView(R.id.movieRoot_Layout)
    LinearLayout mMovieRootLayout;
    @BindView(R.id.tabTop_Layout)
    View mTabTopLayout;
    @BindView(R.id.tabBottomLeft_Layout)
    View mTabBottomLeftLayout;
    @BindView(R.id.tabBottomRight_Layout)
    RelativeLayout mTabBottomRightLayout;
    @BindView(R.id.tabBottom_Layout)
    LinearLayout mTabBottomLayout;
    @BindView(R.id.tabRoot_Layout)
    LinearLayout mTabRootLayout;

    SimpleExoPlayer mExoPlayer;
    @BindView(R.id.toggle_ChekBox)
    CheckBox toggleChekBox;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.drawer)
    CustomDrawerLayout drawerLayout;
    @BindView(R.id.drawer_Button)
    Button drawerButton;
    @BindView(R.id.drawer_TextView)
    TextView drawerTextView;
    @BindView(R.id.touchLayout)
    Button touchLayout;
    @BindView(R.id.behavior_Layout)
    RelativeLayout behaviorLayout;

    private CoordinatorLayout.Behavior mBehavior;

    private TabFragment1 tabFragment1;
    private TabFragment2 tabFragment2;
    private TabFragment3 tabFragment3;

    private DataSource.Factory mMediaDataSourceFactory;
    private Handler mHandler;
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private MappingTrackSelector mTrackSelector;
    private CacheDataSourceFactory mCacheDataSourceFactory;


    private static final int UI_MODE_FULL_MOVIE = 0;
    private static final int UI_MODE_MOVIE_TAB = 1;
    private static final int UI_MODE_MINI_MOVIE = 2;
    private static final int UI_MODE_OPEN_TAB = 3;
    private static final int UI_MODE_CLOSE_TAB = 4;

    private int mFirstUIMode = UI_MODE_MOVIE_TAB;
    private int mCurrentUIMode = mFirstUIMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);
        ButterKnife.bind(this);
        init();
        initVideo();
        initTab();

        //region CoordinatorLayout에서 title영역 숨기고 스크롤 했을때 발생되는 이슈 처리
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) behaviorLayout.getLayoutParams();
        mBehavior = new AppBarLayout.ScrollingViewBehavior();
        params.setBehavior(mBehavior);//title영역 보일때 Behavior set해준다. title영역 없을떄는 null
        //endregion


        //region 뷰의 터치 영역을 넓힌다.
        final View parent = (View) drawerButton.getParent();
        parent.post(new Runnable() {
            @Override
            public void run() {
                final Rect r = new Rect();
                drawerButton.getHitRect(r);
                r.top -= 100;
                r.bottom += 100;
                r.left -= 100;
                r.right += 100;

                parent.setTouchDelegate(new TouchDelegate(r, drawerButton));
            }
        });
        //endregion

        //region DrawerLayout 터치영역을 넓혀준다.
        try{
            Field mDragger = findUnderlying(drawerLayout.getClass(), "mLeftDragger");//상위 클래스에 fieldName을 찾아야하는 경우 findUnderlying함수 사용
            mDragger.setAccessible(true);

            ViewDragHelper draggerObj = (ViewDragHelper) mDragger.get(drawerLayout);
            Field mEdgeSize = draggerObj.getClass().getDeclaredField("mEdgeSize");//클래스에서 fieldName 찾을떄 getDeclaredField 사용
            mEdgeSize.setAccessible(true);

            int edge = mEdgeSize.getInt(draggerObj);
            mEdgeSize.setInt(draggerObj, edge * 5); //optimal value as for me, you may set any constant in dp

        } catch(NoSuchFieldException e){
            e.printStackTrace();
        } catch(IllegalAccessException e){
            e.printStackTrace();
        }
        //endregion

        //버튼 클릭시 DrawerLayout 열리고 닫히는 동작해야함
        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(drawerTextView)){
                    drawerLayout.closeDrawer(drawerTextView);
                } else{
                    drawerLayout.openDrawer(drawerTextView);
                }
            }
        });
    }

    //상위 클래스에 fieldName을 찾아야하는 경우
    public Field findUnderlying(Class<?> clazz, String fieldName) {
        Class<?> current = clazz;
        do{
            try{
                return current.getDeclaredField(fieldName);
            } catch(Exception e){
            }
        } while((current = current.getSuperclass()) != null);
        return null;
    }

    //화면 전환
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        switch(newConfig.orientation){
            case Configuration.ORIENTATION_PORTRAIT:
                // 세로 화면 생성
                collapsingToolbarLayout.setVisibility(View.VISIBLE);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                // 가로 화면 생성
                collapsingToolbarLayout.setVisibility(View.GONE);
                break;
        }
    }

    public void init() {
        if(mHandler == null){
            mHandler = new Handler();
        }

        if(mMediaDataSourceFactory == null){
            mMediaDataSourceFactory = buildDataSourceFactory(true);
        }

        if(mCacheDataSourceFactory == null){
            mCacheDataSourceFactory = new CacheDataSourceFactory(this, 100 * 1024 * 1024, 5 * 1024 * 1024);
        }

        if(mTrackSelector == null){
            TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(BANDWIDTH_METER);
            mTrackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        }

        mMovieTexttureView = (TextureView) findViewById(R.id.movie_TexttureView);
        setUIMode();
    }

    //title 설명부분 열림 닫힘
    public void openHandle(View target) {
        CheckBox toggle_ChekBox = (CheckBox) findViewById(R.id.toggle_ChekBox);
        if(toggle_ChekBox.isChecked()){
            ((View) findViewById(R.id.toggle_Layout)).setVisibility(View.VISIBLE);
        } else{
            ((View) findViewById(R.id.toggle_Layout)).setVisibility(View.GONE);
        }
    }

    public void btnHandle(View target) {

        // 영상 100 %
        if(target.getId() == R.id.test1_Button){
            if(mCurrentUIMode == UI_MODE_OPEN_TAB){
                mMovieTopLeftLayout.clearAnimation();
            }
            if(mCurrentUIMode == UI_MODE_MINI_MOVIE){
                Toast.makeText(getApplicationContext(), "미니모드에선 영상/Tab 모드로만 전환 가능.", Toast.LENGTH_SHORT).show();
                return;
            }
            mCurrentUIMode = UI_MODE_FULL_MOVIE;
        }
        // 미니 모드
        if(target.getId() == R.id.test2_Button){
            if(mCurrentUIMode == UI_MODE_OPEN_TAB){
                mMovieTopLeftLayout.clearAnimation();
            }
            mCurrentUIMode = UI_MODE_MINI_MOVIE;
        }
        // 세로 모드(영상 아래 탭)
        if(target.getId() == R.id.test3_Button){
            if(mCurrentUIMode == UI_MODE_OPEN_TAB){
                mMovieTopLeftLayout.clearAnimation();
            }
            mCurrentUIMode = UI_MODE_MOVIE_TAB;
        }
        // 기로 모드(영상 옆 탭)
        if(target.getId() == R.id.test4_Button){
            if(mCurrentUIMode == UI_MODE_FULL_MOVIE || mCurrentUIMode == UI_MODE_CLOSE_TAB){
                mCurrentUIMode = UI_MODE_OPEN_TAB;
            } else{
                Toast.makeText(getApplicationContext(), "전체 모드에서 만 가능", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(target.getId() == R.id.test5_Button){
            if(mCurrentUIMode == UI_MODE_OPEN_TAB){
                mCurrentUIMode = UI_MODE_CLOSE_TAB;
            } else{
                Toast.makeText(getApplicationContext(), "분할 모드에서 만 가능", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        setUIMode();
    }


    public void setUIMode() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) behaviorLayout.getLayoutParams();

        switch(mCurrentUIMode){
            //region UI_MODE_FULL_MOVIE
            case UI_MODE_FULL_MOVIE:
                mMovieRootLayout.setVisibility(View.VISIBLE);
                mMovieTopLayout.setVisibility(View.VISIBLE);
                mMovieBottomLayout.setVisibility(View.GONE);
                mMovieTopLeftLayout.setVisibility(View.VISIBLE);
                mMovieTopRightLayout.setVisibility(View.GONE);
                mMovieTexttureView.setX(0);
                mMovieTexttureView.setY(0);
                mMovieTopRightLayout.setVisibility(View.GONE);
                mTabRootLayout.setVisibility(View.GONE);
                params.setBehavior(mBehavior);
                collapsingToolbarLayout.setVisibility(View.VISIBLE);

                break;
            //endregion
            //region UI_MODE_MOVIE_TAB
            case UI_MODE_MOVIE_TAB:

                mMovieRootLayout.setVisibility(View.VISIBLE);
                mMovieTopLayout.setVisibility(View.VISIBLE);
                mMovieBottomLayout.setVisibility(View.VISIBLE);
                mMovieTopLeftLayout.setVisibility(View.VISIBLE);
                mMovieTopRightLayout.setVisibility(View.GONE);
                mTabRootLayout.setVisibility(View.VISIBLE);
                mTabTopLayout.setVisibility(View.VISIBLE);
                mTabBottomLayout.setVisibility(View.VISIBLE);
                mTabBottomLeftLayout.setVisibility(View.GONE);
                mTabBottomRightLayout.setVisibility(View.VISIBLE);
                params.setBehavior(mBehavior);
                collapsingToolbarLayout.setVisibility(View.VISIBLE);


                mMovieTexttureView.setOnTouchListener(null);
                mMovieTexttureView.setX(0);
                mMovieTexttureView.setY(0);
                RelativeLayout.LayoutParams movieFullLayoutParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                movieFullLayoutParam.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                mMovieTexttureView.setLayoutParams(movieFullLayoutParam);
                mMovieTexttureView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                    @Override
                    public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                        Log.e("onLayoutChange", "mMovieTexttureView onLayoutChange");
                        Log.e("tab layout", "  left : " + left + "  top : " + top + "  right : " + right + "  oldTop : " + oldTop + "  oldLeft : " + oldLeft + "  oldRight : " + oldRight + "  oldBottom : " + oldBottom);

                        mMovieTexttureView.removeOnLayoutChangeListener(this);
                        mMovieTexttureView.setX(0);
                        mMovieTexttureView.setY(0);
                    }
                });
                mMovieTexttureView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 200);

                //mMovieTexttureView.setX(0);
                //mMovieTexttureView.setY(0);
                /*mMovieTexttureView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RelativeLayout.LayoutParams movieFullLayoutParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                        mMovieTexttureView.setLayoutParams(movieFullLayoutParam);
                    }
                },200);*/
                /*mMovieTexttureView.setOnTouchListener(null);
                RelativeLayout.LayoutParams movieFullLayoutParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                movieFullLayoutParam.setMargins(0,0,0,0);
                mMovieTexttureView.setLayoutParams(movieFullLayoutParam);
                mMovieTexttureView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        *//*
                        mMovieRootLayout.setVisibility(View.VISIBLE);
                        mMovieTopLayout.setVisibility(View.VISIBLE);
                        mMovieBottomLayout.setVisibility(View.VISIBLE);
                        mMovieTopLeftLayout.setVisibility(View.VISIBLE);
                        mMovieTopRightLayout.setVisibility(View.GONE);
                        mTabRootLayout.setVisibility(View.VISIBLE);
                        mTabTopLayout.setVisibility(View.VISIBLE);
                        mTabBottomLayout.setVisibility(View.VISIBLE);
                        mTabBottomLeftLayout.setVisibility(View.GONE);
                        mTabBottomRightLayout.setVisibility(View.VISIBLE);*//*
                    }
                },100);*/


                break;
            //endregion
            //region UI_MODE_MINI_MOVIE
            case UI_MODE_MINI_MOVIE:
                mMovieRootLayout.setVisibility(View.VISIBLE);
                mMovieTopLayout.setVisibility(View.VISIBLE);
                mMovieBottomLayout.setVisibility(View.GONE);
                mMovieTopLeftLayout.setVisibility(View.VISIBLE);
                mMovieTopRightLayout.setVisibility(View.GONE);
                RelativeLayout.LayoutParams movieMiniLayoutParam = new RelativeLayout.LayoutParams(660, 660);
                movieMiniLayoutParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                mMovieTexttureView.setY(60);
                mMovieTexttureView.setLayoutParams(movieMiniLayoutParam);
                mMovieTexttureView.setOnTouchListener(movieOnTouchListener);
                mMovieRootLayout.bringToFront();
                params.setBehavior(null);
                collapsingToolbarLayout.setVisibility(View.GONE);

                mTabRootLayout.setVisibility(View.VISIBLE);
                mTabTopLayout.setVisibility(View.GONE);
                mTabBottomLayout.setVisibility(View.VISIBLE);
                mTabBottomLeftLayout.setVisibility(View.GONE);
                mTabBottomRightLayout.setVisibility(View.VISIBLE);
                break;
            //endregion
            //region UI_MODE_OPEN_TAB
            case UI_MODE_OPEN_TAB:
                mMovieRootLayout.setVisibility(View.VISIBLE);
                mMovieTopLayout.setVisibility(View.VISIBLE);
                mMovieBottomLayout.setVisibility(View.GONE);
                mMovieTopLeftLayout.setVisibility(View.VISIBLE);
                mMovieTopRightLayout.setVisibility(View.GONE);
                mMovieRootLayout.bringToFront();
                mTabRootLayout.setVisibility(View.VISIBLE);
                mTabTopLayout.setVisibility(View.GONE);
                mTabBottomLayout.setVisibility(View.VISIBLE);
                mTabBottomLeftLayout.setVisibility(View.VISIBLE);
                mTabBottomRightLayout.setVisibility(View.VISIBLE);
                params.setBehavior(null);
                collapsingToolbarLayout.setVisibility(View.GONE);

                mTabBottomRightLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animation movieAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ani_width_100to50);
                        movieAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                //mMovieTopRightLayout.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        mMovieTopLeftLayout.startAnimation(movieAnimation);

                        Animation tabAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ani_x_right_in);
                        tabAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        mTabBottomRightLayout.startAnimation(tabAnimation);
                    }
                }, 200);
                break;
            //endregion
            //region UI_MODE_CLOSE_TAB
            case UI_MODE_CLOSE_TAB:
                mTabBottomRightLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animation movieAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ani_width_50to100);
                        movieAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                Log.e("test", "onAnimationStart");
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                Log.e("test", "onAnimationEnd");
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        mMovieTopLeftLayout.startAnimation(movieAnimation);

                        Animation tabAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ani_x_out_right);
                        tabAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                Log.e("test", "onAnimationStart");
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                Log.e("test", "onAnimationEnd 11111");
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        mTabBottomRightLayout.startAnimation(tabAnimation);
                    }
                }, 200);
                break;
            //endregion
        }
    }

    public void initVideo() {
        try{
            mMovieTexttureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                @Override
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                    Log.e("TEST", "mainVideo onSurfaceTextureAvailable");
                    MediaSource mediaSource = buildMediaSource(Uri.parse("https://d3kuwcirgjs0xs.cloudfront.net/video/801/file_1492661117885.mp4?Expires=1508933494&Signature=EnGX0OhaylXteCxQB97vsTkvcUVRme7KrH3J7toJORzp08JAI3bM6CBawWfuwd8ppOMoKcKsy0oU7xgtoJSNwfsq~Cf9HNNkeidwNRrb4m1XZ5SV4W3xBaI4-IFNwwfjXhcq5TyslgZ1QMMqoOW4jkUn17JAaLjPAVCVNkabgSxdZlIXh4YcFKV23hAmhrE7lJOYQ~7hRsg3OtF~OusllGCm2iV86ugMbwX0Z8i7o3Dun12Gi641IubUKTZbqPvMRpYZ2EDH9KvHVjv2TeCOAZWeFtT7JK16jNMUyfj6Cm4wyEQNpHXgq-~CkNagmxQiElWAIT0laiubg6lS3faAPA__&Key-Pair-Id=APKAIEHR574N3DWZSKNA"), null);
                    mExoPlayer = ExoPlayerFactory.newSimpleInstance(getApplicationContext(), mTrackSelector, new DefaultLoadControl());
                    mExoPlayer.setVideoSurface(new Surface(mMovieTexttureView.getSurfaceTexture()));
                    mExoPlayer.prepare(mediaSource);
                    mExoPlayer.setPlayWhenReady(true);
                }

                @Override
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

                }

                @Override
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    return false;
                }

                @Override
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

                }
            });

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void initTab() {
        tabFragment1 = new TabFragment1();
        tabFragment2 = new TabFragment2();
        tabFragment3 = new TabFragment3();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_frameLayout, tabFragment1)
                .add(R.id.fragment_frameLayout, tabFragment2)
                .add(R.id.fragment_frameLayout, tabFragment3)
                .hide(tabFragment2)
                .hide(tabFragment3)
                .commitAllowingStateLoss();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setIcon(getApplicationContext().getResources().getDrawable(R.drawable.ac_ic_noti)));
        tabLayout.addTab(tabLayout.newTab().setIcon(getApplicationContext().getResources().getDrawable(R.drawable.ac_ic_back)));
        tabLayout.addTab(tabLayout.newTab().setIcon(getApplicationContext().getResources().getDrawable(R.drawable.ac_ic_close)));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // tab 터치시 해당 fragment 를 보여준다.
                if(tab.getPosition() == 0){
                    fragmentVisible(tabFragment1);
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                } else if(tab.getPosition() == 1){
                    fragmentVisible(tabFragment2);
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                } else{
                    fragmentVisible(tabFragment3);
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void fragmentVisible(Fragment fragment) {
        try{
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(tabFragment1)
                    .hide(tabFragment2)
                    .hide(tabFragment3)
                    .show(fragment)
                    .commitAllowingStateLoss();
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        return buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }

    private DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(getApplicationContext().getApplicationContext(), bandwidthMeter, buildHttpDataSourceFactory(bandwidthMeter));
    }

    private HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultHttpDataSourceFactory(Util.getUserAgent(getApplicationContext().getApplicationContext(), "ExoVideoView"), bandwidthMeter);
    }

    private MediaSource buildMediaSource(Uri uri, String overrideExtension) {
        int type = Util.inferContentType(!TextUtils.isEmpty(overrideExtension) ? "." + overrideExtension
                : uri.getLastPathSegment());
        switch(type){
            case C.TYPE_SS:
                return new SsMediaSource(uri, buildDataSourceFactory(false),
                        new DefaultSsChunkSource.Factory(mMediaDataSourceFactory), mHandler, null);
            case C.TYPE_DASH:
                return new DashMediaSource(uri, buildDataSourceFactory(false),
                        new DefaultDashChunkSource.Factory(mMediaDataSourceFactory), mHandler, null);
            case C.TYPE_HLS:
                return new HlsMediaSource(uri, mMediaDataSourceFactory, mHandler, null);
            case C.TYPE_OTHER:
                //Cache cache = new SimpleCache(getApplicationContext().getCacheDir(), new LeastRecentlyUsedCacheEvictor(1024 * 1024 * 10));

                return new ExtractorMediaSource(uri, mCacheDataSourceFactory, new DefaultExtractorsFactory(),
                        mHandler, null);
            default:{
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }

    class CacheDataSourceFactory implements DataSource.Factory {
        private final Context context;
        private final DefaultDataSourceFactory defaultDatasourceFactory;
        private final long maxFileSize, maxCacheSize;

        CacheDataSourceFactory(Context context, long maxCacheSize, long maxFileSize) {
            super();
            this.context = context;
            this.maxCacheSize = maxCacheSize;
            this.maxFileSize = maxFileSize;
            String userAgent = Util.getUserAgent(context, context.getString(R.string.app_name));
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            defaultDatasourceFactory = new DefaultDataSourceFactory(this.context,
                    bandwidthMeter,
                    new DefaultHttpDataSourceFactory(userAgent, bandwidthMeter));
        }

        @Override
        public DataSource createDataSource() {
            LeastRecentlyUsedCacheEvictor evictor = new LeastRecentlyUsedCacheEvictor(maxCacheSize);
            SimpleCache simpleCache = new SimpleCache(new File(context.getCacheDir(), "media"), evictor);
            return new CacheDataSource(simpleCache, defaultDatasourceFactory.createDataSource(),
                    new FileDataSource(), new CacheDataSink(simpleCache, maxFileSize),
                    CacheDataSource.FLAG_BLOCK_ON_CACHE | CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR, null);
        }
    }

    View.OnTouchListener movieOnTouchListener = new View.OnTouchListener() {
        private float childX;
        private float childY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int width = v.getWidth();
            int height = v.getHeight();

            if(event.getAction() == MotionEvent.ACTION_DOWN){

                Log.e("TEST", "event.getX() : " + event.getX() + " event.getY() : " + event.getY());
                childX = event.getX();
                childY = event.getY();
                Log.e("TEST", "child X : " + childX + " child Y : " + childY);
            } else if(event.getAction() == MotionEvent.ACTION_MOVE){
                Log.e("TEST", "event.getRawX() : " + event.getRawX() + " event.getRawY() : " + event.getRawY());
                View parent = (View) v.getParent();
                int[] location = new int[2];
                parent.getLocationOnScreen(location);

                Log.e("TEST", "onScreen location x : " + location[0] + "location y : " + location[1]);

                v.setX(event.getRawX() - childX);
                v.setY(event.getRawY() - (childY + location[1]));

                if(v.getX() < parent.getLeft()){
                    v.setX(parent.getLeft());
                } else if(parent.getRight() < (v.getX() + width)){
                    v.setX(parent.getRight() - width);
                }

                if(v.getY() < parent.getTop()){
                    v.setY(parent.getTop());
                } else if(parent.getBottom() < (v.getY() + height)){
                    v.setY(parent.getBottom() - height);
                }

                Log.e("TEST", "onScreen location x : " + location[0] + "\n location y : " + location[1]);


            }
            return true;
        }
    };
}
