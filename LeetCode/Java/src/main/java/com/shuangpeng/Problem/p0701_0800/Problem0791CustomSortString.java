package com.shuangpeng.Problem.p0701_0800;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: Problem0791CustomSortString（自定义字符串排序）
 * @Date 2022/10/28 2:48 PM
 * @Version 1.0
 */
public class Problem0791CustomSortString {

    public String customSortString0(String order, String s) {
        int n = s.length(), m = order.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            char c = order.charAt(i);
            int j = c - 'a';
            while (cnt[j] > 0) {
                sb.append(c);
                cnt[j]--;
            }
        }
        for (int i = 0; i < 26; i++) {
            while (cnt[i] > 0) {
                sb.append((char) (i + 'a'));
                cnt[i]--;
            }
        }
        return sb.toString();
    }

    public String customSortString(String order, String s) {
        int[] val  = new int[26];
        int m = order.length(), n = s.length();
        for (int i = 0; i < m; i++) {
            val[order.charAt(i) - 'a'] = i + 1;
        }
        Character[] cs = new Character[n];
        for (int i = 0; i < n; i++) {
            cs[i] = s.charAt(i);
        }
        Arrays.sort(cs, Comparator.comparingInt(a -> val[a - 'a']));
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            sb.append(c);
        }
        return sb.toString();
    }
}
