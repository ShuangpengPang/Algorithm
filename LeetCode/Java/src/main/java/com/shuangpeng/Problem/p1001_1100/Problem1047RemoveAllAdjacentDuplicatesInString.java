package com.shuangpeng.Problem.p1001_1100;

import java.util.Deque;
import java.util.LinkedList;

public class Problem1047RemoveAllAdjacentDuplicatesInString {

    public String removeDuplicates0(String S) {
        if (S == null || S.length() <= 1) {
            return S;
        }
        Deque<Character> stack = new LinkedList<>();
        int n = S.length();
        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);
            if (!stack.isEmpty() && stack.peekLast() == c) {
                stack.pollLast();
            } else {
                stack.offerLast(c);
            }
        }
        StringBuilder builder = new StringBuilder(stack.size());
        while (!stack.isEmpty()) {
            builder.append(stack.pollFirst());
        }
        return builder.toString();
    }

    public String removeDuplicates(String S) {
        if (S == null || S.length() <= 1) {
            return S;
        }
        int n = S.length();
        StringBuilder builder = new StringBuilder(n);
        int top = -1;
        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);
            if (top >= 0 && builder.charAt(top) == c) {
                builder.deleteCharAt(top);
                top--;
            } else {
                builder.append(c);
                top++;
            }
        }
        return builder.toString();
    }
}
