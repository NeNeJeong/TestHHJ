package com.example.hjhan.testhhj.testLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.hjhan.testhhj.R;
import com.example.hjhan.testhhj.stickyHeaderTest.sticky.DataSetAdapter;
import com.example.hjhan.testhhj.stickyHeaderTest.sticky.model.BoardListItem;
import com.example.hjhan.testhhj.stickyHeaderTest.sticky.model.Person;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TabFragment1
 *
 * @author hjhan
 * @since 2017-11-16
 */

public class TabFragment2 extends Fragment {

    DataSetAdapter adapter = new DataSetAdapter();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_2, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new StickyHeaderLayoutManager());
        adapter.setPeople(getBoardItemList());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public List<BoardListItem> getBoardItemList() {
        List<BoardListItem> boardListItems = new ArrayList<BoardListItem>();
        boardListItems.add(new BoardListItem("board1", "section1", 0));
        boardListItems.add(new BoardListItem("board2", "section1", 0));
        boardListItems.add(new BoardListItem("board3", "section1", 0));
        boardListItems.add(new BoardListItem("board4", "section1", 0));
        boardListItems.add(new BoardListItem("board5", "section1", 0));
        boardListItems.add(new BoardListItem("board6", "section1", 0));
        boardListItems.add(new BoardListItem("board7", "section1", 0));
        boardListItems.add(new BoardListItem("board8", "section1", 0));

        boardListItems.add(new BoardListItem("board1", "section2", 1));
        boardListItems.add(new BoardListItem("board2", "section2", 1));
        boardListItems.add(new BoardListItem("board3", "section2", 1));
        boardListItems.add(new BoardListItem("board4", "section2", 1));
        boardListItems.add(new BoardListItem("board5", "section2", 1));
        boardListItems.add(new BoardListItem("board6", "section2", 1));
        boardListItems.add(new BoardListItem("board7", "section2", 1));
        boardListItems.add(new BoardListItem("board8", "section2", 1));

        boardListItems.add(new BoardListItem("board1", "section333333", 2));
        boardListItems.add(new BoardListItem("board2", "section333333", 2));
        boardListItems.add(new BoardListItem("board3", "section333333", 2));
        boardListItems.add(new BoardListItem("board4", "section333333", 2));
        boardListItems.add(new BoardListItem("board5", "section333333", 2));
        boardListItems.add(new BoardListItem("board6", "section333333", 2));
        boardListItems.add(new BoardListItem("board7", "section333333", 2));
        boardListItems.add(new BoardListItem("board8", "section333333", 2));

        boardListItems.add(new BoardListItem("board2", "section444", 3));
        boardListItems.add(new BoardListItem("board3", "section444", 3));
        boardListItems.add(new BoardListItem("board4", "section444", 3));
        boardListItems.add(new BoardListItem("board5", "section444", 3));
        boardListItems.add(new BoardListItem("board6", "section444", 3));
        boardListItems.add(new BoardListItem("board7", "section444", 3));
        boardListItems.add(new BoardListItem("board8", "section444", 3));


        return boardListItems;
    }

    public List<Person> addUser() {
        List<Person> test = new ArrayList<>();
        test.add(new Person(new Person.Name("23424", "adfddsf", "11111")));
        test.add(new Person(new Person.Name("22", "adfddsf", "22222")));
        test.add(new Person(new Person.Name("22", "adfddsf1", "22222")));
        test.add(new Person(new Person.Name("22", "adfddsf2", "22222")));
        test.add(new Person(new Person.Name("22", "adfddsf3", "22222")));
        test.add(new Person(new Person.Name("22", "adfddsf4", "22222")));

        test.add(new Person(new Person.Name("33", "bdfddsf", "3333")));
        test.add(new Person(new Person.Name("44", "cdfddsf1", "4444")));
        test.add(new Person(new Person.Name("55", "ddfddsf2", "5555")));
        test.add(new Person(new Person.Name("55", "ddfddsf3", "5555")));
        test.add(new Person(new Person.Name("55", "ddfddsf4", "5555")));
        test.add(new Person(new Person.Name("55", "ddfddsf5", "5555")));
        test.add(new Person(new Person.Name("55", "ddfddsf6", "5555")));

        test.add(new Person(new Person.Name("66", "edfddsf", "6666")));
        test.add(new Person(new Person.Name("77", "fdfddsf", "7777")));
        test.add(new Person(new Person.Name("88", "gdfddsf1", "8888")));
        test.add(new Person(new Person.Name("88", "gdfddsf2", "8888")));
        test.add(new Person(new Person.Name("88", "gdfddsf3", "8888")));
        test.add(new Person(new Person.Name("88", "gdfddsf4", "8888")));
        test.add(new Person(new Person.Name("88", "gdfddsf5", "8888")));
        test.add(new Person(new Person.Name("88", "gdfddsf6", "8888")));

        test.add(new Person(new Person.Name("99", "hdfddsf", "9999")));


        return test;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
