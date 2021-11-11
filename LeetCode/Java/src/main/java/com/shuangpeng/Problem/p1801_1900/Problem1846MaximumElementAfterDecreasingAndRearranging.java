package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;

public class Problem1846MaximumElementAfterDecreasingAndRearranging {

    public int maximumElementAfterDecrementingAndRearranging0(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1] + 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[n - 1];
    }

    public int maximumElementAfterDecrementingAndRearranging1(int[] arr) {
        int n = arr.length;
        int[] counts = new int[n + 1];
        for (int i = 0; i < n; i++) {
            counts[Math.min(n, arr[i])]++;
        }
        int j = 0, previous = 0;
        for (int i = 1; i <= n; i++) {
            previous = Math.min(i, previous + counts[i]);
            j += counts[i];
            if (j >= n) {
                return previous;
            }
        }
        return previous;
    }

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] counts = new int[n + 1];
        for (int i = 0; i < n; i++) {
            counts[Math.min(n, arr[i])]++;
        }
        int miss = 0;
        for (int i = 1; i <= n; i++) {
            if (counts[i] == 0) {
                miss++;
            } else {
                miss -= Math.min(miss, counts[i] - 1);
            }
        }
        return n - miss;
    }
}
