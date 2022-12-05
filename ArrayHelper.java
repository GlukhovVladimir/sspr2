package com.company;

public class ArrayHelper {
    public static int[][] CloneArray(int[][] Arr, int m) {
        int [][] newArr = new int[m][m];
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[0].length; j++) {
                newArr[i][j] = Arr[i][j];
            }
        }
        return  newArr;
    }

    public static int[][] GenerateArray(int m) {
        int [][] newArr = new int[m][m];
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[0].length; j++) {
                newArr[i][j] = (int)(Math.random() * 10);
            }
        }
        return  newArr;
    }
    public static void ReplaceElementsArray(int[] Arr, int elFirst, int elSecond) {
        int t = Arr[elFirst];
        Arr[elFirst] = Arr[elSecond];
        Arr[elSecond] = t;
    }
    public static void ReplaceColumnArray(int[][] Arr, int columnFirst, int columnSecond) {
        for (int i = 0; i < Arr.length; i++) {
            int t = Arr[i][columnFirst];
            Arr[i][columnFirst] = Arr[i][columnSecond];
            Arr[i][columnSecond] = t;
        }
    }
    public static void PrintArray(int[][] Arr) {
        for (int i = 0; i < Arr.length; i++) {
            for (int j = 0; j < Arr[0].length; j++) {
                System.out.printf(String.valueOf(Arr[i][j]) + " ");
            }
            System.out.printf("\n");
        }
    }
}
