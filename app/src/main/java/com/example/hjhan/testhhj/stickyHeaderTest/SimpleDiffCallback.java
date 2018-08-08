package com.example.hjhan.testhhj.stickyHeaderTest;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * SimpleDiffCallback
 *
 * @author hjhan
 * @since 2017-11-21
 */

public class SimpleDiffCallback  extends DiffUtil.Callback {

    private final List<StickyTestModel.Data.CardTypeList> oldList;
    private final List<StickyTestModel.Data.CardTypeList> newList;

    SimpleDiffCallback(List<StickyTestModel.Data.CardTypeList> oldList, List<StickyTestModel.Data.CardTypeList> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areItemsTheSame(oldItemPosition, newItemPosition);
    }
}