package com.example.hjhan.testhhj.stickyHeaderTest;

import android.content.Context;

import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler;

/**
 * TopSnappedStickyLayoutManager
 *
 * @author hjhan
 * @since 2017-11-21
 */

public class TopSnappedStickyLayoutManager extends StickyLayoutManager {

    public TopSnappedStickyLayoutManager(Context context, StickyHeaderHandler headerHandler) {
        super(context, headerHandler);
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPositionWithOffset(position, 0);
    }
}
