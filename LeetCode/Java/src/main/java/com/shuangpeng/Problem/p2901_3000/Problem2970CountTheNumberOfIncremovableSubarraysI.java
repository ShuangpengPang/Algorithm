package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2970CountTheNumberOfIncremovableSubarraysI（统计移除递增子数组的数目I）
 * @date 2024/4/15 11:20 AM
 */
public class Problem2970CountTheNumberOfIncremovableSubarraysI {

    // 错误做法
//    public int incremovableSubarrayCount(int[] nums) {
//        int n = nums.length, left = -1, right = -1;
//        int maxValue = Integer.MIN_VALUE;
//        for (int i = 0; i < n; i++) {
//            if (nums[i] > maxValue) {
//                maxValue = nums[i];
//            } else {
//                right = i;
//                left = left == -1 ? i - 1 : left;
//                while (left > 0 && nums[i] <= nums[left - 1]) {
//                    left--;
//                }
//            }
//        }
//        if (left == -1) {
//            return (n + 1) * n >> 1;
//        }
//        return (left + 1) * (n - right + 1) + n - right;
//    }

    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length, i = n - 1;
        while (i > 0 && nums[i - 1] < nums[i]) {
            i--;
        }
        if (i == 0) {
            return (n + 1) * n >> 1;
        }
        int cnt = 0;
        for (int j = 0; j < n && (j < 2 || nums[j - 1] > nums[j - 2]); j++) {
            while (j > 0 && i < n && nums[i] <= nums[j - 1]) {
                i++;
            }
            cnt += n - i + 1;
        }
        return cnt;
    }
}
