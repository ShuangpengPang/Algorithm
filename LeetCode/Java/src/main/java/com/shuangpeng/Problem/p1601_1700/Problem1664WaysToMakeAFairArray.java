package com.shuangpeng.Problem.p1601_1700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1664WaysToMakeAFairArray（生成平衡数组的方案数）
 * @date 2023/1/30 5:58 PM
 */
public class Problem1664WaysToMakeAFairArray {

    public int waysToMakeFair0(int[] nums) {
        int n = nums.length, totalEven = 0, totalOdd = 0;
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                totalEven += nums[i];
            } else {
                totalOdd += nums[i];
            }
        }
        int ans = 0;
        for (int i = 0, sumEven = 0, sumOdd = 0; i < n; i++) {
            int even = sumEven, odd = sumOdd;
            if ((i & 1) == 0) {
                sumEven += nums[i];
            } else {
                sumOdd += nums[i];
            }
            if (even + totalOdd - sumOdd == odd + totalEven - sumEven) {
                ans++;
            }
        }
        return ans;
    }

    public int waysToMakeFair(int[] nums) {
        int n = nums.length, ans = 0, s = 0;
        for (int i = 0; i < n; i++) {
            s += (i & 1) == 0 ? nums[i] : -nums[i];
        }
        for (int i = 0; i < n; i++) {
            s -= (i & 1) == 0 ? nums[i] : -nums[i];
            ans += s == 0 ? 1 : 0;
            s -= (i & 1) == 0 ? nums[i] : -nums[i];
        }
        return ans;
    }
}
