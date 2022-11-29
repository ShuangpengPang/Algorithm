package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1758MinimumChangesToMakeAlternatingBinaryString（生成交替二进制字符串的最少操作数）
 * @date 2022/11/29 9:56 AM
 */
public class Problem1758MinimumChangesToMakeAlternatingBinaryString {

    public int minOperations(String s) {
        int n = s.length(), c = 0, c0 = 0, c1 = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if ((i & 1) == 0) {
                c++;
                if (ch == '1') {
                    c0++;
                }
            } else if (ch == '1') {
                c1++;
            }
        }
        return Math.min(c - c0 + c1, c0 + n - c - c1);
    }
}
