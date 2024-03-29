package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1003CheckIfWordIsValidAfterSubstitutions（检查替换后的词是否有效）
 * @date 2022/11/29 11:58 PM
 */
public class Problem1003CheckIfWordIsValidAfterSubstitutions {

    public boolean isValid0(String s) {
        int n = s.length();
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - 'a';
            if (j == 0) {
                q.push(j);
            } else if (j == 1) {
                if (q.isEmpty() || q.peek() != 0) {
                    return false;
                }
                q.push(j);
            } else {
                if (q.size() < 2) {
                    return false;
                }
                int a = q.pop(), b = q.pop();
                if (a != 1 || b != 0) {
                    return false;
                }
            }
        }
        return q.isEmpty();
    }

    public boolean isValid1(String s) {
        int n = s.length();
        Deque<Character> q = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'b' && (q.isEmpty() || q.peek() != 'a')) {
                return false;
            }
            if (c == 'c') {
                if (q.size() < 2) {
                    return false;
                }
                if (q.pop() != 'b') {
                    return false;
                }
                if (q.pop() != 'a') {
                    return false;
                }
            } else {
                q.push(c);
            }
        }
        return q.isEmpty();
    }

    public boolean isValid2(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            sb.append(c);
            if (c == 'c') {
                if (sb.length() < 3 || !sb.substring(sb.length() - 3).equals("abc")) {
                    return false;
                }
                sb.setLength(sb.length() - 3);
            }
        }
        return sb.length() == 0;
    }

    public boolean isValid3(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
            if (sb.length() >= 3 && sb.substring(sb.length() - 3).equals("abc")) {
                sb.setLength(sb.length() - 3);
            }
        }
        return sb.length() == 0;
    }

    public boolean isValid(String s) {
        char[] cs = s.toCharArray();
        int i = 0;
        for (char c : cs) {
            if (c != 'a' && (i == 0 || c != cs[--i] + 1)) {
                return false;
            }
            if (c != 'c') {
                cs[i++] = c;
            }
        }
        return i == 0;
    }
}
