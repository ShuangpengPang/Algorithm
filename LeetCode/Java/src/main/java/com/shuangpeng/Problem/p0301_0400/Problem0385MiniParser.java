package com.shuangpeng.Problem.p0301_0400;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @Description: Problem0385MiniParser
 * @Date 2022/4/19 7:18 PM
 * @Version 1.0
 */
public class Problem0385MiniParser {

    public NestedInteger deserialize(String s) {
        if (s.equals("[]")) {
            return new NestedInteger();
        }
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        NestedInteger nestedInteger = new NestedInteger();
        int n = s.length();
        int count = 0;
        for (int i = 1, j = 1; j < n; ++j) {
            if (j == n - 1 || (count == 0 && s.charAt(j) == ',')) {
                nestedInteger.add(deserialize(s.substring(i, j)));
                i = j + 1;
            } else if (s.charAt(j) == '[') {
                ++count;
            } else if (s.charAt(j) == ']') {
                --count;
            }
        }
        return nestedInteger;
    }
}

class Problem0385MiniParser0 {

    int index = 0;

    public NestedInteger deserialize(String s) {
        int n = s.length();
        if (s.charAt(index) == '[') {
            ++index;
            NestedInteger ni = new NestedInteger();
            while (s.charAt(index) != ']') {
                ni.add(deserialize(s));
                if (s.charAt(index) == ',') {
                    ++index;
                }
            }
            ++index;
            return ni;
        } else {
            boolean isNegative = s.charAt(index) == '-';
            if (isNegative) {
                ++index;
            }
            int num = 0;
            while (index < n && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                num = num * 10 + s.charAt(index) - '0';
                ++index;
            }
            num = isNegative ? -num : num;
            return new NestedInteger(num);
        }
    }
}

class Problem0385MiniParser1 {
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        int n = s.length();
        boolean negative = false;
        int num = 0;
        Deque<NestedInteger> stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '-') {
                negative = true;
            } else if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                stack.add(new NestedInteger());
            } else if (c == ',' || c == ']') {
                char ch = s.charAt(i - 1);
                if (ch >= '0' && ch <= '9') {
                    num = negative ? -num : num;
                    stack.peekLast().add(new NestedInteger(num));
                }
                if (c == ']' && stack.size() > 1) {
                    NestedInteger ni = stack.pollLast();
                    stack.peekLast().add(ni);
                }
                num = 0;
                negative = false;
            }
        }
        return stack.pollLast();
    }
}

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {
    // Constructor initializes an empty nested list.
    public NestedInteger(){}

    // Constructor initializes a single integer.
    public NestedInteger(int value){}

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger(){return false;}

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger(){return 0;}

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value){}

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni){}

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList(){return null;}
}
