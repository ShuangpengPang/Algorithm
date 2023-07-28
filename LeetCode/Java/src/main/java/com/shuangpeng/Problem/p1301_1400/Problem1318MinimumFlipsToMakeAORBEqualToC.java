package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1318MinimumFlipsToMakeAORBEqualToC（或运算的最小翻转次数）
 * @date 2023/7/8 9:19 PM
 */
public class Problem1318MinimumFlipsToMakeAORBEqualToC {

    public int minFlips0(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int b1 = a >> i & 1, b2 = b >> i & 1, b3 = c >> i & 1;
            ans += ((b1 | b2) ^ b3) * (b3 == 1 ? 1 : 2 - (b1 ^ b2));
        }
        return ans;
    }

    public int minFlips1(int a, int b, int c) {
        int x = (a | b) ^ c, d = a & b & x;
        return (getBitCount(d) << 1) + getBitCount(x ^ d);
    }

    private int getBitCount(int num) {
        int cnt = 0;
        while (num != 0) {
            num &= num - 1;
            cnt++;
        }
        return cnt;
    }

    public int minFlips2(int a, int b, int c) {
        int cnt = 0;
        while (a != 0 || b != 0 || c != 0) {
            if ((c & 1) == 0) {
                cnt += (a & 1) + (b & 1);
            } else if ((a & 1) == 0 && (b & 1) == 0) {
                cnt++;
            }
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        return cnt;
    }

    public int minFlips(int a, int b, int c) {
        int x = (a | b) ^ c, ans = 0;
        while (x != 0) {
            int bit = x & -x;
            ans++;
            if ((c & bit) == 0 && (a & b & bit) != 0) {
                ans++;
            }
            x ^= bit;
        }
        return ans;
    }
}
