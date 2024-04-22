package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3083ExistenceOfASubstringInAStringAndItsReverse（字符串及其反转中是否存在同一子字符串）
 * @date 2024/4/22 5:36 PM
 */
public class Problem3083ExistenceOfASubstringInAStringAndItsReverse {

    public boolean isSubstringPresent(String s) {
        boolean[][] set = new boolean[26][26];
        char[] cs = s.toCharArray();
        for (int n = cs.length, i = 1; i < n; i++) {
            if (cs[i - 1] == cs[i] || set[cs[i] - 'a'][cs[i - 1] - 'a']) {
                return true;
            }
            set[cs[i - 1] - 'a'][cs[i] - 'a'] = true;
        }
        return false;
    }
}
