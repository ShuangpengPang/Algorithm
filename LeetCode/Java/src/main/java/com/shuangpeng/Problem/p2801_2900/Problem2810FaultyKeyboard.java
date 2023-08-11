package com.shuangpeng.Problem.p2801_2900;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2810FaultyKeyboard（故障键盘）
 * @date 2023/8/11 10:21 AM
 */
public class Problem2810FaultyKeyboard {

    public String finalString0(String s) {
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

    public String finalString(String s) {
        int n = s.length();
        boolean reverse = false;
        Deque<Character> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'i') {
                reverse = !reverse;
            } else if (reverse) {
                q.offerFirst(c);
            } else {
                q.offerLast(c);
            }
        }
        int m = q.size();
        char[] cs = new char[m];
        for (int i = reverse ? m - 1 : 0, step = reverse ? -1 : 1; i >= 0 && i < m; i += step) {
            cs[i] = q.pollFirst();
        }
        return new String(cs);
    }
}
