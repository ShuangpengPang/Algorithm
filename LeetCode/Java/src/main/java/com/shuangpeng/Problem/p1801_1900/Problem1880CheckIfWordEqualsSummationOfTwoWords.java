package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1880CheckIfWordEqualsSummationOfTwoWords（检查某单词是否等于两单词之和）
 * @date 2024/3/20 3:34 PM
 */
public class Problem1880CheckIfWordEqualsSummationOfTwoWords {

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return getNumber(firstWord) + getNumber(secondWord) == getNumber(targetWord);
    }

    private int getNumber(String s) {
        int ans = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            ans = ans * 10 + s.charAt(i) - 'a';
        }
        return ans;
    }
}
