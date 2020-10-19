package com.shuangpeng;

import java.util.ArrayList;
import java.util.List;

public class Problem0022GenerateParentheses {

    // 递归
//    public List<String> generateParenthesis(int n) {
//        if (n == 1) {
//            List<String> list = new ArrayList<>();
//            list.add("()");
//            return list;
//        }
//        List<String> result = new ArrayList<>();
//        List<String> list = generateParenthesis(n - 1);
//        for (String s : list) {
//            int count = 0;
//            for (int i = 0; i < s.length(); i++) {
//                if (count == 0) {
//                    result.add('(' + s.substring(0, i) + ')' + s.substring(i, s.length()));
//                }
//                if (s.charAt(i) == '(') {
//                    count++;
//                } else {
//                    count--;
//                }
//            }
//            result.add('(' + s + ')');
//        }
//        return result;
//    }

    // 回溯
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, n, n, new StringBuilder());
        return result;
    }

    public void backtrack(List<String> result, int left, int right, StringBuilder stringBuilder) {
        // right >= left
        if (left == 0 && right == 0) {
            result.add(stringBuilder.toString());
            return;
        }
        if (left == 0) {
            stringBuilder.append(')');
            backtrack(result, left, right - 1, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return;
        }
        if (left == right) {
            stringBuilder.append('(');
            backtrack(result, left - 1, right, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return;
        }
        if (right > left) {
            stringBuilder.append('(');
            backtrack(result, left - 1, right, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(')');
            backtrack(result, left, right - 1, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}
