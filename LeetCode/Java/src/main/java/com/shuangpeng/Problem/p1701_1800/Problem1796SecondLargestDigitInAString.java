package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1796SecondLargestDigitInAString（字符串中第二大的数字）
 * @date 2022/12/9 12:00 AM
 */
public class Problem1796SecondLargestDigitInAString {

    public int secondHighest(String s) {
        int first = -1, second = -1;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int j = c - '0';
                if (j > first) {
                    second = first;
                    first = j;
                } else if (j < first && j > second) {
                    second = j;
                }
            }
        }
        return second;
    }
}
