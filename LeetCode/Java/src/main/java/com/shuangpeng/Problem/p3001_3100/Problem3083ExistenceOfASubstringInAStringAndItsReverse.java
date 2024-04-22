package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3083ExistenceOfASubstringInAStringAndItsReverse（字符串及其反转中是否存在同一子字符串）
 * @date 2024/4/22 5:36 PM
 */
public class Problem3083ExistenceOfASubstringInAStringAndItsReverse {

    public boolean isSubstringPresent0(String s) {
        boolean[][] set = new boolean[26][26];
        char[] cs = s.toCharArray();
        for (int n = cs.length, i = 1; i < n; i++) {
            set[cs[i - 1] - 'a'][cs[i] - 'a'] = true;
            if (set[cs[i] - 'a'][cs[i - 1] - 'a']) {
                return true;
            }
        }
        return false;
    }

    public boolean isSubstringPresent(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, N = Integer.MAX_VALUE;
        int[] set = new int[26];
        for (int i = 1; i < n; i++) {
            int x = cs[i] - 'a', y = cs[i - 1] - 'a';
            set[x] |= 1 << y;
            if ((set[y] >> x & 1) == 1) {
                return true;
            }
        }
        return false;
    }
}
