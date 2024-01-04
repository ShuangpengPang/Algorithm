package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2918MinimumEqualSumOfTwoArraysAfterReplacingZeros（数组的最小相等和）
 * @date 2024/1/4 11:29 PM
 */
public class Problem2918MinimumEqualSumOfTwoArraysAfterReplacingZeros {

    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0, sum2 = 0;
        int zero1 = 0, zero2 = 0;
        for (int num : nums1) {
            if (num == 0) {
                zero1++;
            } else {
                sum1 += num;
            }
        }
        for (int num : nums2) {
            if (num == 0) {
                zero2++;
            } else {
                sum2 += num;
            }
        }
        if (zero1 != 0 && zero2 != 0) {
            return Math.max(sum1 + zero1, sum2 + zero2);
        }
        if (zero1 == 0 && zero2 == 0) {
            return sum1 == sum2 ? sum1 : -1;
        } else if (zero1 == 0) {
            return sum1 >= sum2 + zero2 ? sum1 : -1;
        } else {
            return sum2 >= sum1 + zero1 ? sum2 : -1;
        }
    }
}
