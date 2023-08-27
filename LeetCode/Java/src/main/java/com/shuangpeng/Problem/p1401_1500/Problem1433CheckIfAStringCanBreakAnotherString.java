package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1433CheckIfAStringCanBreakAnotherString（检查一个字符串是否可以打破另一个字符串）
 * @date 2023/8/25 3:58 PM
 */
public class Problem1433CheckIfAStringCanBreakAnotherString {

    public boolean checkIfCanBreak0(String s1, String s2) {
        int n = s1.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s1.charAt(i) - 'a']++;
            cnt[s2.charAt(i) - 'a']--;
        }
        int i = 0;
        while (i < 26 && cnt[i] == 0) {
            i++;
        }
        int num = cnt[i], sum = 0;
        while (i < 26) {
            sum += cnt[i];
            if (sum != 0 && (sum ^ num) < 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    public boolean checkIfCanBreak(String s1, String s2) {
        int n = s1.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s1.charAt(i) - 'a']++;
            cnt[s2.charAt(i) - 'a']--;
        }
        boolean positive = false, negative = false;
        for (int s = 0, i = 0; i < 26 && (!positive || !negative); i++) {
            s += cnt[i];
            if (s > 0) {
                positive = true;
            } else if (s < 0) {
                negative = true;
            }
        }
        return !positive || !negative;
    }
}
