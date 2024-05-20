package com.shuangpeng.lcs;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCS001LeastMinutes（下载插件）
 * @date 2024/5/20 10:32 AM
 */
public class LCS001LeastMinutes {

    public int leastMinutes0(int n) {
        int ans = n;
        for (int i = 2, cnt = 1; i <= n; i <<= 1, cnt++) {
            ans = Math.min(ans, cnt + (n + i - 1) / i);
        }
        return ans;
    }

    public int leastMinutes(int n) {
        int x = (int) (Math.log(n * Math.log(2)) / Math.log(2));
        return Math.min(getCount(n, x), getCount(n, x + 1));
    }

    private int getCount(int n, int x) {
        int p = (int) Math.pow(2, x);
        return x + (n + p - 1) / p;
    }
}
