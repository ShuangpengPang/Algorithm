package com.shuangpeng.Problem.p0201_0300;

/**
 * @description:（打家劫舍II）
 * @date 2023/9/17 7:07 PM
 **/
public class Problem0213HouseRobberII {

    public int rob0(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int a = 0, b = 0;
        for (int i = 0; i < n - 1; i++) {
            int t = b;
            b = a + nums[i];
            a = Math.max(a, t);
        }
        int max = Math.max(a, b);
        a = 0;
        b = 0;
        for (int i = 1; i < n; i++) {
            int t = b;
            b = a + nums[i];
            a = Math.max(a, t);
        }
        return Math.max(max, Math.max(a, b));
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(maxMoney(nums, 0, n - 2), maxMoney(nums, 1, n - 1));
    }

    private int maxMoney(int[] nums, int start, int end) {
        int first = 0, second = 0;
        for (int i = start; i <= end; i++) {
            int tmp = Math.max(second, first + nums[i]);
            first = second;
            second = tmp;
        }
        return second;
    }
}
