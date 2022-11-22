package com.shuangpeng.competition.第271到280场周赛.第280场周赛;

import java.util.*;

/**
 * @Description: Problem2172MaximumANDSumOfArray（数组的最大与和）
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

class Problem2172MaximumANDSumOfArray0 {

    public int maximumANDSum(int[] nums, int numSlots) {
        int n = nums.length, N = (int) Math.pow(3, numSlots);
        int[] dp = new int[N];
        for (int i = 1; i < N; i++) {
            int cnt = 0;
            for (int j = 0, k = i; j < numSlots; j++) {
                cnt += k % 3;
                k /= 3;
            }
            if (cnt > n) {
                continue;
            }
            for (int j = 1, w = 1, k = i; j <= numSlots; j++, w *= 3, k /= 3) {
                int m = k % 3;
                if (m > 0) {
                    dp[i] = Math.max(dp[i], dp[i - w] + (j & nums[cnt - 1]));
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}