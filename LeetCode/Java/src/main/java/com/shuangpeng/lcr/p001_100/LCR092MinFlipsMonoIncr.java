package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR092MinFlipsMonoIncr（将字符串翻转到单调递增）
 * @date 2024/7/4 5:23 PM
 */
public class LCR092MinFlipsMonoIncr {

    public int minFlipsMonoIncr(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] cnt = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cnt[i] = cnt[i - 1] + cs[i - 1] - '0';
        }
        int ans = cnt[n];
        for (int i = n - 1, c = 0; i >= 0; i--) {
            c += '1' - cs[i];
            ans = Math.min(ans, cnt[i] + c);
        }
        return ans;
    }
}
