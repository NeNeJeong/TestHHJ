package com.example.hjhan.testhhj.playerTest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.brandongogetap.stickyheaders.exposed.StickyHeaderListener;
import com.example.hjhan.testhhj.R;
import com.example.hjhan.testhhj.stickyHeaderTest.StickyHeaderAdapter;
import com.example.hjhan.testhhj.stickyHeaderTest.StickyHeaderTestModel;
import com.example.hjhan.testhhj.stickyHeaderTest.StickyTestModel;
import com.example.hjhan.testhhj.stickyHeaderTest.TopSnappedStickyLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * TabFragment1
 *
 * @author hjhan
 * @since 2017-11-16
 */

public class TabFragment1 extends Fragment {

    private RecyclerView recyclerView;
    private StickyHeaderAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_1, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.tabRecyclerView);

        adapter = new StickyHeaderAdapter();
        adapter.setData(addData());

        StickyLayoutManager layoutManager = new TopSnappedStickyLayoutManager(getContext(), adapter);
        layoutManager.elevateHeaders(1); // Default elevation of 5dp
        // You can also specify a specific dp for elevation
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        layoutManager.setStickyHeaderListener(new StickyHeaderListener() {
            @Override
            public void headerAttached(View headerView, int adapterPosition) {
                Log.d("Listener", "Attached with position: " + adapterPosition);
            }

            @Override
            public void headerDetached(View headerView, int adapterPosition) {
                Log.d("Listener", "Detached with position: " + adapterPosition);
            }
        });

        return view;
    }

    private List<StickyTestModel.Data.CardTypeList> addData() {
        List<StickyTestModel.Data.CardTypeList> list = new ArrayList<>();


        StickyHeaderTestModel list1 = new StickyHeaderTestModel();
        list1.setIdx(1);
        list1.setName("1111111111");
        list1.setCardType("SECTION");
        StickyTestModel.Data.CardTypeList list2 = new StickyTestModel.Data.CardTypeList();
        list2.setIdx(2);
        list2.setName("222222222");
        list2.setCardType(null);
        StickyHeaderTestModel list3 = new StickyHeaderTestModel();
        list3.setIdx(3);
        list3.setName("33333333");
        list3.setCardType("SECTION");
        StickyHeaderTestModel list4 = new StickyHeaderTestModel();
        list4.setIdx(4);
        list4.setName("444444444");
        list4.setCardType("SECTION");
        StickyTestModel.Data.CardTypeList list5 = new StickyTestModel.Data.CardTypeList();
        list5.setIdx(5);
        list5.setName("5555555");
        list5.setCardType(null);
        StickyHeaderTestModel list6 = new StickyHeaderTestModel();
        list6.setIdx(6);
        list6.setName("6666666");
        list6.setCardType("SECTION");
        StickyHeaderTestModel list7 = new StickyHeaderTestModel();
        list7.setIdx(7);
        list7.setName("777777");
        list7.setCardType("SECTION");
        StickyTestModel.Data.CardTypeList list8 = new StickyTestModel.Data.CardTypeList();
        list8.setIdx(8);
        list8.setName("888888");
        list8.setCardType(null);
        StickyTestModel.Data.CardTypeList list9 = new StickyTestModel.Data.CardTypeList();
        list9.setIdx(9);
        list9.setName("999999");
        list9.setCardType(null);
        StickyTestModel.Data.CardTypeList list10 = new StickyTestModel.Data.CardTypeList();
        list10.setIdx(10);
        list10.setName("10101010");
        list10.setCardType(null);
        StickyTestModel.Data.CardTypeList list11 = new StickyTestModel.Data.CardTypeList();
        list11.setIdx(11);
        list11.setName("111111111");
        list11.setCardType(null);


        list.add(list1);
        list.add(list2);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list3);
        list.add(list11);
        list.add(list4);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list6);
        list.add(list7);
        list.add(list8);
        list.add(list9);
        list.add(list10);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list11);


        return list;
    }
}
