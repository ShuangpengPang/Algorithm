package com.shuangpeng.Problem.p1701_1800;


/**
 * @program: Algorithm
 * @description: Problem1734DecodeXORedPermutation（解码异或后的排列）
 * @author: ShuangPengPang
 * @create: 2025-05-27 16:34
 */
public class Problem1734DecodeXORedPermutation {

    public int[] decode(int[] encoded) {
        int n = encoded.length + 1, x = n, y = 0;
        for (int i = 1; i < n; i++) {
            y ^= encoded[i - 1];
            x ^= y ^ i;
        }
        int[] ans = new int[n];
        ans[0] = x;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] ^ encoded[i - 1];
        }
        return ans;
    }
}
