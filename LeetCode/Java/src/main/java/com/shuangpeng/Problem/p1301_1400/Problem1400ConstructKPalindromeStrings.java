package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1400ConstructKPalindromeStrings（构造K个回文字符串）
 * @date 2023/8/22 2:49 PM
 */
public class Problem1400ConstructKPalindromeStrings {

    public boolean canConstruct(String s, int k) {
        int n = s.length();
        if (n < k) {
            return false;
        }
        int hash = 0;
        for (int i = 0; i < n; i++) {
            hash ^= 1 << s.charAt(i) - 'a';
        }
        int cnt = 0;
        while (hash != 0) {
            cnt++;
            hash ^= hash & -hash;
        }
        return cnt <= k;
    }
}
