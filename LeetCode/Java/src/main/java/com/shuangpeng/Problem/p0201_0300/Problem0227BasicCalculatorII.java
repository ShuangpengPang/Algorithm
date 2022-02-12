package com.shuangpeng.Problem.p0201_0300;

import java.util.Deque;
import java.util.LinkedList;

public class Problem0227BasicCalculatorII {

//    public static void main(String[] args) {
//        Problem0227BasicCalculatorII a = new Problem0227BasicCalculatorII();
//        a.calculate("42");
//    }

    public int calculate0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Integer> dataStack = new LinkedList<>();
        Deque<Character> operatorStack = new LinkedList<>();
        s = s.replace(" ", "") + ' ';
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch >= '0' && ch <= '9') {
                builder.append(ch);
            } else {
                dataStack.offer(Integer.parseInt(builder.toString()));
                builder.delete(0, builder.length());
                if (!operatorStack.isEmpty()) {
                    char operator = operatorStack.peekLast();
                    if (operator == '*' || operator == '/') {
                        operatorStack.pollLast();
                        int b = dataStack.pollLast();
                        int a = dataStack.pollLast();
                        if (operator == '*') {
                            dataStack.offer(a * b);
                        } else {
                            if (b == 0) {
                                return 0;
                            }
                            dataStack.offer(a / b);
                        }
                    }
                }
                if (ch != ' ') {
                    operatorStack.offer(ch);
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            char operator = operatorStack.pollFirst();
            int a = dataStack.pollFirst();
            int b = dataStack.pollFirst();
            if (operator == '+') {
                dataStack.offerFirst(a + b);
            } else {
                dataStack.offerFirst(a - b);
            }
        }
        return dataStack.pollLast();
    }

    public int calculate1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch >= '0' && ch <= '9') {
                num = num * 10 + (ch - '0');
            }
            if (ch == '+' || ch == '-'
                    || ch == '*' || ch == '/'
                    || i == chars.length - 1) {
                if (sign == '+') {
                    stack.offer(num);
                } else if (sign == '-') {
                    stack.offer(-num);
                } else if (sign == '*') {
                    int data = stack.pollLast();
                    stack.offer(data * num);
                } else if (sign == '/') {
                    int data = stack.pollLast();
                    stack.offer(data / num);
                }
                sign = ch;
                num = 0;
            }
        }
        int answer = 0;
        while (!stack.isEmpty()) {
            answer += stack.poll();
        }
        return answer;
    }

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        Deque<Integer> stack = new LinkedList<>();
        char sign = '+';
        int num = 0;
        for (int i = 0; i <= n; i++) {
            char c = '+';
            if (i < n) {
                c = s.charAt(i);
                if (c == ' ') {
                    continue;
                }
                if (Character.isDigit(c)) {
                    while (i < n && Character.isDigit(s.charAt(i))) {
                        num = num * 10 + s.charAt(i) - '0';
                        i++;
                    }
                    i--;
                    continue;
                }
            }
            if (sign == '*') {
                num = stack.pollLast() * num;
                stack.offerLast(num);
            } else if (sign == '/') {
                num = stack.pollLast() / num;
                stack.offerLast(num);
            } else if (sign == '+') {
                stack.offerLast(num);
            } else if (sign == '-') {
                stack.offerLast(-num);
            }
            num = 0;
            sign = c;
        }
        int answer = 0;
        while (!stack.isEmpty()) {
            answer += stack.pollLast();
        }
        return answer;
    }
}
