package com.shuangpeng.Problem.p0301_0400;

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
