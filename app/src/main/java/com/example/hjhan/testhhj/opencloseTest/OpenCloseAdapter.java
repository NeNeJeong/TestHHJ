package com.example.hjhan.testhhj.opencloseTest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjhan.testhhj.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * OpenCloseAdapter
 *
 * @author prompt
 * @since 2017-11-10
 */
public class OpenCloseAdapter extends RecyclerView.Adapter<OpenCloseAdapter.OpenCloseHolder> {

    private Context context;
    private List<OpenCloseModel.Data.OpenCloseList> lists = new ArrayList<>();
    private ScrollPositionListener scrollPositionListener;

    public interface ScrollPositionListener {
        void setScrollPosition(int position);
    }

    @Override
    public OpenCloseAdapter.OpenCloseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_open_close, parent, false);
        return new OpenCloseHolder(view);
    }

    @Override
    public void onBindViewHolder(final OpenCloseAdapter.OpenCloseHolder holder, final int position) {
        holder.idxTextView.setText(lists.get(position).getIdx() + "");
        holder.nameTextView.setText(lists.get(position).getName());
        holder.dateTextView.setText(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date(lists.get(position).getDate())));


        //접히는 아이템
        if ("N".equals(lists.get(position).getOpenFlag())) {
            holder.nameTextView.setSelected(false);
            holder.nameTextView.setVisibility(View.GONE);
            holder.button.setText("OPEN");
        } else {
            holder.nameTextView.setSelected(true);
            holder.nameTextView.setVisibility(View.VISIBLE);
            holder.button.setText("CLOSE");
        }

        if ("close".equals(lists.get(position).getOpenShare())) {
            holder.nameTextView.setSelected(false);
            holder.nameTextView.setVisibility(View.GONE);
            holder.button.setText("OPEN");
        } else if ("open".equals(lists.get(position).getOpenShare())) {
            holder.nameTextView.setSelected(true);
            holder.nameTextView.setVisibility(View.VISIBLE);
            holder.button.setText("CLOSE");
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, lists.get(position).getIdx()+"", Toast.LENGTH_SHORT).show();
                if (holder.nameTextView.isSelected()) {
                    holder.nameTextView.setSelected(false);
                    holder.nameTextView.setVisibility(View.GONE);
                    holder.button.setText("OPEN");
                    lists.get(position).setOpenShare("close");
                } else {
                    holder.nameTextView.setSelected(true);
                    holder.nameTextView.setVisibility(View.VISIBLE);
                    holder.button.setText("CLOSE");
                    lists.get(position).setOpenShare("open");
                }
            }
        });



        //스크롤
        if (position == 0) {
            holder.selectTextView.setBackgroundColor(context.getResources().getColor(R.color.color_common_delete));
        } else {
            holder.selectTextView.setBackgroundColor(context.getResources().getColor(R.color.cyan));
        }

        if ("Y".equals(lists.get(position).getSelectFlag())) {
            holder.selectTextView.setBackgroundColor(context.getResources().getColor(R.color.color_common_delete));
        } else if ("N".equals(lists.get(position).getSelectFlag())) {
            holder.selectTextView.setBackgroundColor(context.getResources().getColor(R.color.cyan));
        }

        holder.buttonScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, lists.get(position).getIdx() + " click", Toast.LENGTH_SHORT).show();

                scrollPositionListener.setScrollPosition(position);
                for (int i = 0; i < lists.size(); i++) {
                    if (position == i) {
                        lists.get(position).setSelectFlag("Y");
                    } else {
                        lists.get(i).setSelectFlag("N");
                    }
                }
                notifyDataSetChanged();
            }
        });


        //날짜 테스트
        if (position == 0) {
            holder.dateTextView.setVisibility(View.VISIBLE);
        } else {
            if (new Date(lists.get(position-1).getDate()).getDate() == new Date(lists.get(position).getDate()).getDate()) {
                holder.dateTextView.setVisibility(View.GONE);
            } else {
                holder.dateTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public OpenCloseAdapter(Context context, ScrollPositionListener scrollPositionListener) {
        this.context = context;
        this.scrollPositionListener = scrollPositionListener;
    }

    public void setLists(List<OpenCloseModel.Data.OpenCloseList> lists) {
        this.lists = lists;
    }

    public class OpenCloseHolder extends RecyclerView.ViewHolder {

        TextView idxTextView;
        TextView nameTextView;
        Button button;
        Button buttonScroll;
        TextView selectTextView;
        TextView dateTextView;

        public OpenCloseHolder(View itemView) {
            super(itemView);
            idxTextView = (TextView)itemView.findViewById(R.id.idx_TextView);
            nameTextView = (TextView)itemView.findViewById(R.id.name_TextView);
            selectTextView = (TextView)itemView.findViewById(R.id.select_TextView);
            dateTextView = (TextView)itemView.findViewById(R.id.date_TextView);
            button = (Button)itemView.findViewById(R.id.button);
            buttonScroll = (Button)itemView.findViewById(R.id.button_scroll);
        }
    }
}
