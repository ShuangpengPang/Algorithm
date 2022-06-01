package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem1106ParsingABooleanExpression（解析布尔表达式）
 * @Date 2022/5/31 4:00 PM
 * @Version 1.0
 */
public class Problem1106ParsingABooleanExpression {

    int index = 0;

    public boolean parseBoolExpr(String expression) {
        boolean ans = false;
        char c = expression.charAt(index);
        if (c == '(') {
            ++index;
            ans = parseBoolExpr(expression);
            ++index;
        } else if (c == 't') {
            ans = true;
            ++index;
        } else if (c == 'f') {
            ans = false;
            ++index;
        } else if (c == '!') {
            index += 2;
            ans = !parseBoolExpr(expression);
            ++index;
        } else {
            ++index;
            ans = c == '&' ? true : false;
            while (expression.charAt(index) != ')') {
                ++index;
                ans = c == '&' ? parseBoolExpr(expression) && ans : parseBoolExpr(expression) || ans;
            }
            ++index;
        }
        return ans;
    }
}

class Problem1106ParsingABooleanExpression0 {

    public boolean parseBoolExpr(String expression) {
        return parse(expression.toCharArray(), 0, expression.length());
    }

    private boolean parse(char[] chars, int s, int e) {
        if (chars[s] == '(') {
            return parse(chars, s + 1, e - 1);
        }
        char c = chars[s];
        if (c == 't') {
            return true;
        }
        if (c == 'f') {
            return false;
        }
        if (c == '!') {
            return !parse(chars, s + 2, e - 1);
        }
        int i = s + 2;
        boolean ans = c == '&' ? true : false;
        while (i < e - 1 && (c == '&' && ans || c == '|' && !ans)) {
            int count = 0;
            int j = i;
            while (j < e - 1 && (count > 0 || chars[j] != ',')) {
                if (chars[j] == '(') {
                    ++count;
                } else if (chars[j] == ')') {
                    --count;
                }
                ++j;
            }
            ans = c == '&' ? parse(chars, i, j) && ans : parse(chars, i, j) || ans;
            i = j + 1;
        }
        return ans;
    }
}

// 栈方法
class Problem1106ParsingABooleanExpression1 {

    public boolean parseBoolExpr0(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = expression.length();
        for (int i = 0; i < n; ++i) {
            char c = expression.charAt(i);
            if (c == ',') {
                continue;
            }
            if (c != ')') {
                stack.push(c);
            } else {
                boolean hasTrue = false, hasFalse = false;
                while (stack.peek() != '(') {
                    char ch = stack.pop();
                    hasTrue = hasTrue || ch == 't';
                    hasFalse = hasFalse || ch == 'f';
                }
                stack.pop();
                char opt = stack.pop();
                boolean ans = false;
                if (opt == '!') {
                    ans = hasFalse;
                } else if (opt == '&') {
                    ans = !hasFalse;
                } else {
                    ans = hasTrue;
                }
                stack.push(ans ? 't' : 'f');
            }
        }
        return stack.pop() == 't' ? true : false;
    }
}


