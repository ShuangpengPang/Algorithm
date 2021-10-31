package com.shuangpeng.Problem.p0201_0300;

import java.util.Arrays;

public class Problem0274HIndex {

    public int hIndex0(int[] citations) {
        int n = citations.length;
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = citations[i];
        }
        Arrays.sort(array, (a, b) -> b -a);
        for (int i = 0; i < n; i++) {
            if (array[i] <= i) {
                return i;
            }
        }
        return n;
    }

    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] array = new int[n + 2];
        for (int i = 0; i < n; i++) {
            if (citations[i] > n) {
                array[n]++;
            } else {
                array[citations[i]]++;
            }
        }
        int h = n;
        for (int i = n; i >= 0; i--) {
            if (array[i] + array[i + 1] >= h) {
                return h;
            }
            h--;
            array[i] += array[i + 1];
        }
        return 0;
    }

//    public static void main(String[] args) {
//        Problem0274HIndex a = new Problem0274HIndex();
//        a.hIndex(new int[]{1, 3, 1});
//    }
}
