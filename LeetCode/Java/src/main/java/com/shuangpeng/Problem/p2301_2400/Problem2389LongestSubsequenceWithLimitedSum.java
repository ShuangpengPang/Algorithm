package com.shuangpeng.Problem.p2301_2400;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2389LongestSubsequenceWithLimitedSum（和有限的最长子序列）
 * @date 2023/3/17 10:21 AM
 */
public class Problem2389LongestSubsequenceWithLimitedSum {

    public int[] answerQueries0(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Integer[] ids = new Integer[m];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(nums);
        Arrays.sort(ids, Comparator.comparingInt(a -> queries[a]));
        int[] ans = new int[m];
        for (int i = 0, j = 0, s = 0; i < m; i++) {
            int id = ids[i];
            while (j < n && s + nums[j] <= queries[id]) {
                s += nums[j++];
            }
            ans[id] = j;
        }
        return ans;
    }

    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length, m = queries.length;
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int s = queries[i], left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + right >> 1;
                if (nums[mid] <= s) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            ans[i] = left;
        }
        return ans;
    }
}
