package com.shuangpeng.Problem;

import java.util.Deque;
import java.util.LinkedList;

public class Problem0227BasicCalculatorII {

//    public static void main(String[] args) {
//        Problem0227BasicCalculatorII a = new Problem0227BasicCalculatorII();
//        a.calculate(" 3+5 / 2 ");
//    }

    public int calculate(String s) {
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
}
