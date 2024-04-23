package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3110ScoreOfAString（字符串的分数）
 * @date 2024/4/23 11:47 AM
 */
public class Problem3110ScoreOfAString {

    public int scoreOfString(String s) {
        int sum = 0, n = s.length();
        for (int i = 1; i < n; i++) {
            sum += Math.abs(s.charAt(i) - s.charAt(i - 1));
        }
        return sum;
    }
}
