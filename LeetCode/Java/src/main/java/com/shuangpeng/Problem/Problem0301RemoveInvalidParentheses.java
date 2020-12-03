package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem0301RemoveInvalidParentheses {

//    Set<String> resultSet = new HashSet<>();
    int minOperation = Integer.MAX_VALUE;
    public List<String> removeInvalidParentheses0(String s) {
        resultSet.clear();
        recurse(s, 0, 0, 0, 0, new StringBuilder());
        return new ArrayList<>(resultSet);
    }

    // 回溯
    public void recurse(String s, int index, int removeCount, int leftCount, int rightCount, StringBuilder result) {
        if (index == s.length()) {
            if (leftCount == rightCount && removeCount <= minOperation) {
                if (removeCount < minOperation) {
                    resultSet.clear();
                    minOperation = removeCount;
                }
                resultSet.add(result.toString());
            }
        } else {
            char ch = s.charAt(index);
            int length = result.length();
            if (ch != '(' && ch != ')') {
                result.append(ch);
                recurse(s, index + 1, removeCount, leftCount, rightCount, result);
                result.deleteCharAt(length);
            } else if (ch == '(') {
                recurse(s, index + 1, removeCount + 1, leftCount, rightCount, result);

                result.append(ch);
                recurse(s, index + 1, removeCount, leftCount + 1, rightCount, result);
                result.deleteCharAt(length);
            } else {
                recurse(s, index + 1, removeCount + 1, leftCount, rightCount, result);
                if (leftCount > rightCount) {
                    result.append(ch);
                    recurse(s, index + 1, removeCount, leftCount, rightCount + 1, result);
                    result.deleteCharAt(length);
                }
            }
        }
    }


    Set<String> resultSet = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        resultSet.clear();
        int leftRem = 0;
        int rightRem = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftRem++;
            } else if (ch == ')') {
                rightRem += leftRem > 0 ? 0 : 1;
                leftRem -= leftRem > 0 ? 1 : 0;
            }
        }
        backtrack(s, 0, 0, 0, leftRem, rightRem, new StringBuilder());
        return new ArrayList<>(resultSet);
    }

    public void backtrack(String s, int index, int leftCount, int rightCount, int leftRem, int rightRem, StringBuilder result) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                resultSet.add(result.toString());
            }
        } else {
            char ch = s.charAt(index);
            // 删除
            if (ch == '(' && leftRem > 0) {
                backtrack(s, index + 1, leftCount, rightCount, leftRem - 1, rightRem, result);
            } else if (ch == ')' && rightRem > 0) {
                backtrack(s, index + 1, leftCount, rightCount, leftRem, rightRem - 1, result);
            }

            int length = result.length();
            result.append(ch);
            if (ch != '(' && ch != ')') {
                backtrack(s, index + 1, leftCount, rightCount, leftRem, rightRem, result);
            } else if (ch == '(') {
                backtrack(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, result);
            } else if (ch == ')' && leftCount > rightCount) {
                backtrack(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, result);
            }
            result.deleteCharAt(length);
        }
    }
}
