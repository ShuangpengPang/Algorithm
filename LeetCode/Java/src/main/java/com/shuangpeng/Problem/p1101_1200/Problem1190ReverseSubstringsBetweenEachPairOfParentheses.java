package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1190ReverseSubstringsBetweenEachPairOfParentheses（反转每对括号间的子串）
 * @date 2023/6/9 7:55 PM
 */
public class Problem1190ReverseSubstringsBetweenEachPairOfParentheses {

    int index;

    public String reverseParentheses(String s) {
        index = 0;
        return dfs(s.toCharArray()).toString();
    }

    private StringBuilder dfs(char[] cs) {
        int n = cs.length;
        StringBuilder ans = new StringBuilder();
        while (index < n && cs[index] != ')') {
            if (cs[index] != '(') {
                ans.append(cs[index]);
            } else {
                index++;
                StringBuilder sb = dfs(cs);
                ans.append(sb.reverse());
            }
            index++;
        }
        return ans;
    }
}

class Problem1190ReverseSubstringsBetweenEachPairOfParentheses0 {

    public String reverseParentheses(String s) {
        Deque<StringBuilder> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                q.push(sb);
                sb = new StringBuilder();
            } else if (c == ')') {
                sb = q.pop().append(sb.reverse());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
