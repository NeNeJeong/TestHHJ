package com.example.hjhan.testhhj.stickyHeaderTest;

import android.graphics.Color;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brandongogetap.stickyheaders.exposed.StickyHeader;
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler;
import com.example.hjhan.testhhj.R;


import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;

/**
 * StickyHeaderAdapter
 *
 * @author hjhan
 * @since 2017-11-21
 */

public class StickyHeaderAdapter extends RecyclerView.Adapter<StickyHeaderAdapter.BaseViewHolder>
        implements StickyHeaderHandler {

    private final List<StickyTestModel.Data.CardTypeList> data = new ArrayList<>();

    public void setData(List<StickyTestModel.Data.CardTypeList> items) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SimpleDiffCallback(data, items));
        data.clear();
        data.addAll(items);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext()).inflate(R.layout.item_sticky_test, parent, false);
        final BaseViewHolder viewHolder;
//        if (viewType == 0) {
            viewHolder = new MyViewHolder(view);
//        } else {
//            viewHolder = new MyOtherViewHolder(view);
//        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is unsafe to do in OnClickListeners attached to sticky headers. The adapter
                // position of the holder will be out of sync if any items have been added/removed.
                // If a click listener needs to be set on a sticky header, it is recommended to identify the header
                // based on its backing model, rather than position in the data set.
//                int position = viewHolder.getAdapterPosition();
//                if (position != NO_POSITION) {
//                    List<StickyTestModel.Data.CardTypeList> newData = new ArrayList<>(data);
//                    newData.remove(position);
//                    setData(newData);
//                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        StickyTestModel.Data.CardTypeList item = data.get(position);
        holder.titleTextView.setText(item.getIdx()+"");
        holder.messageTextView.setText(item.getName());
        if ("SECTION".equals(item.getCardType())) {
            holder.messageTextView.setText("");
        }

        //StickyHeader 로 header, item 판단
        if (item instanceof StickyHeader) {
            holder.itemView.setBackgroundColor(Color.CYAN);
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public List<?> getAdapterData() {
        return data;
    }

    private static final class MyViewHolder extends BaseViewHolder {

        MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static final class MyOtherViewHolder extends BaseViewHolder {

        MyOtherViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView messageTextView;

        BaseViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.tv_title);
            messageTextView = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }
}