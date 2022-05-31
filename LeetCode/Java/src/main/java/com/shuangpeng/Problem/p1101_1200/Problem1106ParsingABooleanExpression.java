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

    public boolean parseBoolExpr(String expression) {
        // 使用双端队列来模拟栈
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : expression.toCharArray()) {
            // 跳过就好了，不需要往栈里面放
            if (c == ',') continue;
            // 放进栈里面去，标记嵌套的起始位置
            if (c != ')') stack.addLast(c);
            else {
                // 创建一个新栈
                Deque<Character> buffer = new ArrayDeque<>();
                // 把括号中间的字符都放到新栈中去
                while (stack.peekLast() != '(') buffer.push(stack.pollLast());
                stack.pollLast();   // 去掉 ( 字符
                // 再处理新栈中的表达式，获取处理结果 ret
                char ret = helper(buffer, stack.pollLast());
                // 再把处理结果放到栈中
                stack.offerLast(ret);
            }
        }
        return stack.peek() == 't';
    }

    private char helper(Deque<Character> buffer, char operator) {
        // 如果 `operator == !` 那么说明新栈里面只会有一个字符，反转就好了
        if (operator == '!') return buffer.pollLast() == 't' ? 'f' : 't';
        if (operator == '&') {
            // 如果是 & 那么就需要 buffer 里面全是 t 时，才返回 t，只要有一个 f 就返回 f
            while (!buffer.isEmpty()) {
                if (buffer.pollLast() == 'f') return 'f';
            }
            return 't';
        } else {
            // 如果是 | 那么就需要 buffer 里面全是 f 时，才返回 f，只要有一个 t，就返回 t
            while (!buffer.isEmpty()) {
                if (buffer.pollLast() == 't') return 't';
            }
            return 'f';
        }
    }
}


