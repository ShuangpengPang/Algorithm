package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3456FindSpecialSubstringOfLengthK（找出长度为K的特殊子字符串）
 * @date 2025/3/13 11:20
 */
public class Problem3456FindSpecialSubstringOfLengthK {

    public boolean hasSpecialSubstring0(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int i = 0, j = 0;
        while (i < n) {
            while (j < n && cs[i] == cs[j]) {
                j++;
            }
            if (j - i == k) {
                return true;
            }
            i = j;
        }
        return false;
    }

    public boolean hasSpecialSubstring(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length, cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt++;
            if (i == n - 1 || cs[i] != cs[i + 1]) {
                if (cnt == k) {
                    return true;
                }
                cnt = 0;
            }
        }
        return false;
    }
}
