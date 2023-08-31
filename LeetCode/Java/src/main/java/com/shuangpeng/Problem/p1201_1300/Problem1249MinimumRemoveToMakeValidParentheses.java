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

    public String minRemoveToMakeValid0(String s) {
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

    public String minRemoveToMakeValid1(String s) {
        int n = s.length(), cnt = 0, cur = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ')') {
                cnt++;
                cur++;
            } else if (c == '(' && cur > 0) {
                cur--;
            }
        }
        cnt -= cur;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(' && cnt > 0) {
                sb.append(c);
                cnt--;
            } else if (c == ')' && cur > 0) {
                cur--;
            } else if (c != '(') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String minRemoveToMakeValid2(String s) {
        int n = s.length(), cnt = 0, cur = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                cnt++;
                cur++;
            } else if (c == ')' && cur > 0) {
                cur--;
            }
        }
        cnt -= cur;
        cur = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(' && cnt > 0) {
                cnt--;
                cur++;
                sb.append(c);
            } else if (c == ')' && cur > 0) {
                cur--;
                sb.append(c);
            } else if (c != '(' && c != ')') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String minRemoveToMakeValid(String s) {
        int n = s.length(), total = 0, cnt = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == '(') {
                total++;
                cnt++;
            } else if (c == ')' && cnt > 0) {
                cnt--;
            }
        }
        total -= cnt;
        cnt = 0;
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '(') {
                if (total > 0) {
                    total--;
                    cnt++;
                    cs[p++] = cs[i];
                }
            } else if (cs[i] == ')') {
                if (cnt > 0) {
                    cnt--;
                    cs[p++] = cs[i];
                }
            } else {
                cs[p++] = cs[i];
            }
        }
        return new String(cs, 0, p);
    }
}
