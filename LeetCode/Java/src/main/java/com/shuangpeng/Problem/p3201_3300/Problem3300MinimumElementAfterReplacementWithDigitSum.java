package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3300MinimumElementAfterReplacementWithDigitSum（替换为数位和以后的最小元素）
 * @date 2024/11/14 7:26 PM
 */
public class Problem3300MinimumElementAfterReplacementWithDigitSum {

    public int minElement(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int num : nums) {
            int sum = 0;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}
