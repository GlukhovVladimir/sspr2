package com.company;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadPool implements Callable<Integer> {
    private int[][] Arr;
    private int Column;
    public ThreadPool(int[][] arr, int column) {
        Arr = arr;
        Column = column;
    }
    @Override
    public Integer call() {
       int sum = 0;

        for (int i = 0; i < Arr.length; i++) {
            sum += Arr[i][Column];
        }
        return sum;
    }
}
