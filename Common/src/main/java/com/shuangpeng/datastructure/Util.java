package com.shuangpeng.datastructure;

public class Util {
    public static void quickSort(int[] data, int start, int end) {
        if (data == null || data.length == 0) {
            return;
        }
        if (start >= end) {
            return;
        }
        int index = partition(data, start, end);
        quickSort(data, start, index - 1);
        quickSort(data, index + 1, end);
    }

    public static int partition(int[] data, int start, int end) {
        int d = data[end];
        int index = start - 1;
        for (int i = start; i < end; i++) {
            if (data[i] <= d) {
                index++;
                swap(data, index, i);
            }
        }
        swap(data, index + 1, end);
        return index + 1;
    }

    public static void swap(int[] data, int i, int j) {
        if (i != j) {
            data[i] = data[j] - data[i];
            data[j] = data[j] - data[i];
            data[i] = data[j] + data[i];
        }
    }
}
