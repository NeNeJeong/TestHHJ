package com.example.hjhan.testhhj.stickyHeaderTest;

import com.brandongogetap.stickyheaders.exposed.StickyHeader;

/**
 * HeaderItem
 *
 * @author hjhan
 * @since 2017-11-21
 */

public class HeaderItem extends Item implements StickyHeader {

    HeaderItem(String title, String message) {
        super(title, message);
    }
}