package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1249MinimumRemoveToMakeValidParentheses（移除无效的括号）
 * @date 2023/6/14 2:19 PM
 */
public class Problem1249MinimumRemoveToMakeValidParentheses {

    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        boolean[] valid = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                q.push(i);
            } else if (c == ')') {
                if (!q.isEmpty()) {
                    valid[q.pop()] = valid[i] = true;
                }
            } else {
                valid[i] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (valid[i]) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
