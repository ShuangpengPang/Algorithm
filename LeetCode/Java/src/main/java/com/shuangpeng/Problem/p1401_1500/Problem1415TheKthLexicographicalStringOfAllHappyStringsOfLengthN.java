package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1415TheKthLexicographicalStringOfAllHappyStringsOfLengthN（长度为 n 的开心字符串中字典序第 k 小的字符串）
 * @date 2023/8/23 5:23 PM
 */
public class Problem1415TheKthLexicographicalStringOfAllHappyStringsOfLengthN {

    public String getHappyString(int n, int k) {
        char[] cs = new char[n];
        for (int i = 0; i < n; i++) {
            cs[i] = (i & 1) == 0 ? 'a' : 'b';
        }
        for (int i = 2; i <= k; i++) {
            int j = n - 1;
            while (j >= 0) {
                boolean find = false;
                for (char c = (char) (cs[j] + 1); c <= 'c'; c++) {
                    if (j == 0 || c != cs[j - 1]) {
                        cs[j] = c;
                        find = true;
                        break;
                    }
                }
                if (find) {
                    break;
                }
                j--;
            }
            if (j < 0) {
                return "";
            }
            for (int p = j + 1; p < n; p++) {
                for (char c = 'a'; c <= 'c'; c++) {
                    if (c != cs[p - 1]) {
                        cs[p] = c;
                        break;
                    }
                }
            }
        }
        return new String(cs);
    }
}
