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
        int n = s.length();
        String ans = s;
        s += s;
        int g1 = gcd(n, b), g2 = gcd(10, a);
        boolean isOdd = (b & 1) == 1;
        for (int i = 0; i < n; i += g1) {
            char[] cs = s.substring(i, i + n).toCharArray();
            add(cs, g2, 1);
            if (isOdd) {
                add(cs, g2, 0);
            }
            String str = new String(cs);
            if (str.compareTo(ans) < 0) {
                ans = str;
            }
        }
        return ans;
    }

    private void add(char[] cs, int g, int start) {
        int minNum = 10, delta = 0, c = cs[start], n = cs.length;
        for (int i = 0; i < 10; i += g) {
            int num = (c - '0' + i) % 10;
            if (num < minNum) {
                minNum = num;
                delta = i;
            }
        }
        for (int i = start; i < n; i += 2) {
            cs[i] = (char) ('0' + (cs[i] - '0' + delta) % 10);
        }
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
