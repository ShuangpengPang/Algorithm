package com.shuangpeng.Problem.p1601_1700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1663SmallestStringWithAGivenNumericValue（具有给定数值的最小字符串）
 * @date 2023/1/31 11:49 AM
 */
public class Problem1663SmallestStringWithAGivenNumericValue {

    public String getSmallestString(int n, int k) {
        char[] cs = new char[n];
        for (int i = n - 1; i >= 0; i--) {
            int d = Math.min(k - i, 26);
            k -= d;
            cs[i] = (char) ('a' + d - 1);
        }
        return new String(cs);
    }
}
