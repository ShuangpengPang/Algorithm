package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3438FindValidPairOfAdjacentDigitsInString（找到字符串中合法的相邻数字）
 * @date 2025/3/10 17:24
 */
public class Problem3438FindValidPairOfAdjacentDigitsInString {

    public String findValidPair(String s) {
        int[] cnt = new int[10];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            cnt[c - '0']++;
        }
        for (int i = 1, n = cs.length; i < n; i++) {
            int d1 = cs[i - 1] - '0', d2 = cs[i] - '0';
            if (d1 != d2 && d1 == cnt[d1] && d2 == cnt[d2]) {
                return "" + cs[i - 1] + cs[i];
            }
        }
        return "";
    }
}
