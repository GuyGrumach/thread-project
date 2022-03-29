package com.Maman15.Ex1;

import java.util.concurrent.BlockingQueue;

public class Job implements Runnable {

    private final BlockingQueue<Bucket> mQueue;

    public Job(BlockingQueue<Bucket> q) {
        this.mQueue = q;
    }

    @Override
    public void run() {
        while (mQueue.size() > 1) {
            Bucket bucket1;
            Bucket bucket2;
            bucket1 = mQueue.poll();
            bucket2 = mQueue.poll();
            if (bucket1 == null || bucket2 == null) {
                if (bucket1 != null) {
                    mQueue.offer(bucket1);
                }
                if (bucket2 != null) {
                    mQueue.offer(bucket2);
                }
            } else {
                Bucket mergedBucket = mergeBuckets(bucket1, bucket2);
                mQueue.offer(mergedBucket);
            }
        }

    }


    private Bucket mergeBuckets(Bucket a, Bucket b) {
        int[] arrayA = a.getContent();
        int[] arrayB = b.getContent();
        int[] mergedArray = new int[arrayA.length + arrayB.length];
        int i = 0, j = 0, k = 0;
        while (i < arrayA.length && j < arrayB.length) {
            if (arrayA[i] < arrayB[j]) {
                mergedArray[k] = arrayA[i];
                i++;
                k++;
            } else {
                mergedArray[k] = arrayB[j];
                j++;
                k++;
            }
        }

        while (i < arrayA.length) {
            mergedArray[k] = arrayA[i];
            i++;
            k++;
        }

        while (j < arrayB.length) {
            mergedArray[k] = arrayB[j];
            j++;
            k++;
        }

        return new Bucket(mergedArray);
    }

}
