package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3012MinimizeLengthOfArrayUsingOperations（通过操作使数组长度最小）
 * @date 2024/3/4 12:10 AM
 */
public class Problem3012MinimizeLengthOfArrayUsingOperations {

    public int minimumArrayLength(int[] nums) {
        int minValue = Integer.MAX_VALUE;
        int minCount = 0;
        for (int num : nums) {
            if (num < minValue) {
                minValue = num;
                minCount = 1;
            } else if (num == minValue) {
                minCount++;
            }
        }
        for (int num : nums) {
            if (num != minValue && num % minValue != 0) {
                return 1;
            }
        }
        return minCount + 1 >> 1;
    }
}
