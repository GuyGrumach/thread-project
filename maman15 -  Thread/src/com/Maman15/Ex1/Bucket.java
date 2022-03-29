package com.Maman15.Ex1;

public class Bucket {

    private final int[] mList;

    public Bucket(int num) {
        mList = new int[1];
        mList[0] = num;
    }

    public Bucket(int[] list) {
        mList = list;
    }

    public int[] getContent() {
        return mList;
    }
}
