package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1370IncreasingDecreasingString（上升下降字符串）
 * @date 2024/3/20 4:08 PM
 */
public class Problem1370IncreasingDecreasingString {

    public String sortString(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder(n);
        int c = 0;
        while (c < n) {
            for (int i = 0; i < 26; i++) {
                if (cnt[i] > 0) {
                    cnt[i]--;
                    c++;
                    sb.append((char) (i + 'a'));
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (cnt[i] > 0) {
                    cnt[i]--;
                    c++;
                    sb.append((char) (i + 'a'));
                }
            }
        }
        return sb.toString();
    }
}
