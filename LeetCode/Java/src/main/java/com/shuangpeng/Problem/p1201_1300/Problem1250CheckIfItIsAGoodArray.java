package com.shuangpeng.Problem.p1201_1300;

/**
 * @Description: Problem1250CheckIfItIsAGoodArray（检查好数组）
 * @Date 2022/7/27 4:55 PM
 * @Version 1.0
 */
public class Problem1250CheckIfItIsAGoodArray {

    public boolean isGoodArray(int[] nums) {
        int n = nums.length;
        int g = nums[0];
        for (int i = 1; i < n && g > 1; i++) {
            g = gcd(nums[i], g);
        }
        return g == 1;
    }

    private int gcd(int a, int b) {
        int c = b;
        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }
}
