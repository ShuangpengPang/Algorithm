package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3039ApplyOperationsToMakeStringEmpty（进行操作使字符串为空）
 * @date 2024/2/23 2:25 PM
 */
public class Problem3039ApplyOperationsToMakeStringEmpty {

    public String lastNonEmptyString(String s) {
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
}
