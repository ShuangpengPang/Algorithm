package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2485FindThePivotNumber（找出中枢整数）
 * @date 2023/7/3 5:48 PM
 */
public class Problem2485FindThePivotNumber {

    public int pivotInteger0(int n) {
        int left = 1, right = n, sum = (n + 1) * n >> 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            int s1 = (1 + mid) * mid >> 1, s2 = sum - s1 + mid;
            if (s1 < s2) {
                left = mid + 1;
            } else if (s1 > s2) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int pivotInteger(int n) {
        int num = (n + 1) * n >> 1;
        int x = (int) Math.sqrt(num);
        if (x * x == num) {
            return x;
        }
        return -1;
    }
}
