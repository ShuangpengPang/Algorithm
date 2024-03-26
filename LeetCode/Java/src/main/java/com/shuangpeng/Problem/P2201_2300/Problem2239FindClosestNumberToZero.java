package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2239FindClosestNumberToZero（找到最接近0的数字）
 * @date 2024/3/26 11:31 AM
 */
public class Problem2239FindClosestNumberToZero {

    public int findClosestNumber(int[] nums) {
        int abs = Integer.MAX_VALUE, ans = 0;
        for (int num : nums) {
            int a = Math.abs(num);
            if (a < abs || a == abs && num > ans) {
                abs = a;
                ans = num;
            }
        }
        return ans;
    }
}
