package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2427NumberOfCommonFactors（公因子的数目）
 * @date 2023/4/5 2:59 PM
 */
public class Problem2427NumberOfCommonFactors {

    public int commonFactors(int a, int b) {
        int g = gcd(a, b), ans = 0;
        for (int i = 1; i <= g; i++) {
            if (g % i == 0) {
                ans++;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
