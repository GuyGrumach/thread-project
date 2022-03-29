package com.Maman15.Ex1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Manager extends Thread {
    private final int[] mArr;
    private final int mNumberOfWorkers;
    private final BlockingQueue<Bucket> mBucketsQueue = new LinkedBlockingQueue<>();

    public Manager(int[] arr, int m) {
        this.mArr = arr;
        this.mNumberOfWorkers = m;
        splitArrToBuckets();
    }

    public int[] getResult() {
        return mBucketsQueue.peek().getContent();
    }

    @Override
    public void run() {
        Thread[] threads = new Thread[mNumberOfWorkers];
        for (int i = 0; i < mNumberOfWorkers; i++) {
            threads[i] = new Thread(new Job(mBucketsQueue));
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {

            }
        }
    }

    private void splitArrToBuckets() {
        for (int j : mArr) {
            mBucketsQueue.offer(new Bucket(j));
        }
    }


}


