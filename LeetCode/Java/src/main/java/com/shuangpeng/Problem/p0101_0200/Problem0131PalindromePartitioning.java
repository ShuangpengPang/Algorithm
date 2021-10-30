package com.shuangpeng.Problem.p0101_0200;

import java.util.ArrayList;
import java.util.List;

public class Problem0131PalindromePartitioning {

//    public static void main(String[] args) {
//        Problem0131PalindromePartitioning a = new Problem0131PalindromePartitioning();
//        a.partition("aab");
//    }

    public List<List<String>> partition(String s) {
        if (s == null) {
            return new ArrayList<>();
        }
        List<List<String>> result = new ArrayList<>();
        if (s.isEmpty()) {
            List<String> list = new ArrayList<>();
            list.add(s);
            result.add(list);
            return result;
        }
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    public void backtrack(String s, int start, List<String> list, List<List<String>> result) {
        int length = s.length();
        if (start == length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < length; i++) {
            if (isPalindrome(s, start, i)) {
                list.add(s.substring(start, i + 1));
                backtrack(s, i + 1, list, result);
                list.remove(list.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
