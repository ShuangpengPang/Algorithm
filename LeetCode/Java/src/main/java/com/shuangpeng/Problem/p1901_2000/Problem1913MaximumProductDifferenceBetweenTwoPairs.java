package com.shuangpeng.Problem.p1901_2000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1913MaximumProductDifferenceBetweenTwoPairs（两个数对之间的最大乘积差）
 * @date 2024/3/22 5:55 PM
 */
public class Problem1913MaximumProductDifferenceBetweenTwoPairs {

    public int maxProductDifference(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = min1;
        int max1 = Integer.MIN_VALUE, max2 = max1;
        for (int num : nums) {
            if (num <= min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
            if (num >= max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }
        return max1 * max2 - min1 * min2;
    }
}
