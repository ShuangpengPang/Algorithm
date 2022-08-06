package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: Problem1408StringMatchingInAnArray（数组中的字符串匹配）
 * @Date 2022/8/6 10:07 AM
 * @Version 1.0
 */
public class Problem1408StringMatchingInAnArray {

    public List<String> stringMatching0(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(a -> a.length()));
        int n = words.length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSubstring(words[i], words[j])) {
                    ans.add(words[i]);
                    break;
                }
            }
        }
        return ans;
    }

    private boolean isSubstring(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        for (int i = 0; i <= n2 - n1; i++) {
            boolean match = true;
            for (int j = 0; j < n1; j++) {
                if (s1.charAt(j) != s2.charAt(i + j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }

    public List<String> stringMatching(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(a -> a.length()));
        int n = words.length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] next = getNext(words[i]);
            for (int j = i + 1; j < n; j++) {
                if (kmp(words[i], words[j], next)) {
                    ans.add(words[i]);
                    break;
                }
            }
        }
        return ans;
    }

    private int[] getNext(String s) {
        int n = s.length();
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int j = i - 1;
            char c = s.charAt(i);
            while (j >= 0 && s.charAt(next[j]) != c) {
                j = next[j] - 1;
            }
            next[i] = j >= 0 ? next[j] + 1 : 0;
        }
        return next;
    }

    private boolean kmp(String s1, String s2, int[] next) {
        int n1 = s1.length(), n2 = s2.length();
        int idx = 0;
        for (int i = 0; i + n1 - idx <= n2 && idx < n1; i++) {
            char c = s2.charAt(i);
            int j = idx;
            while (j > 0 && s1.charAt(j) != c) {
                j = next[j - 1];
            }
            idx = s1.charAt(j) == c ? j + 1 : j;
        }
        return idx == n1;
    }
}
