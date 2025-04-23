package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1529MinimumSuffixFlips（最少的后缀翻转次数）
 * @date 2025/4/23 16:32
 */
public class Problem1529MinimumSuffixFlips {

    public int minFlips0(String target) {
        int ans = 0, n = target.length();
        for (int i = n - 1; i >= 0; i--) {
            if (target.charAt(i) == '1') {
                if (i == n - 1) {
                    ans++;
                } else if (target.charAt(i + 1) == '0') {
                    ans += 2;
                }
            }
        }
        return ans;
    }

    public int minFlips(String target) {
        int ans = 0, pre = 0;
        for (char ch : target.toCharArray()) {
            int c = ch - '0';
            ans += c ^ pre;
            pre = c;
        }
        return ans;
    }
}
