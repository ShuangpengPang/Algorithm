package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

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

class Problem0736ParseLispExpression0 {
    Map<String, Deque<Integer>> scope;
    int pos;

    public int evaluate(String expression) {
        scope = new HashMap<>();
        return parseExpression(expression);
    }

    private int parseExpression(String s) {
        char c = s.charAt(pos);
        if ((c >= '0' && c <= '9') || c == '-') {
            return parseInt(s);
        }
        if (c != '(') {
            return scope.get(parseVar(s)).peek();
        }
        pos++;
        c = s.charAt(pos);
        int ans = 0;
        if (c == 'l') {
            pos += 4;
            List<String> list = new ArrayList<>();
            while (true) {
                if (!Character.isLetter(s.charAt(pos))) {
                    ans = parseExpression(s);
                    break;
                }
                String v = parseVar(s);
                if (s.charAt(pos) == ')') {
                    ans = scope.get(v).peek();
                    break;
                }
                ++pos;
                int e = parseExpression(s);
                list.add(v);
                scope.putIfAbsent(v, new ArrayDeque<>());
                scope.get(v).push(e);
                ++pos;
            }
            for (String v : list) {
                scope.get(v).pop();
            }
        } else if (c == 'a') {
            pos += 4;
            int e1 = parseExpression(s);
            pos++;
            int e2 = parseExpression(s);
            ans = e1 + e2;
        } else {
            pos += 5;
            int e1 = parseExpression(s);
            pos++;
            int e2 = parseExpression(s);
            ans = e1 * e2;
        }
        ++pos;
        return ans;
    }

    private int parseInt(String s) {
        int sign = 1;
        int num = 0;
        int n = s.length();
        if (s.charAt(pos) == '-') {
            sign = -1;
            pos++;
        }
        while (pos < n && Character.isDigit(s.charAt(pos))) {
            num = num * 10 + s.charAt(pos) - '0';
            pos++;
        }
        return sign * num;
    }

    private String parseVar(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        while (pos < n && Character.isLetterOrDigit(s.charAt(pos))) {
            sb.append(s.charAt(pos));
            pos++;
        }
        return sb.toString();
    }
}

class Problem0736ParseLispExpression1 {
    enum ExpStatus {
        VALUE,
        UNKNOWN,
        LET,
        LET1,
        ADD,
        ADD1,
        ADD2,
        MUL,
        MUL1,
        MUL2,
        DONE
    }

    class Exp {
        ExpStatus status;
        int value;
        int e1, e2;

        Exp(ExpStatus s) {
            this.status = s;
        }
    }

    public int evaluate(String expression) {
        int pos = 0, n = expression.length();
        Exp cur = new Exp(ExpStatus.VALUE);
        Deque<Exp> expStack = new ArrayDeque<>();
        Deque<Deque<String>> listStack = new ArrayDeque<>();
        Map<String, Deque<Integer>> context = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        while (pos < n) {
            char c = expression.charAt(pos);
            if (c == ' ') {
                pos++;
                continue;
            }
            if (c == '(') {
                expStack.push(cur);
                cur = new Exp(ExpStatus.UNKNOWN);
                pos++;
                continue;
            } else if (c == ')') {
                if (cur.status == ExpStatus.LET1) {
                    Deque<String> q = listStack.pop();
                    cur.value = parseValue(q.pop(), context);
                    sb = new StringBuilder(Integer.toString(cur.value));
                    while (!q.isEmpty()) {
                        context.get(q.pop()).pop();
                    }
                } else if (cur.status == ExpStatus.ADD2) {
                    sb = new StringBuilder(Integer.toString(cur.e1 + cur.e2));
                } else {
                    sb = new StringBuilder(Integer.toString(cur.e1 * cur.e2));
                }
                cur = expStack.pop();
                pos++;
            } else {
                sb = new StringBuilder();
                while (pos < n && expression.charAt(pos) != ' ' && expression.charAt(pos) != ')') {
                    sb.append(expression.charAt(pos));
                    pos++;
                }
            }
            String s = sb.toString();
            switch (cur.status) {
                case UNKNOWN:
                    if (s.equals("let")) {
                        cur.status = ExpStatus.LET;
                        listStack.push(new ArrayDeque<>());
                    } else if (s.equals("add")) {
                        cur.status = ExpStatus.ADD;
                    } else {
                        cur.status = ExpStatus.MUL;
                    }
                    break;
                case LET:
                    listStack.peek().push(s);
                    cur.status = ExpStatus.LET1;
                    break;
                case LET1:
                    String v = listStack.peek().peek();
                    int value = parseValue(s, context);
                    context.putIfAbsent(v, new ArrayDeque<>());
                    context.get(v).push(value);
                    cur.status = ExpStatus.LET;
                    break;
                case ADD:
                    cur.e1 = parseValue(s, context);
                    cur.status = ExpStatus.ADD1;
                    break;
                case ADD1:
                    cur.e2 = parseValue(s, context);
                    cur.status = ExpStatus.ADD2;
                    break;
                case MUL:
                    cur.e1 = parseValue(s, context);
                    cur.status = ExpStatus.MUL1;
                    break;
                case MUL1:
                    cur.e2 = parseValue(s, context);
                    cur.status = ExpStatus.MUL2;
                    break;
                case VALUE:
                    cur.value = parseInt(s);
                    break;
            }
        }
        return cur.value;
    }

    private int parseValue(String s, Map<String, Deque<Integer>> context) {
        if (Character.isLowerCase(s.charAt(0))) {
            return context.get(s).peek();
        }
        return parseInt(s);
    }

    private int parseInt(String s) {
        int sign = 1, n = s.length();
        int i = 0;
        if (s.charAt(0) == '-') {
            sign = -1;
            i++;
        }
        int num = 0;
        while (i < n) {
            num = num * 10 + s.charAt(i) - '0';
            i++;
        }
        return sign * num;
    }

//    public static void main(String[] args) {
//        Problem0736ParseLispExpression1 a = new Problem0736ParseLispExpression1();
//        String s = "(let x -2 y x y)";
//        a.evaluate(s);
//    }
}

class Problem0736ParseLispExpression2 {
    Map<String, Deque<Integer>> scope = new HashMap<String, Deque<Integer>>();

    public int evaluate(String expression) {
        Deque<Deque<String>> vars = new ArrayDeque<Deque<String>>();
        int start = 0, n = expression.length();
        Deque<Expr> stack = new ArrayDeque<Expr>();
        Expr cur = new Expr(ExprStatus.VALUE);
        while (start < n) {
            if (expression.charAt(start) == ' ') {
                start++; // 去掉空格
                continue;
            }
            if (expression.charAt(start) == '(') {
                start++; // 去掉左括号
                stack.push(cur);
                cur = new Expr(ExprStatus.NONE);
                continue;
            }
            StringBuffer sb = new StringBuffer();
            if (expression.charAt(start) == ')') { // 本质上是把表达式转成一个 token
                start++; // 去掉右括号
                if (cur.status == ExprStatus.LET2) {
                    sb = new StringBuffer(Integer.toString(cur.value));
                    for (String var : vars.peek()) { // 清除作用域
                        scope.get(var).pop();
                    }
                    vars.pop();
                } else if (cur.status == ExprStatus.ADD2) {
                    sb = new StringBuffer(Integer.toString(cur.e1 + cur.e2));
                } else {
                    sb = new StringBuffer(Integer.toString(cur.e1 * cur.e2));
                }
                cur = stack.pop(); // 获取上层状态
            } else {
                while (start < n && expression.charAt(start) != ' ' && expression.charAt(start) != ')') {
                    sb.append(expression.charAt(start));
                    start++;
                }
            }
            String token = sb.toString();
            switch (cur.status.toString()) {
                case "VALUE":
                    cur.value = Integer.parseInt(token);
                    cur.status = ExprStatus.DONE;
                    break;
                case "NONE":
                    if ("let".equals(token)) {
                        cur.status = ExprStatus.LET;
                        vars.push(new ArrayDeque<String>()); // 记录该层作用域的所有变量, 方便后续的清除
                    } else if ("add".equals(token)) {
                        cur.status = ExprStatus.ADD;
                    } else if ("mult".equals(token)) {
                        cur.status = ExprStatus.MULT;
                    }
                    break;
                case "LET":
                    if (expression.charAt(start) == ')') { // let 表达式的最后一个 expr 表达式
                        cur.value = calculateToken(token);
                        cur.status = ExprStatus.LET2;
                    } else {
                        cur.var = token;
                        vars.peek().push(token); // 记录该层作用域的所有变量, 方便后续的清除
                        cur.status = ExprStatus.LET1;
                    }
                    break;
                case "LET1":
                    scope.putIfAbsent(cur.var, new ArrayDeque<Integer>());
                    scope.get(cur.var).push(calculateToken(token));
                    cur.status = ExprStatus.LET;
                    break;
                case "ADD":
                    cur.e1 = calculateToken(token);
                    cur.status = ExprStatus.ADD1;
                    break;
                case "ADD1":
                    cur.e2 = calculateToken(token);
                    cur.status = ExprStatus.ADD2;
                    break;
                case "MULT":
                    cur.e1 = calculateToken(token);
                    cur.status = ExprStatus.MULT1;
                    break;
                case "MULT1":
                    cur.e2 = calculateToken(token);
                    cur.status = ExprStatus.MULT2;
                    break;
            }
        }
        return cur.value;
    }

    public int calculateToken(String token) {
        if (Character.isLowerCase(token.charAt(0))) {
            return scope.get(token).peek();
        } else {
            return Integer.parseInt(token);
        }
    }

    enum ExprStatus {
        VALUE,     // 初始状态
        NONE,      // 表达式类型未知
        LET,       // let 表达式
        LET1,      // let 表达式已经解析了 vi 变量
        LET2,      // let 表达式已经解析了最后一个表达式 expr
        ADD,       // add 表达式
        ADD1,      // add 表达式已经解析了 e1 表达式
        ADD2,      // add 表达式已经解析了 e2 表达式
        MULT,      // mult 表达式
        MULT1,     // mult 表达式已经解析了 e1 表达式
        MULT2,     // mult 表达式已经解析了 e2 表达式
        DONE       // 解析完成
    }

    class Expr {
        ExprStatus status;
        String var; // let 的变量 vi
        int value; // VALUE 状态的数值，或者 LET2 状态最后一个表达式的数值
        int e1, e2; // add 或 mult 表达式的两个表达式 e1 和 e2 的数值

        public Expr(ExprStatus s) {
            status = s;
        }
    }
}
