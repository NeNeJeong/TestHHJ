package com.example.hjhan.testhhj.stickyHeaderTest.sticky.model;

/**
 * boardListItem
 *
 * @author hjhan
 * @since 2018-03-16
 */

public class BoardListItem {
    public int position;
    public long boardIdx;
    public long boardBegin;
    public long boardEnd;
    public String boardName;
    public String boardTags;
    public long timelineId;
    public long cardIdx;
    public String cardType;
    public Integer cardOrderValue;
    public Integer cardRatingValue;
    public String sectionTitle;

    //section 내용 추가
    public long sectionIdx;


    public BoardListItem(String boardName, String sectionTitle, long sectionIdx) {
        this.boardName = boardName;
        this.sectionTitle = sectionTitle;
        this.sectionIdx = sectionIdx;
    }

    public BoardListItem(String boardName, String sectionTitle, long sectionIdx, String cardType) {
        this.boardName = boardName;
        this.cardType = cardType;
        this.sectionTitle = sectionTitle;
        this.sectionIdx = sectionIdx;
    }
}
