package com.example.hjhan.testhhj.playerTest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hjhan.testhhj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TabFragment1
 *
 * @author hjhan
 * @since 2017-11-16
 */

public class TabFragment3 extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    EmptyAdapter emptyAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_2, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;

    }

    public void init(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyAdapter = new EmptyAdapter(getContext());
        recyclerView.setAdapter(emptyAdapter);
        emptyAdapter.notifyDataSetChanged();
    }

    public class EmptyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        Context mContext;

        public EmptyAdapter(Context context){
            mContext = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new EmptyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_empty,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }


    private class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View view) {
            super(view);

        }
    }


}
