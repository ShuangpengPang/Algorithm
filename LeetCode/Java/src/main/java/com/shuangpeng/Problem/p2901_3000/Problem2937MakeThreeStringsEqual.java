package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2937MakeThreeStringsEqual（使三个字符串相等）
 * @date 2024/4/13 10:58 PM
 */
public class Problem2937MakeThreeStringsEqual {

    public int findMinimumOperations(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        int i = 0, n = Math.min(n1, Math.min(n2, n3));
        while (i < n && s1.charAt(i) == s2.charAt(i) && s2.charAt(i) == s3.charAt(i)) {
            i++;
        }
        return i == 0 ? -1 : n1 + n2 + n3 - 3 * i;
    }
}
