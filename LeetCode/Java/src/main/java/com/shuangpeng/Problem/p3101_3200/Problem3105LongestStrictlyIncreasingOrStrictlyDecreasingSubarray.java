package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3105LongestStrictlyIncreasingOrStrictlyDecreasingSubarray（最长的严格递增或递减子数组）
 * @date 2024/4/23 11:56 AM
 */
public class Problem3105LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {

    public int longestMonotonicSubarray0(int[] nums) {
        int n = nums.length, i = 1, ans = 1;
        while (i < n) {
            int m = 1;
            while (i < n && nums[i - 1] < nums[i]) {
                i++;
                m++;
            }
            ans = Math.max(ans, m);
            while (i < n && nums[i - 1] == nums[i]) {
                i++;
            }
            m = 1;
            while (i < n && nums[i - 1] > nums[i]) {
                i++;
                m++;
            }
            ans = Math.max(ans, m);
        }
        return ans;
    }

    public int longestMonotonicSubarray1(int[] nums) {
        int n = nums.length, ans = 1, i = 0;
        while (i < n - 1) {
            if (nums[i] == nums[i + 1]) {
                i++;
                continue;
            }
            boolean inc = nums[i] < nums[i + 1];
            int start = i;
            i += 2;
            while (i < n && nums[i] != nums[i - 1] && nums[i - 1] < nums[i] == inc) {
                i++;
            }
            ans = Math.max(ans, i - start);
            i--;
        }
        return ans;
    }

    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length, asc = 1, desc = 1, ans = 1;
        for (int i = 1; i < n; i++) {
            asc = nums[i - 1] < nums[i] ? asc + 1 : 1;
            desc = nums[i - 1] > nums[i] ? desc + 1 : 1;
            ans = Math.max(ans, Math.max(asc, desc));
        }
        return ans;
    }
}
