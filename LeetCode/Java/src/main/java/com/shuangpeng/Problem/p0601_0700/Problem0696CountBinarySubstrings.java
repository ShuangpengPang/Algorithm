package com.shuangpeng.Problem.p0601_0700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0696CountBinarySubstrings（计数二进制子串）
 * @date 2023/4/21 6:00 PM
 */
public class Problem0696CountBinarySubstrings {

    public int countBinarySubstrings0(String s) {
        int n = s.length(), ans = 0;
        boolean valid = false;
        for (int i = 1, p = -1; i < n; i++) {
            char c = s.charAt(i);
            if (c != s.charAt(i - 1)) {
                p = i;
                valid = true;
                ans++;
            } else {
                int index = (p << 1) - i - 1;
                if (valid && index >= 0 && s.charAt(index) != c) {
                    ans++;
                } else {
                    valid = false;
                }
            }
        }
        return ans;
    }

    public int countBinarySubstrings(String s) {
        int n = s.length(), p = 0, c = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || s.charAt(i) == s.charAt(i - 1)) {
                c++;
            } else {
                ans += Math.min(p, c);
                p = c;
                c = 1;
            }
        }
        return ans + Math.min(p, c);
    }
}
