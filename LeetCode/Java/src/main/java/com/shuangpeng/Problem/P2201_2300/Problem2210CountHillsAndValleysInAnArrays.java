package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2210CountHillsAndValleysInAnArrays（统计数组中峰和谷的数量）
 * @date 2024/3/25 6:09 PM
 */
public class Problem2210CountHillsAndValleysInAnArrays {

    public int countHillValley(int[] nums) {
        int n = nums.length, ans = 0;
        int i = 1;
        while (i < n && nums[i] == nums[0]) {
            i++;
        }
        while (i < n) {
            if (nums[i] > nums[i - 1]) {
                while (i < n && nums[i] >= nums[i - 1]) {
                    i++;
                }
            } else {
                while (i < n && nums[i] <= nums[i - 1]) {
                    i++;
                }
            }
            if (i < n) {
                ans++;
            }
        }
        return ans;
    }
}
