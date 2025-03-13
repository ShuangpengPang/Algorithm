package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Probllem3456FindSpecialSubstringOfLengthK（找出长度为K的特殊子字符串）
 * @date 2025/3/13 11:20
 */
public class Probllem3456FindSpecialSubstringOfLengthK {

    public boolean hasSpecialSubstring(String s, int k) {
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
}
