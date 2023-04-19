package com.shuangpeng.Problem.p1601_1700;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1625LexicographicallySmallestStringAfterApplyingOperations（执行操作后字典序最小的字符串）
 * @date 2023/4/18 6:04 PM
 */
public class Problem1625LexicographicallySmallestStringAfterApplyingOperations {

    String ans;

    public String findLexSmallestString0(String s, int a, int b) {
        ans = s;
        dfs(s, a, b, new HashSet<>());
        return ans;
    }

    private void dfs(String s, int a, int b, Set<String> set) {
        if (!set.add(s)) {
            return;
        }
        if (s.compareTo(ans) < 0) {
            ans = s;
        }
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 1; i < n; i += 2) {
            cs[i] = (char) ('0' + (cs[i] - '0' + a) % 10);
        }
        dfs(new String(cs), a, b, set);
        dfs(s.substring(n - b) + s.substring(0, n - b), a, b, set);
    }

    public String findLexSmallestString1(String s, int a, int b) {
        String ans = s;
        Set<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(s);
        set.add(s);
        int n = s.length();
        while (!q.isEmpty()) {
            String str = q.poll();
            if (str.compareTo(ans) < 0) {
                ans = str;
            }
            char[] cs = str.toCharArray();
            for (int i = 1; i < n; i += 2) {
                cs[i] = (char) ('0' + (cs[i] - '0' + a) % 10);
            }
            String s1 = new String(cs), s2 = str.substring(n - b) + str.substring(0, n - b);
            if (set.add(s1)) {
                q.add(s1);
            }
            if (set.add(s2)) {
                q.add(s2);
            }
        }
        return ans;
    }

    public String findLexSmallestString(String s, int a, int b) {
        char[] cs = s.toCharArray();
        int n = s.length();
        boolean[] visited = new boolean[n];
        String ans = s;
        boolean isOdd = (b & 1) == 1;
        for (int i = 0; !visited[i]; i = (i + b) % n) {
            visited[i] = true;
            boolean[] even = new boolean[10];
            char[] copy = getRotate(cs, i);
            for (int j = 0; !even[j]; j = (j + a) % 10) {
                even[j] = true;
                char[] tmp = copy.clone();
                for (int k = 1; k < n; k += 2) {
                    tmp[k] = (char) ('0' + (tmp[k] - '0' + j) % 10);
                }
                if (isOdd) {
                    boolean[] odd = new boolean[10];
                    for (int k = 0; !odd[k]; k = (k + a) % 10) {
                        odd[k] = true;
                        String str = new String(tmp);
                        if (str.compareTo(ans) < 0) {
                            ans = str;
                        }
                        for (int x = 0; x < n; x += 2) {
                            tmp[x] = (char) ('0' + (tmp[x] - '0' + a) % 10);
                        }
                    }
                } else {
                    String str = new String(tmp);
                    if (str.compareTo(ans) < 0) {
                        ans = str;
                    }
                }
            }
        }
        return ans;
    }

    private char[] getRotate(char[] cs, int i) {
        int n = cs.length;
        char[] ans = new char[n];
        for (int j = i; j < n; j++) {
            ans[j - i] = cs[j];
        }
        for (int j = 0; j < i; j++) {
            ans[n - i + j] = cs[j];
        }
        return ans;
    }
}
