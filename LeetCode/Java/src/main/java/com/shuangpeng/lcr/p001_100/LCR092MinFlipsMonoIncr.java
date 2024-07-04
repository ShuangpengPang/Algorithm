package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR092MinFlipsMonoIncr（将字符串翻转到单调递增）
 * @date 2024/7/4 5:23 PM
 */
public class LCR092MinFlipsMonoIncr {

    public int minFlipsMonoIncr0(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, ones = 0;
        for (char c : cs) {
            ones += c - '0';
        }
        int ans = ones;
        for (int i = 0, cnt = 0; i < n; i++) {
            ans = Math.min(ans, cnt + n - i -ones);
            if (cs[i] == '1') {
                cnt++;
                ones--;
            }
        }
        return ans;
    }

    public int minFlipsMonoIncr(String s) {
        int d0 = 0, d1 = 0;
        for (char c : s.toCharArray()) {
            d1 = Math.min(d0, d1);
            if (c == '0') {
                d1++;
            } else {
                d0++;
            }
        }
        return Math.min(d0, d1);
    }
}
