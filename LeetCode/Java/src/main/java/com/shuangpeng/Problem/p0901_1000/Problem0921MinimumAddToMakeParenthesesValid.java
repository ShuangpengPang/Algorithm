package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0921MinimumAddToMakeParenthesesValid（使括号有效的最少添加）
 * @Date 2022/10/4 1:12 PM
 * @Version 1.0
 */
public class Problem0921MinimumAddToMakeParenthesesValid {

    public int minAddToMakeValid0(String s) {
        int n = s.length();
        int cnt = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                if (cnt < 0) {
                    ans += -cnt;
                    cnt = 1;
                } else {
                    cnt++;
                }
            } else {
                cnt--;
            }
        }
        return ans + Math.abs(cnt);
    }

    public int minAddToMakeValid(String s) {
        int n = s.length();
        int cnt = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                cnt++;
            } else {
                if (cnt == 0) {
                    ans++;
                } else {
                    cnt--;
                }
            }
        }
        return ans + cnt;
    }
}
