package com.shuangpeng.Problem.p1601_1700;

/**
 * @Description: Problem1662CheckIfTwoStringArraysAreEquivalent（检查两个字符串数组是否相等）
 * @Date 2022/11/1 10:23 AM
 * @Version 1.0
 */
public class Problem1662CheckIfTwoStringArraysAreEquivalent {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int n1 = word1.length, n2 = word2.length;
        int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
        while (i1 < n1 && i2 < n2) {
            if (word1[i1].charAt(j1) != word2[i2].charAt(j2)) {
                return false;
            }
            j1++;
            j2++;
            if (j1 == word1[i1].length()) {
                i1++;
                j1 = 0;
            }
            if (j2 == word2[i2].length()) {
                i2++;
                j2 = 0;
            }
        }
        return i1 == n1 && j1 == 0 && i2 == n2 && j2 == 0;
    }
}
