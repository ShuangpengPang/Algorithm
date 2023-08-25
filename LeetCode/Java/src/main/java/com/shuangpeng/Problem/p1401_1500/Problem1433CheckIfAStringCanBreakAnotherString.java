package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1433CheckIfAStringCanBreakAnotherString（检查一个字符串是否可以打破另一个字符串）
 * @date 2023/8/25 3:58 PM
 */
public class Problem1433CheckIfAStringCanBreakAnotherString {

    public boolean checkIfCanBreak(String s1, String s2) {
        int[] diff = new int[26];
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            diff[s1.charAt(i) - 'a']++;
            diff[s2.charAt(i) - 'a']--;
        }
        int i = 0;
        while (i < 26 && diff[i] == 0) {
            i++;
        }
        if (i == 26) {
            return true;
        }
        for (int j = i + 1, s = diff[i]; j < 26; j++) {
            s += diff[j];
            if (diff[i] < 0 && s > 0 || diff[i] > 0 && s < 0) {
                return false;
            }
        }
        return true;
    }
}
