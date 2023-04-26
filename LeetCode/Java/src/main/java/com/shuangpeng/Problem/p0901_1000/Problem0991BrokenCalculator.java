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
        while (startValue < target) {
            startValue <<= 1;
            count++;
        }
        int p = 1 << count, d = startValue - target;
        while (d > 0) {
            count += d / p;
            d %= p;
            p >>= 1;
        }
        return count;
    }

    public int brokenCalc1(int startValue, int target) {
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

    public int brokenCalc(int startValue, int target) {
        int ans = 0;
        while (target > startValue) {
            ans += (target & 1) + 1;
            target = (target + 1) >> 1;
        }
        return ans + startValue - target;
    }
}
