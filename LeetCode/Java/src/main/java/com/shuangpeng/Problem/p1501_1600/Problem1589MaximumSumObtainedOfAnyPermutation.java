package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1589MaximumSumObtainedOfAnyPermutation（所有排列中的最大和）
 * @date 2023/9/7 6:59 PM
 */
public class Problem1589MaximumSumObtainedOfAnyPermutation {

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int[] r : requests) {
            diff[r[0]]++;
            diff[r[1] + 1]--;
        }
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for (int i = 0, cnt = 0; i < n; i++) {
            cnt += diff[i];
            freq.merge(cnt, 1, Integer::sum);
        }
        int idx = 0;
        long ans = 0, N = (int) 1e9 + 7;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int f = entry.getKey(), c = entry.getValue();
            if (f > 0) {
                for (int i = 0; i < c; i++) {
                    ans = (ans + (long) nums[idx + i] * f) % N;
                }
            }
            idx += c;
        }
        return (int) ans;
    }
}
