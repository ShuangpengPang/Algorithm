package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2278PercentageOfLetterInString（字母在字符串中的百分比）
 * @date 2024/3/29 3:25 PM
 */
public class Problem2278PercentageOfLetterInString {

    public int percentageLetter(String s, char letter) {
        int n = s.length(), cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == letter) {
                cnt++;
            }
        }
        return cnt * 100 / n;
    }
}
