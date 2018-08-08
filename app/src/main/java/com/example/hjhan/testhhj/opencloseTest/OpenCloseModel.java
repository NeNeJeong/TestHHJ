package com.example.hjhan.testhhj.opencloseTest;

import java.util.List;

/**
 * OpenCloseModel
 *
 * @author prompt
 * @since 2017-11-10
 */
public class OpenCloseModel {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private List<OpenCloseList> lists;

        public List<OpenCloseList> getLists() {
            return lists;
        }

        public void setLists(List<OpenCloseList> lists) {
            this.lists = lists;
        }

        public static class OpenCloseList {
            private int idx;
            private String name;
            private String openFlag;
            private String openShare;
            private String selectFlag;
            private long date;

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public String getSelectFlag() {
                return selectFlag;
            }

            public void setSelectFlag(String selectFlag) {
                this.selectFlag = selectFlag;
            }

            public String getOpenFlag() {
                return openFlag;
            }

            public void setOpenFlag(String openFlag) {
                this.openFlag = openFlag;
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

            public String getOpenShare() {
                return openShare;
            }

            public void setOpenShare(String openShare) {
                this.openShare = openShare;
            }
        }
    }
}
