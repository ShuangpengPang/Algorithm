package com.shuangpeng.Problem.p1001_1100;

import java.util.Arrays;

/**
 * @Description: Problem1051HeightChecker（高度检查器）
 * @Date 2022/6/13 10:10 AM
 * @Version 1.0
 */
public class Problem1051HeightChecker {

    public int heightChecker0(int[] heights) {
        int[] copy = heights.clone();
        Arrays.sort(copy);
        int ans = 0;
        int n = heights.length;
        for (int i = 0; i < n; ++i) {
            if (heights[i] != copy[i]) {
                ++ans;
            }
        }
        return ans;
    }

    public int heightChecker(int[] heights) {
        int[] buckets = new int[101];
        for (int h : heights) {
            ++buckets[h];
        }
        int ans = 0;
        int idx = heights.length - 1;
        for (int i = 100; i > 0 && idx >= 0; --i) {
            while (buckets[i] > 0) {
                if (heights[idx] != i) {
                    ++ans;
                }
                --buckets[i];
                --idx;
            }
        }
        return ans;
    }
}
