package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem0736ParseLispExpression（Lisp语法解析）
 * @Date 2022/7/6 2:13 PM
 * @Version 1.0
 */
public class Problem0736ParseLispExpression {

    public int evaluate(String expression) {
        return parse(expression, new HashMap<>());
    }

    private int parse(String exp, Map<String, Integer> context) {
        if (exp.charAt(0) != '(') {
            if (context.containsKey(exp)) {
                return context.get(exp);
            }
            return Integer.parseInt(exp);
        }
        String opt = exp.substring(1, 4);
        int n = exp.length();
        if (opt.equals("let")) {
            Deque<String> q = new ArrayDeque<>();
            int index = 5;
            while (index < n - 1) {
                int next = splitString(exp, index);
                q.addLast(exp.substring(index, next));
                index = next + 1;
            }
            String result = q.pollLast();
            Map<String, Integer> current = new HashMap<>(context);
            while (!q.isEmpty()) {
                String v = q.pollFirst();
                String e = q.pollFirst();
                current.put(v, parse(e, current));
            }
            return parse(result, current);
        }
        int index = opt.equals("add") ? 5 : 6;
        int next = splitString(exp, index);
        String e1 = exp.substring(index, next);
        String e2 = exp.substring(next + 1, n - 1);
        int v1 = parse(e1, context), v2 = parse(e2, context);
        if (opt.equals("add")) {
            return v1 + v2;
        }
        return v1 * v2;
    }

    private int splitString(String s, int i) {
        int count = 0;
        int n = s.length();
        while (i < n - 1 && (s.charAt(i) != ' ' || count != 0)) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            i++;
        }
        return i;
    }

//    public static void main(String[] args) {
//        Problem1736LatestTimeByReplacingHiddenDigits0 a = new Problem1736LatestTimeByReplacingHiddenDigits0();
//        String s = "(let x 2 (mult x (let x 3 y 4 (add x y))))";
//        a.evaluate();
//    }
}
