package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0991BrokenCalculator（坏了的计算器）
 * @date 2023/4/6 12:06 PM
 */
public class Problem0991BrokenCalculator {

    public int brokenCalc0(int startValue, int target) {
        int count = 0;
        while (target > startValue) {
            startValue <<= 1;
            count++;
        }
        int d = startValue - target, n = 1 << count;
        while (d > 0) {
            if (d >= n) {
                count += d / n;
                d %= n;
            }
            n >>= 1;
        }
        return count;
    }

    public int brokenCalc(int startValue, int target) {
        int ans = 0;
        while (target > startValue) {
            if ((target & 1) == 1) {
                ans++;
                target++;
            }
            ans++;
            target >>= 1;
        }
        return ans + startValue - target;
    }
}
