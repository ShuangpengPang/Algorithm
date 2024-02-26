package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3034NumberOfSubarraysThatMatchAPatternI（匹配模式数组的子数组数目I）
 * @date 2024/2/26 7:26 PM
 */
public class Problem3034NumberOfSubarraysThatMatchAPatternI {

    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length, m = pattern.length;
        int[] next = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = next[j - 1];
            }
            next[i] = j = pattern[i] == pattern[j] ? j + 1 : 0;
        }
        int ans = 0;
        for (int i = 0, j = 0; n - i > m - j; i++) {
            int p = nums[i] < nums[i + 1] ? 1 : nums[i] == nums[i + 1] ? 0 : -1;
            while (j > 0 && pattern[j] != p) {
                j = next[j - 1];
            }
            if (pattern[j] == p && ++j == m) {
                ans++;
                j = next[m - 1];
            }
        }
        return ans;
    }
}
