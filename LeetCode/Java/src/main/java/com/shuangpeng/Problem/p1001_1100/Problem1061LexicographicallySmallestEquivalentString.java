package com.shuangpeng.Problem.p1001_1100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1061LexicographicallySmallestEquivalentString（按字典序排列最小的等效字符串）
 * @date 2023/5/12 3:53 PM
 */
public class Problem1061LexicographicallySmallestEquivalentString {

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] parent = new int[26];
        Arrays.setAll(parent, i -> i);
        int m = s1.length(), n = baseStr.length();
        for (int i = 0; i < m; i++) {
            union(parent, s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }
        char[] cs = new char[n];
        for (int i = 0; i < n; i++) {
            cs[i] = (char) ('a' + find(parent, baseStr.charAt(i) - 'a'));
        }
        return new String(cs);
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px < py) {
            parent[py] = px;
        } else {
            parent[px] = py;
        }
    }
}
