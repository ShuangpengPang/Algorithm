package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1318MinimumFlipsToMakeAORBEqualToC（或运算的最小翻转次数）
 * @date 2023/7/8 9:19 PM
 */
public class Problem1318MinimumFlipsToMakeAORBEqualToC {

    public int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int b1 = a >> i & 1, b2 = b >> i & 1, b3 = c >> i & 1;
            ans += ((b1 | b2) ^ b3) * (b3 == 1 ? 1 : 2 - (b1 ^ b2));
        }
        return ans;
    }
}
