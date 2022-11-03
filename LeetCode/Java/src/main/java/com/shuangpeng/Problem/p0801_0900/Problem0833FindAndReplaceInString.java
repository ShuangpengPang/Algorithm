package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: Problem0833FindAndReplaceInString（字符串中的查找与替换）
 * @Date 2022/11/3 12:04 PM
 * @Version 1.0
 */
public class Problem0833FindAndReplaceInString {

    public String findReplaceString0(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length(), k = indices.length;
        Integer[] ids = new Integer[k];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparingInt(i -> indices[i]));
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i = 0; i < k; i++) {
            int id = ids[i], idx = indices[id];
            sb.append(s, j, idx);
            if (check(s, sources[id], idx)) {
                sb.append(targets[id]);
                j = idx + sources[id].length();
            } else {
                j = idx;
            }
        }
        sb.append(s.substring(j, n));
        return sb.toString();
    }

    private boolean check(String s, String str, int idx) {
        int n = s.length(), m = str.length();
        if (idx + m > n) {
            return false;
        }
        for (int i = 0; i < m; i++) {
            if (s.charAt(i + idx) != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length(), m = indices.length;
        int[] match = new int[n];
        Arrays.fill(match, -1);
        for (int i = 0; i < m; i++) {
            if (s.startsWith(sources[i], indices[i])) {
                match[indices[i]] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n;) {
            int j = match[i];
            if (j != -1) {
                sb.append(targets[j]);
                i += sources[j].length();
            } else {
                sb.append(s.charAt(i++));
            }
        }
        return sb.toString();
    }
}