package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1477FindTwoNonOverlappingSubarraysEachWithTargetSum（找两个和为目标值且不重叠的子数组）
 * @date 2023/8/28 5:52 PM
 */
public class Problem1477FindTwoNonOverlappingSubarraysEachWithTargetSum {

    public int minSumOfLengths0(int[] arr, int target) {
        int n = arr.length, N = Integer.MAX_VALUE >> 1;
        Map<Integer, Integer> map = new HashMap<>();
        int[] minLength = new int[n + 1];
        Arrays.fill(minLength, N);
        int ans = N;
        map.put(0, 0);
        for (int i = 1, s = 0; i <= n; i++) {
            s += arr[i - 1];
            minLength[i] = minLength[i - 1];
            int idx = map.getOrDefault(s - target, -1);
            if (idx != -1) {
                ans = Math.min(ans, i - idx + minLength[idx]);
                minLength[i] = Math.min(minLength[i], i - idx);
            }
            map.put(s, i);
        }
        return ans == N ? -1 : ans;
    }

    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length, N = Integer.MAX_VALUE >> 1, ans = N;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, N);
        for (int p = 0, s = 0, i = 0; i < n; i++) {
            dp[i + 1] = dp[i];
            s += arr[i];
            while (s > target) {
                s -= arr[p++];
            }
            if (s == target) {
                ans = Math.min(ans, dp[p] + i - p + 1);
                dp[i + 1] = Math.min(dp[i + 1], i - p + 1);
            }
        }
        return ans == N ? -1 : ans;
    }
}
