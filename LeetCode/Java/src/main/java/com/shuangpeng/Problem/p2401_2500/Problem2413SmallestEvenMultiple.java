package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2413SmallestEvenMultiple（最小偶倍数）
 * @date 2023/4/21 3:59 PM
 */
public class Problem2413SmallestEvenMultiple {

    public int smallestEvenMultiple0(int n) {
        return (n & 1) == 0 ? n : n << 1;
    }

    public int smallestEvenMultiple(int n) {
        return n << (n & 1);
    }
}
