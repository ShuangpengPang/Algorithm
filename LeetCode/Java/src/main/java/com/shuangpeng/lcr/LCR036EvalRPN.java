package com.shuangpeng.lcr;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR036EvalRPN（逆波兰表达式求值） https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 * @date 2024/6/22 4:47 PM
 */
public class LCR036EvalRPN {

    public int evalRPN(String[] tokens) {
        Deque<Integer> s = new ArrayDeque<>();
        for (String t : tokens) {
            if (t.equals("+")) {
                int b = s.pollLast(), a = s.pollLast();
                s.offer(a + b);
            } else if (t.equals("-")) {
                int b = s.pollLast(), a = s.pollLast();
                s.offer(a - b);
            } else if (t.equals("*")) {
                int b = s.pollLast(), a = s.pollLast();
                s.offer(a * b);
            } else if (t.equals("/")) {
                int b = s.pollLast(), a = s.pollLast();
                s.offer(a / b);
            } else {
                s.offer(Integer.parseInt(t));
            }
        }
        return s.pollLast();
    }
}

class LCR036EvalRPN0 {

    private String[] tokens;
    int index;

    public int evalRPN(String[] tokens) {
        this.tokens = tokens;
        index = tokens.length - 1;
        return eval();
    }

    private int eval() {
        String s = tokens[index--];
        switch (s) {
            case "+": return eval() + eval();
            case "-": return -eval() + eval();
            case "*": return eval() * eval();
            case "/":
                int divisor = eval();
                return eval() / divisor;
            default: return Integer.valueOf(s);
        }
    }
}
