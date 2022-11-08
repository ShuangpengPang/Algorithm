package com.shuangpeng.Problem.p1601_1700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1684CountTheNumberOfConsistentStrings（统计一致字符串的数目）
 * @date 2022/11/8 10:04 AM
 */
public class Problem1684CountTheNumberOfConsistentStrings {

    public int countConsistentStrings(String allowed, String[] words) {
        int n = allowed.length();
        boolean[] map = new boolean[26];
        for (int i = 0; i < n; i++) {
            map[allowed.charAt(i) - 'a'] = true;
        }
        int ans = 0;
        for (String w : words) {
            int m = w.length();
            boolean valid = true;
            for (int i = 0; i < m; i++) {
                if (!map[w.charAt(i) - 'a']) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans++;
            }
        }
        return ans;
    }
}