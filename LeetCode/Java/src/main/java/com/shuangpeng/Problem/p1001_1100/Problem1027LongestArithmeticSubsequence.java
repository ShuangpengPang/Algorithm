package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 最长等差数列
 * @date 2023/4/23 12:33 AM
 **/
public class Problem1027LongestArithmeticSubsequence {

    public int longestArithSeqLength0(int[] nums) {
        int n = nums.length;
        List<Map<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            list.add(new HashMap<>());
        }
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int d = nums[i] - nums[j];
                int c = list.get(j).getOrDefault(d, 1) + 1;
                int count = list.get(i).getOrDefault(d, 0);
                if (c > count) {
                    list.get(i).put(d, c);
                    ans = Math.max(ans, c);
                }
            }
        }
        return ans;
    }

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length, ans = 0;
        Map<Integer, Integer>[] m = new Map[n];
        for (int i = 0; i < n; i++) {
            m[i] = new HashMap<>();
            for (int j = i - 1; j >= 0; j--) {
                int d = nums[i] - nums[j];
                if (!m[i].containsKey(d)) {
                    int count = m[j].getOrDefault(d, 1) + 1;
                    m[i].put(d, count);
                    ans = Math.max(ans, count);
                }
            }
        }
        return ans;
    }
}
