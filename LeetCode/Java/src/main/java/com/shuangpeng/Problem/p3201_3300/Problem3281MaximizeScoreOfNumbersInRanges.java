package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3281MaximizeScoreOfNumbersInRanges（范围内整数的最大得分）
 * @date 2024/11/27 2:46 PM
 */
public class Problem3281MaximizeScoreOfNumbersInRanges {

    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        int n = start.length, left = 0, right = (start[n - 1] + d - start[0]) / (n - 1);
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (check(start, d, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    private boolean check(int[] start, long d, long abs) {
        int n = start.length;
        long prev = start[0];
        for (int i = 1; i < n; i++) {
            prev = Math.max(prev + abs, start[i]);
            if (prev > start[i] + d) {
                return false;
            }
        }
        return true;
    }
}
