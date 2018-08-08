package com.example.hjhan.testhhj.stickyHeaderTest;

import java.util.List;

/**
 * OpenCloseModel
 *
 * @author prompt
 * @since 2017-11-10
 */
public class StickyTestModel {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private List<CardTypeList> lists;

        public List<CardTypeList> getLists() {
            return lists;
        }

        public void setLists(List<CardTypeList> lists) {
            this.lists = lists;
        }

        public static class CardTypeList {
            private int idx;
            private String name;
            private String cardType;

            public String getCardType() {
                return cardType;
            }

            public void setCardType(String cardType) {
                this.cardType = cardType;
            }

            public int getIdx() {
                return idx;
            }

            public void setIdx(int idx) {
                this.idx = idx;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
