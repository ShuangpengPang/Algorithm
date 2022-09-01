package com.shuangpeng.competition.第280场周赛;

import java.util.*;

/**
 * @Description: Problem2172MaximumANDSumOfArray
 * @Date 2022/2/19 4:19 PM
 * @Version 1.0
 */
public class Problem2172MaximumANDSumOfArray {

    public int maximumANDSum(int[] nums, int numSlots) {
        return dfs(nums, new int[numSlots][2], new boolean[nums.length], nums.length, 0, new HashMap<>());
    }

    private int dfs(int[] nums, int[][] used, boolean[] visited, int remain, int sum, Map<String, Integer> memo) {
        if (remain == 0) {
            return sum;
        }
        String key = toKey(used);
        int value = memo.getOrDefault(key, -1);
        if (value != -1) {
            return value;
        }
        int slot = used.length;
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                for (int j = 0; j < slot; ++j) {
                    if (nums[i] == j + 1) {
                        continue;
                    }
                    int k = used[j][0] == 0 ? 0 : (used[j][1] == 0 && nums[i] > used[j][0] ? 1 : -1);
                    if (k != -1) {
                        used[j][k] = nums[i];
                        ans = Math.max(ans, dfs(nums, used, visited, remain - 1, sum + (nums[i] & (j + 1)), memo));
                        used[j][k] = 0;
                    }
                }
                visited[i] = false;
            }
        }
        memo.put(key, ans);
        return ans;
    }

    private String toKey(int[][] used) {
        StringBuilder sb = new StringBuilder();
        for (int[] p : used) {
            sb.append(p[0] + '_' + p[1] + '_');
        }
        return sb.toString();
    }
}
