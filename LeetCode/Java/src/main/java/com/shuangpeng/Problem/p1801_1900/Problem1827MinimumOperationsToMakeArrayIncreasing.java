package com.shuangpeng.Problem.p1801_1900;

/**
 * @description: 最少操作使数组递增
 * @date 2022/12/11 7:27 PM
 **/
public class Problem1827MinimumOperationsToMakeArrayIncreasing {

    public int minOperations0(int[] nums) {
        if (nums == null || nums.length == 1) {
            return 0;
        }
        int current = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= current) {
                current++;
            } else {
                current = nums[i];
            }
            count += current - nums[i];
        }
        return count;
    }

    public int minOperations1(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int opt = 0;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= num) {
                num++;
                opt += (num - nums[i]);
            } else {
                num = nums[i];
            }
        }
        return opt;
    }

    public int minOperations(int[] nums) {
        int n = nums.length, ans = 0, prev = 0;
        for (int i = 0; i < n; i++) {
            prev = Math.max(prev + 1, nums[i]);
            ans += prev - nums[i];
        }
        return ans;
    }
}
