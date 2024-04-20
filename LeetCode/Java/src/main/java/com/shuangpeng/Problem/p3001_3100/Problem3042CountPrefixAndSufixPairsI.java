package com.shuangpeng.Problem.p3001_3100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3042CountPrefixAndSufixPairsI（统计前后缀下标对I）
 * @date 2024/4/20 11:07 AM
 */
public class Problem3042CountPrefixAndSufixPairsI {

    public int countPrefixSuffixPairs0(String[] words) {
        int n = words.length;
        char[][] arr = new char[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = words[i].toCharArray();
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans += getCount(arr[i], arr[j]);
            }
        }
        return ans;
    }

    private int getCount(char[] cs1, char[] cs2) {
        if (cs1.length > cs2.length) {
            return 0;
        }
        int n = cs1.length, m = cs2.length;
        for (int i = 0; i < n; i++) {
            if (cs1[i] != cs2[i] || cs1[i] != cs2[i + m - n]) {
                return 0;
            }
        }
        return 1;
    }

    public int countPrefixSuffixPairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        int ans = 0;
        for (String w : words) {
            ans += map.getOrDefault(w, 0);
            int[] next = getNext(w.toCharArray());
            int j = next[w.length() - 1];
            while (j > 0) {
                ans += map.getOrDefault(w.substring(0, j), 0);
                j = next[j - 1];
            }
            map.merge(w, 1, Integer::sum);
        }
        return ans;
    }

    private int[] getNext(char[] cs) {
        int n = cs.length;
        int[] next = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && cs[j] != cs[i]) {
                j = next[j - 1];
            }
            next[i] = cs[i] == cs[j] ? ++j : j;
        }
        return next;
    }
}
