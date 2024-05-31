package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3146PermutationBetweenTwoStrings（两个字符串的排列差）
 * @date 2024/5/31 11:10 AM
 */
public class Problem3146PermutationBetweenTwoStrings {

    public int findPermutationDifference(String s, String t) {
        int[] indices = new int[26];
        char[] cs1 = s.toCharArray(), cs2 = t.toCharArray();
        int sum = 0;
        for (int n = cs1.length, i = 0; i < n; i++) {
            int c1 = cs1[i] - 'a', c2 = cs2[i] - 'a';
            if (indices[c1] == 0) {
                indices[c1] = i + 1;
            } else {
                sum += Math.abs(indices[c1] - i - 1);
            }
            if (indices[c2] == 0) {
                indices[c2] = i + 1;
            } else {
                sum += Math.abs(indices[c2] - i - 1);
            }
        }
        return sum;
    }
}
