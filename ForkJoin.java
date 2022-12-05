package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveAction {
    private int[][] Arr;
    private int left;
    private int right;
    private ArrayHelper helper;
    public ForkJoin(int[][] arr, int left, int right, ArrayHelper helper) {
        Arr = arr;
        this.left = left;
        this.right = right;
        this.helper = helper;
    }

    @Override
    protected void compute() {
        if (left >= right)
            return;

        int middle = left + (right - left) / 2;
        int opora = getColumnSum(middle);

        int i = left, j = right;

        while (i <= j) {
            while (getColumnSum(i) > opora) {
                i++;
            }

            while (getColumnSum(j) < opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                helper.ReplaceColumnArray(Arr,i,j);
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        List<ForkJoin> subTasks = new ArrayList<>();
        if (left < j)
            subTasks.add(new ForkJoin(Arr, left, j, helper));

        if (right > i)
            subTasks.add(new ForkJoin(Arr, i, right, helper));

        ForkJoinTask.invokeAll(subTasks);
    }

    private int getColumnSum(int index) {
        int sum = 0;
        for (int i = 0; i < Arr.length; i++) {
            sum+= Arr[i][index];
        }
        return sum;
    }
}

