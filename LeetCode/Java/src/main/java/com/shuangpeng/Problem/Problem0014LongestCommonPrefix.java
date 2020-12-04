package com.shuangpeng.Problem;

public class Problem0014LongestCommonPrefix {

    public String longestCommonPrefix0(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int length = strs[0].length();
        for (int i = 0; i < length; i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != ch) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String commonPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while (j < commonPrefix.length() && j < strs[i].length() && commonPrefix.charAt(j) == strs[i].charAt(j)) {
                j++;
            }
            if (j == 0) {
                return "";
            }
            commonPrefix = commonPrefix.substring(0, j);
        }
        return commonPrefix;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        return divideAndConquer(strs, 0, strs.length - 1);
    }

    public String divideAndConquer(String[] strs, int start, int end) {
        if (start > end) {
            return "";
        }
        if (start == end) {
            return strs[start];
        }
        int mid = (start + end) >> 1;
        String left = divideAndConquer(strs, start, mid);
        String right = divideAndConquer(strs, mid + 1, end);
        int length = Math.min(left.length(), right.length());
        int i = 0;
        while (i < length && left.charAt(i) == right.charAt(i)) {
            i++;
        }
        return left.substring(0, i);
    }
}
