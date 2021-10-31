package com.shuangpeng.Problem.p0201_0300;

import java.util.Deque;
import java.util.LinkedList;

public class Problem0224BasicCalculator {

    public int calculate0(String s) {
        return getResult(s);
    }

    private int getResult(String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        int num = 0;
        int factor = 1;
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                result += num * factor;
                factor = 1;
                num = 0;
            } else if (c == '-') {
                result += num * factor;
                factor = -1;
                num = 0;
            } else if (c == '(') {
                int index = i + 1;
                int count = 0;
                while (index < string.length()) {
                    char ch = string.charAt(index);
                    if (ch == '(') {
                        count++;
                    } else if (ch == ')') {
                        if (count == 0) {
                            break;
                        }
                        count--;
                    }
                    index++;
                }
                num = getResult(string.substring(i + 1, index));
                i = index;
            }
        }
        result += num * factor;
        return result;
    }

    public int calculate1(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        Deque<Integer> stack = new LinkedList<>();
        stack.offerLast(1);
        int sign = 1;
        int answer = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '+') {
                sign = stack.peekLast();
            } else if (c == '-') {
                sign = -stack.peekLast();
            } else if (c == '(') {
                stack.offerLast(sign);
            } else if (c == ')') {
                stack.pollLast();
            } else if (Character.isDigit(c)) {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                answer += num * sign;
            }
        }
        return answer;
    }
}
