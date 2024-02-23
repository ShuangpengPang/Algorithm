package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3039ApplyOperationsToMakeStringEmpty（进行操作使字符串为空）
 * @date 2024/2/23 2:25 PM
 */
public class Problem3039ApplyOperationsToMakeStringEmpty {

    public String lastNonEmptyString0(String s) {
        char first = s.charAt(0);
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        int[] cnt = new int[26];
        cnt[first - 'a']++;
        int n = s.length();
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            int j = c - 'a';
            cnt[j]++;
            while (sb.length() > 0 && (sb.charAt(sb.length() - 1) == c || cnt[sb.charAt(sb.length() - 1) - 'a'] < cnt[j])) {
                sb.deleteCharAt(sb.length() - 1);
            }
            if (sb.length() == 0 || cnt[sb.charAt(sb.length() - 1) - 'a'] == cnt[j]) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String lastNonEmptyString(String s) {
        int[] cnt = new int[26], last = new int[26];
        char[] cs = s.toCharArray();
        int n = cs.length, maxCnt = 0;
        for (int i = 0; i < n; i++) {
            int c = cs[i] - 'a';
            maxCnt = Math.max(maxCnt, ++cnt[c]);
            last[c] = i;
        }
        Integer[] ids = new Integer[26];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparingInt(a -> last[a]));
        StringBuilder sb = new StringBuilder();
        for (int id : ids) {
            if (cnt[id] == maxCnt) {
                sb.append((char) (id + 'a'));
            }
        }
        return sb.toString();
    }
}
