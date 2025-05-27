package com.shuangpeng.Problem.p2701_2800;


/**
 * @program: Algorithm
 * @description: Problem2749MinimumOperationsToMakeTheIntegerZero（得到整数零需要执行的最少操作数）
 * @author: ShuangPengPang
 * @create: 2025-05-24 00:19
 */
public class Problem2749MinimumOperationsToMakeTheIntegerZero {

    public int makeTheIntegerZero(int num1, int num2) {
        int cnt = 0;
        long x = num1, y = num2;
        while (cnt <= x && Long.bitCount(x) > cnt) {
            x -= y;
            cnt++;
        }
        return cnt > x ? -1 : cnt;
    }
}
