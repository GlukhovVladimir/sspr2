package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        int[][] Arr = {
//                {1,5,7,9,4},
//                {6,6,3,2,1},
//                {1,1,2,3,1},
//                {9,8,6,8,7},
//                {5,5,7,6,3}
//        };
        ArrayHelper helper = new ArrayHelper();
       int[][] Arr = helper.GenerateArray(1000);

        int[][] Arr1 = helper.CloneArray(Arr, Arr.length);
        int[][] Arr2 = helper.CloneArray(Arr, Arr.length);
        int[][] Arr3 = helper.CloneArray(Arr, Arr.length);

        //non thread
        long start = System.currentTimeMillis();
        for (int m = Arr1[0].length - 1; m >= 1 ; m--) {
            for (int j = 0; j < m; j++) {
                int sumPrev = 0;
                int sumNow = 0;
                for (int i = 0; i < Arr1.length; i++) {
                    sumPrev += Arr1[i][j];
                    sumNow += Arr1[i][j+1];
                }
                if(sumNow > sumPrev) {
                    helper.ReplaceColumnArray(Arr1,j,j+1);
                }
            }
        }
        long stop = System.currentTimeMillis();
        System.out.printf("Без потоков ");
        System.out.printf("Time: %s; %n", stop - start);

        //with ThreadPool
        start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<Integer>> resultsFeature = new ArrayList<>();
        int [] results = new int[Arr2[0].length];
        for (int i = 0; i < Arr2[0].length; i++) {
            resultsFeature.add(executorService.submit(new ThreadPool(Arr2, i)));
        }
        int o = 0;
        for (Future<Integer> task : resultsFeature) {
            try {
                results[o] = task.get();
                o++;
            } catch (InterruptedException | ExecutionException ignored) { }
        }
        executorService.shutdownNow();
        for (int m = results.length - 1; m >= 1 ; m--) {
            for (int j = 0; j < m; j++) {
                if(results[j + 1] > results[j]) {
                    helper.ReplaceColumnArray(Arr2, j, j+1);
                    helper.ReplaceElementsArray(results, j, j+ 1);
                }
            }
        }
        stop = System.currentTimeMillis();
//        helper.PrintArray(Arr2);
        System.out.printf("С ThreadPool ");
        System.out.printf("Time: %s; %n", stop - start);

        //with ForkJoin
        start = System.currentTimeMillis();
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        commonPool.execute(new ForkJoin(Arr3, 0 , Arr3[0].length - 1, helper));
        commonPool.shutdownNow();
        commonPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        stop = System.currentTimeMillis();
        System.out.printf("С ForkJoin ");
        System.out.printf("Time: %s; %n", stop - start);
//        helper.PrintArray(Arr3);
    }

}
