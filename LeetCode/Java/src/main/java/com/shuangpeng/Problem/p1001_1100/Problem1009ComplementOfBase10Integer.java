package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1009ComplementOfBase10Integer（十进制整数的反码）
 * @date 2024/1/6 6:28 PM
 */
public class Problem1009ComplementOfBase10Integer {

    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0, b = 1;
        while (n > 0) {
            if ((n & 1) == 0) {
                ans += b;
            }
            b <<= 1;
            n >>= 1;
        }
        return ans;
    }
}
