package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;

/**
 * @Description: Problem1889MinimumSpaceWastedFromPackaging（装包裹的最小浪费空间）
 * @Date 2022/10/27 10:27 AM
 * @Version 1.0
 */
public class Problem1889MinimumSpaceWastedFromPackaging {

    public int minWastedSpace0(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        int n = packages.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + packages[i];
        }
        long ans = Long.MAX_VALUE, M = (long) 1e9 + 7;
        for (int[] box : boxes) {
            Arrays.sort(box);
            if (box[box.length - 1] >= packages[n - 1]) {
                int prev = -1;
                long res = 0;
                for (int b : box) {
                    int index = getIndex(packages, b);
                    if (index > prev) {
                        res += (long) b * (index - prev) - (preSum[index + 1] - preSum[prev + 1]);
                        prev = index;
                    }
                }
                ans = Math.min(ans, res);
            }
        }
        return ans == Long.MAX_VALUE ? -1 : (int) (ans % M);
    }

    private int getIndex(int[] packages, int data) {
        int left = 0, right = packages.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (packages[mid] <= data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    public int minWastedSpace(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        int n = packages.length;
        long sum = 0;
        for (int p : packages) {
            sum += p;
        }
        long ans = Long.MAX_VALUE, M = (long) 1e9 + 7;
        for (int[] box : boxes) {
            Arrays.sort(box);
            if (box[box.length - 1] >= packages[n - 1]) {
                int prev = -1;
                long s = 0;
                for (int b : box) {
                    int idx = binarySearch(packages, b);
                    s += (long) b * (idx - prev);
                    prev = idx;
                    if (prev == n - 1) {
                        break;
                    }
                }
                ans = Math.min(ans, s - sum);
            }
        }
        return ans == Long.MAX_VALUE ? -1 : (int) (ans % M);
    }

    private int binarySearch(int[] packages, int data) {
        int left = 0, right = packages.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (packages[mid] <= data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }
}