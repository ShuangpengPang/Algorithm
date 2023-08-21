package com.shuangpeng.Problem.p2801_2900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2825MakeStringASubsequenceUsingCyclicIncrements（循环增长使字符串子序列等于另一个字符串）
 * @date 2023/8/21 4:53 PM
 */
public class Problem2825MakeStringASubsequenceUsingCyclicIncrements {

    public boolean canMakeSubsequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        int j = 0;
        for (int i = 0; i < n1 && j < n2 && n1 - i >= n2 - j; i++) {
            int d = str1.charAt(i) - str2.charAt(j);
            if (d == 0 || d == -1 || d == 25) {
                j++;
            }
        }
        return j == n2;
    }
}
