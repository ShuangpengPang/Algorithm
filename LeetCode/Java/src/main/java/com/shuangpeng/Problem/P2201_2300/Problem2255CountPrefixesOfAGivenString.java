package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2255CountPrefixesOfAGivenString（统计是给定字符串前缀的字符串数目）
 * @date 2024/3/28 11:23 AM
 */
public class Problem2255CountPrefixesOfAGivenString {

    public int countPrefixes0(String[] words, String s) {
        int cnt = 0;
        for (String w : words) {
            if (s.startsWith(w)) {
                cnt++;
            }
        }
        return cnt;
    }

    public int countPrefixes(String[] words, String s) {
        char[] cs = s.toCharArray();
        int cnt = 0;
        for (String w : words) {
            if (check(w, cs)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean check(String w, char[] cs) {
        char[] arr = w.toCharArray();
        if (arr.length > cs.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != cs[i]) {
                return false;
            }
        }
        return true;
    }
}
