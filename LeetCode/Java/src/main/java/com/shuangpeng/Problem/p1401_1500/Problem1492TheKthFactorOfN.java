package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1492TheKthFactorOfN（n的第K个因子）
 * @date 2023/8/27 8:16 PM
 */
public class Problem1492TheKthFactorOfN {

    public int kthFactor(int n, int k) {
        for (int i = 1; i <= n; i++) {
            if (n % i == 0 && --k == 0) {
                return i;
            }
        }
        return -1;
    }
}
