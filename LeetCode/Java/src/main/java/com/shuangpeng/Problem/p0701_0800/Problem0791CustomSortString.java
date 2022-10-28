package com.shuangpeng.Problem.p0701_0800;

/**
 * @Description: Problem0791CustomSortString（自定义字符串排序）
 * @Date 2022/10/28 2:48 PM
 * @Version 1.0
 */
public class Problem0791CustomSortString {

    public String customSortString(String order, String s) {
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
}
