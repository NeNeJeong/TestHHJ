package com.example.hjhan.testhhj.stickyHeaderTest.sticky;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;


import com.example.hjhan.testhhj.R;
import com.example.hjhan.testhhj.stickyHeaderTest.sticky.model.BoardListItem;
import com.example.hjhan.testhhj.stickyHeaderTest.sticky.model.Person;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Shows a fake addressbook listing, loaded from randomuser.me, where the people are sorted
 * into sections by the first letter of last name.
 */
public class AddressBookDemoActivity extends Activity {

    private static final String TAG = AddressBookDemoActivity.class.getSimpleName();
    DataSetAdapter adapter = new DataSetAdapter();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_2);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new StickyHeaderLayoutManager());
        adapter.setPeople(getBoardItemList());
        recyclerView.setAdapter(adapter);

    }

    public List<BoardListItem> getBoardItemList() {
        List<BoardListItem> boardListItems = new ArrayList<BoardListItem>();
        boardListItems.add(new BoardListItem("board1", "section1", 0,"text"));
        boardListItems.add(new BoardListItem("board2", "section1", 0,"image"));
        boardListItems.add(new BoardListItem("board3", "section1", 0,"text"));
        boardListItems.add(new BoardListItem("board4", "section1", 0,"text"));
        boardListItems.add(new BoardListItem("board5", "section1", 0,"text"));
        boardListItems.add(new BoardListItem("board6", "section1", 0,"text"));
        boardListItems.add(new BoardListItem("board7", "section1", 0,"image"));
        boardListItems.add(new BoardListItem("board8", "section1", 0,"text"));

        boardListItems.add(new BoardListItem("board1", "section2", 1,"text"));
        boardListItems.add(new BoardListItem("board2", "section2", 1,"text"));
        boardListItems.add(new BoardListItem("board3", "section2", 1,"image"));
        boardListItems.add(new BoardListItem("board4", "section2", 1,"text"));
        boardListItems.add(new BoardListItem("board5", "section2", 1,"text"));
        boardListItems.add(new BoardListItem("board6", "section2", 1,"image"));
        boardListItems.add(new BoardListItem("board7", "section2", 1,"text"));
        boardListItems.add(new BoardListItem("board8", "section2", 1,"text"));

        boardListItems.add(new BoardListItem("board1", "section333333", 2,"text"));
        boardListItems.add(new BoardListItem("board2", "section333333", 2,"text"));
        boardListItems.add(new BoardListItem("board3", "section333333", 2,"image"));
        boardListItems.add(new BoardListItem("board4", "section333333", 2,"text"));
        boardListItems.add(new BoardListItem("board5", "section333333", 2,"text"));
        boardListItems.add(new BoardListItem("board6", "section333333", 2,"text"));
        boardListItems.add(new BoardListItem("board7", "section333333", 2,"image"));
        boardListItems.add(new BoardListItem("board8", "section333333", 2,"text"));

        boardListItems.add(new BoardListItem("board2", "section444", 3,"text"));
        boardListItems.add(new BoardListItem("board3", "section444", 3,"text"));
        boardListItems.add(new BoardListItem("board4", "section444", 3,"text"));
        boardListItems.add(new BoardListItem("board5", "section444", 3,"text"));
        boardListItems.add(new BoardListItem("board6", "section444", 3,"image"));
        boardListItems.add(new BoardListItem("board7", "section444", 3,"text"));
        boardListItems.add(new BoardListItem("board8", "section444", 3,"text"));


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
    protected void onResume() {
        super.onResume();
        /*progressBar.setVisibility(View.VISIBLE);
		recyclerView.setVisibility(View.GONE);
		getRandomUserLoader().load(this);*/
    }
}
