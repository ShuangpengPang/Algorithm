package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1545FindKthBitInNthBinaryString（找出第N个二进制字符串中的第K位）
 * @date 2025/4/23 19:07
 */
public class Problem1545FindKthBitInNthBinaryString {

    public char findKthBit0(int n, int k) {
        return (char) (dfs(n - 1, k) + '0');
    }

    private int dfs(int n, int k) {
        if (n == 0 || k == 1) {
            return 0;
        }
        int h = 1 << n;
        if (k == h) {
            return 1;
        }
        if (k < h) {
            return dfs(n - 1, k);
        }
        return dfs(n - 1, (h << 1) - k) ^ 1;
    }

    public char findKthBit(int n, int k) {
        int b = 0;
        for (int i = n - 1; i > 0; i--) {
            int h = 1 << i;
            if (k > h) {
                b ^= 1;
                k = (h << 1) - k;
            } else if (k == h) {
                b ^= 1;
                break;
            } else if (k == 1) {
                break;
            }
        }
        return (char) (b + '0');
    }
}
