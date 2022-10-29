package com.shuangpeng.competition.双周赛.第085场双周赛;

import java.util.Arrays;

/**
 * @Description: Problem2382MaximumSegmentSumAfterRemovals（删除操作后的最大字段和）
 * @Date 2022/10/29 4:20 PM
 * @Version 1.0
 */
public class Problem2382MaximumSegmentSumAfterRemovals {

    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int[] parent = new int[n], left = new int[n];
        Arrays.fill(parent, -1);
        Arrays.fill(left, -1);
        long[] ans = new long[n];
        long max = 0;
        for (int i = n - 2; i >= 0; i--) {
            int idx = removeQueries[i + 1];
            parent[idx] = left[idx] = idx;
            if (idx > 0 && parent[idx - 1] != -1) {
                union(parent, left, idx - 1, idx);
            }
            if (idx < n - 1 && parent[idx + 1] != -1) {
                union(parent, left, idx, idx + 1);
            }
            int p = find(parent, idx);
            max = Math.max(max, preSum[p + 1] - preSum[left[p]]);
            ans[i] = max;
        }
        return ans;
    }

    private void union(int[] parent, int[] left, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px == py) {
            return;
        }
        if (px < py) {
            parent[px] = py;
            left[py] = left[px];
        } else {
            parent[py] = px;
            left[px] = left[py];
        }
    }

    private int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }
}
