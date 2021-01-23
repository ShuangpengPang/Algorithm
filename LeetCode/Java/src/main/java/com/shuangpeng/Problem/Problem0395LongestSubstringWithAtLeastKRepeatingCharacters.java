package com.shuangpeng.Problem;

import java.util.*;

public class Problem0395LongestSubstringWithAtLeastKRepeatingCharacters {

    public int longestSubstring0(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return 0;
        }
        if (k == 0 || k == 1) {
            return s.length();
        }
        return getLongestSubstring(s, k, 0, s.length());
    }

    private int getLongestSubstring(String s, int k, int start, int end) {
        if (end - start < k) {
            return 0;
        }
        int[] hash = new int[26];
        for (int i = start; i < end; i++) {
            hash[s.charAt(i) - 'a']++;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (hash[s.charAt(i) - 'a'] < k) {
                list.add(i);
            }
        }
        if (list.isEmpty()) {
            return end - start;
        }
        list.add(end);
        int previous = 0;
        int max = 0;
        for (int index : list) {
            max = Math.max(max, getLongestSubstring(s, k, previous, index));
            previous = index + 1;
        }
        return max;
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    private int getCount(int[][] tree, int i, int j) {
        int count = 0;
        while (j > 0) {
            count += tree[i][j];
            j -= lowbit(j);
        }
        return count;
    }

    public int longestSubstring1(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return 0;
        }
        if (k == 0 || k == 1) {
            return s.length();
        }
        int length = s.length();
        int[][] tree = new int[26][length + 1];
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            int j = i + 1;
            int index = ch - 'a';
            while (j <= length) {
                tree[index][j]++;
                j += lowbit(j);
            }
        }
        return getLongestSubstring(s, k, tree, 0, length);
    }

    public int getLongestSubstring(String s, int k, int[][] tree, int start, int end) {
        if (end - start < k) {
            return 0;
        }
        int[] hash = new int[26];
        for (int i = start; i < end; i++) {
            int j = s.charAt(i) - 'a';
            int count = hash[j];
            if (count == 0) {
                count = getCount(tree, j, end) - getCount(tree, j, start);
                hash[j] = count;
            }
            if (count < k) {
                return Math.max(getLongestSubstring(s, k, tree, start, i), getLongestSubstring(s, k, tree, i + 1, end));
            }
        }
        return end - start;
    }

    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return 0;
        }
        if (k == 0 || k == 1) {
            return s.length();
        }
        int length = s.length();
        int[][] tree = new int[26][length + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < tree.length; j++) {
                tree[j][i + 1] = tree[j][i];
            }
            tree[s.charAt(i) - 'a'][i + 1]++;
        }
        return calculateLongestSubstring(s, tree, k, 0, length);
    }

    private int calculateLongestSubstring(String s, int[][] tree, int k, int start, int end) {
        if (end - start < k) {
            return 0;
        }
        for (int i = start; i < end; i++) {
            int j = s.charAt(i) - 'a';
            int count = tree[j][end] - tree[j][start];
            if (count < k) {
                return Math.max(calculateLongestSubstring(s, tree, k, start, i),
                        calculateLongestSubstring(s, tree, k, i + 1, end));
            }
        }
        return end - start;
    }
}
