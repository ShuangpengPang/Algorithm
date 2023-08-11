package com.shuangpeng.Problem.p2801_2900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2810FaultyKeyboard（故障键盘）
 * @date 2023/8/11 10:21 AM
 */
public class Problem2810FaultyKeyboard {

    public String finalString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != 'i') {
                sb.append(c);
            } else {
                sb.reverse();
            }
        }
        return sb.toString();
    }
}
