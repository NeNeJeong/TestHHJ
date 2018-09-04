package com.example.hjhan.testhhj.stickyHeaderTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.brandongogetap.stickyheaders.exposed.StickyHeaderListener;
import com.example.hjhan.testhhj.R;


import java.util.ArrayList;
import java.util.List;

/**
 * StickyHeaderActivity
 *
 * @author hjhan
 * @since 2017-11-21
 */

public class StickyHeaderActivity extends AppCompatActivity {

    private StickyHeaderAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_test);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        adapter = new StickyHeaderAdapter();
        adapter.setData(addData());

        StickyLayoutManager layoutManager = new TopSnappedStickyLayoutManager(this, adapter);
        layoutManager.elevateHeaders(false); //Header에 그림자 넣을건지 안넣을겆니
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
        list4.setCardType(null);
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
        list.add(list11);
        list.add(list11);
        list.add(list11);
        list.add(list3);
        list.add(list11);
        list.add(list11);
        list.add(list11);
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
        list.add(list8);
        list.add(list8);
        list.add(list9);
        list.add(list9);
        list.add(list9);
        list.add(list9);
        list.add(list10);
        list.add(list10);
        list.add(list10);
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
        list.add(list11);
        list.add(list11);
        list.add(list11);


        return list;
    }



}