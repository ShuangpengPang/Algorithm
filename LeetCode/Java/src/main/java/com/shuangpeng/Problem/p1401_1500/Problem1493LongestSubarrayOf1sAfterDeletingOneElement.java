package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1493LongestSubarrayOf1sAfterDeletingOneElement（删除一个元素以后全为1的最长子数组）
 * @date 2023/9/8 2:11 PM
 */
public class Problem1493LongestSubarrayOf1sAfterDeletingOneElement {

    public int longestSubarray0(int[] nums) {
        int n = nums.length, ans = 0, idx = 0;
        while (idx < n && nums[idx] == 1) {
            idx++;
        }
        if (idx == n) {
            return n - 1;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                int p = i;
                while (i < n && nums[i] == 1) {
                    i++;
                }
                int j = i + 1;
                while (j < n && nums[j] == 1) {
                    j++;
                }
                ans = Math.max(ans, j - p - 1);
            }
        }
        return ans;
    }

    public int longestSubarray(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int s = i;
            while (i < n && nums[i] == 1) {
                i++;
            }
            if (i == n) {
                ans = Math.max(ans, i - s + (s > 0 ? 1 : 0));
                break;
            }
            int j = i + 1;
            while (j < n && nums[j] == 1) {
                j++;
            }
            ans = Math.max(ans, j - s);
        }
        return Math.max(ans - 1, 0);
    }
}
