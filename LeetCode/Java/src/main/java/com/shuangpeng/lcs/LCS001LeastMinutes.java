package com.shuangpeng.lcs;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCS001LeastMinutes（下载插件）
 * @date 2024/5/20 10:32 AM
 */
public class LCS001LeastMinutes {

    public int leastMinutes(int n) {
        int ans = n;
        for (int i = 2, cnt = 1; i <= n; i <<= 1, cnt++) {
            ans = Math.min(ans, cnt + (n + i - 1) / i);
        }
        return ans;
    }
}
