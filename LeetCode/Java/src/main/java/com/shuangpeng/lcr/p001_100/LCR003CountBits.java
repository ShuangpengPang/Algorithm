package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR003CountBits（比特位计数）
 * @date 2024/5/6 2:33 PM
 */
public class LCR003CountBits {

    public int[] countBits0(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = (i & 1) == 1 ? ans[i - 1] + 1 : ans[i >> 1];
        }
        return ans;
    }

    public int[] countBits1(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }

    public int[] countBits2(int n) {
        int[] ans = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if (i == (i & -i)) {
                highBit = i;
            }
            ans[i] = ans[i - highBit] + 1;
        }
        return ans;
    }

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}

class LCR003CountBits0 {

    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        dfs(1, 1, n, result);
        return result;
    }

    public void dfs(int i, int countOne, int n, int[] result) {
        if (i > n) {
            return;
        }
        result[i] = countOne;
        dfs(i << 1, countOne, n, result);
        dfs((i << 1) + 1, countOne + 1, n, result);
    }
}
