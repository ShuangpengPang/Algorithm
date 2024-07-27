package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3226NumberOfBitChangesToMakeTwoIntegersEqual（使两个整数相等的位更改次数）
 * @date 2024/7/28 12:21 AM
 */
public class Problem3226NumberOfBitChangesToMakeTwoIntegersEqual {

    public int minChanges(int n, int k) {
        return n == (n | k) ? Integer.bitCount(n ^ k) : -1;
    }
}
