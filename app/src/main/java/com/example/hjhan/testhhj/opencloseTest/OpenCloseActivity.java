package com.example.hjhan.testhhj.opencloseTest;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;

import com.example.hjhan.testhhj.R;

import java.util.ArrayList;
import java.util.List;

/**
 * OpenCloseActivity
 *
 * @author prompt
 * @since 2017-11-10
 */
public class OpenCloseActivity extends Activity implements OpenCloseAdapter.ScrollPositionListener {

    private RecyclerView recyclerView;
    private OpenCloseAdapter openCloseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_close);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        init();
        addModelData();

    }

    @Override
    public void setScrollPosition(int position) {
        recyclerView.smoothScrollToPosition(position);
    }

    private void init() {
        openCloseAdapter = new OpenCloseAdapter(getApplicationContext(), this);

        recyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(getApplicationContext()));
        recyclerView.setAdapter(openCloseAdapter);
    }

    private void addModelData() {
        List<OpenCloseModel.Data.OpenCloseList> lists = new ArrayList<>();
        OpenCloseModel.Data.OpenCloseList list1 = new OpenCloseModel.Data.OpenCloseList();
        list1.setIdx(1);
        list1.setOpenFlag("N");
        list1.setName("일");
        list1.setDate(1510557383717L);
        OpenCloseModel.Data.OpenCloseList list2 = new OpenCloseModel.Data.OpenCloseList();
        list2.setIdx(2);
        list2.setOpenFlag("Y");
        list2.setName("이");
        list2.setDate(1510557373717L);
        OpenCloseModel.Data.OpenCloseList list3 = new OpenCloseModel.Data.OpenCloseList();
        list3.setIdx(3);
        list3.setOpenFlag("N");
        list3.setName("삼");
        list3.setDate(1509557363717L);
        OpenCloseModel.Data.OpenCloseList list4 = new OpenCloseModel.Data.OpenCloseList();
        list4.setIdx(4);
        list4.setOpenFlag("N");
        list4.setName("사");
        list4.setDate(1509557353717L);
        OpenCloseModel.Data.OpenCloseList list5 = new OpenCloseModel.Data.OpenCloseList();
        list5.setIdx(5);
        list5.setOpenFlag("Y");
        list5.setName("오");
        list5.setDate(1507284761194L);
        OpenCloseModel.Data.OpenCloseList list6 = new OpenCloseModel.Data.OpenCloseList();
        list6.setIdx(6);
        list6.setOpenFlag("Y");
        list6.setName("육");
        list6.setDate(1506284751194L);
        OpenCloseModel.Data.OpenCloseList list7 = new OpenCloseModel.Data.OpenCloseList();
        list7.setIdx(7);
        list7.setOpenFlag("Y");
        list7.setName("칠");
        list7.setDate(1506284741194L);
        OpenCloseModel.Data.OpenCloseList list8 = new OpenCloseModel.Data.OpenCloseList();
        list8.setIdx(8);
        list8.setOpenFlag("N");
        list8.setName("팔");
        list8.setDate(1506284731194L);
        OpenCloseModel.Data.OpenCloseList list9 = new OpenCloseModel.Data.OpenCloseList();
        list9.setIdx(9);
        list9.setOpenFlag("N");
        list9.setName("구");
        list9.setDate(1497007553316L);
        OpenCloseModel.Data.OpenCloseList list10 = new OpenCloseModel.Data.OpenCloseList();
        list10.setIdx(10);
        list10.setOpenFlag("Y");
        list10.setName("십");
        list10.setDate(1497005453316L);
        OpenCloseModel.Data.OpenCloseList list11 = new OpenCloseModel.Data.OpenCloseList();
        list11.setIdx(11);
        list11.setOpenFlag("Y");
        list11.setName("십일");
        list11.setDate(1497004353316L);
        OpenCloseModel.Data.OpenCloseList list12 = new OpenCloseModel.Data.OpenCloseList();
        list12.setIdx(12);
        list12.setOpenFlag("N");
        list12.setName("십이");
        list12.setDate(1497003253316L);
        OpenCloseModel.Data.OpenCloseList list13 = new OpenCloseModel.Data.OpenCloseList();
        list13.setIdx(13);
        list13.setOpenFlag("Y");
        list13.setName("십삼");
        list13.setDate(1494997160533L);
        OpenCloseModel.Data.OpenCloseList list14 = new OpenCloseModel.Data.OpenCloseList();
        list14.setIdx(14);
        list14.setOpenFlag("N");
        list14.setName("십사");
        list14.setDate(1494987150533L);
        OpenCloseModel.Data.OpenCloseList list15 = new OpenCloseModel.Data.OpenCloseList();
        list15.setIdx(15);
        list15.setOpenFlag("Y");
        list15.setName("십오");
        list15.setDate(1494957140533L);
        OpenCloseModel.Data.OpenCloseList list16 = new OpenCloseModel.Data.OpenCloseList();
        list16.setIdx(16);
        list16.setOpenFlag("N");
        list16.setName("십육");
        list16.setDate(1494917130533L);

        lists.add(0, list1);
        lists.add(1, list2);
        lists.add(2, list3);
        lists.add(3, list4);
        lists.add(4, list5);
        lists.add(5, list6);
        lists.add(6, list7);
        lists.add(7, list8);
        lists.add(8, list9);
        lists.add(9, list10);
        lists.add(10, list11);
        lists.add(11, list12);
        lists.add(12, list13);
        lists.add(13, list14);
        lists.add(14, list15);
        lists.add(15, list16);

        openCloseAdapter.setLists(lists);
        openCloseAdapter.notifyDataSetChanged();
    }

    public class LinearLayoutManagerWithSmoothScroller extends LinearLayoutManager {

        public LinearLayoutManagerWithSmoothScroller(Context context) {
            super(context, VERTICAL, false);
        }

        public LinearLayoutManagerWithSmoothScroller(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state,
                                           int position) {
            RecyclerView.SmoothScroller smoothScroller = new TopSnappedSmoothScroller(recyclerView.getContext());
            smoothScroller.setTargetPosition(position);
            startSmoothScroll(smoothScroller);
        }

        private class TopSnappedSmoothScroller extends LinearSmoothScroller {
            public TopSnappedSmoothScroller(Context context) {
                super(context);

            }

            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return LinearLayoutManagerWithSmoothScroller.this
                        .computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected int getVerticalSnapPreference() {
                return SNAP_TO_START;
            }
        }
    }
}
