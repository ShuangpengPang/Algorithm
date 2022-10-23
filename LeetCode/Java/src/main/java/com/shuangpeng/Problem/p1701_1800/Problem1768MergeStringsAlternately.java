package com.shuangpeng.Problem.p1701_1800;

/**
 * @Description: Problem1768MergeStringsAlternately（交替合并字符串）
 * @Date 2022/10/23 8:16 PM
 * @Version 1.0
 */
public class Problem1768MergeStringsAlternately {

    public String mergeAlternately(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        int n = n1 + n2;
        char[] cs = new char[n];
        for (int i = 0, idx1 = 0, idx2 = 0; i < n;) {
            if (idx1 < n1) {
                cs[i++] = word1.charAt(idx1++);
            }
            if (idx2 < n2) {
                cs[i++] = word2.charAt(idx2++);
            }
        }
        return new String(cs);
    }
}
