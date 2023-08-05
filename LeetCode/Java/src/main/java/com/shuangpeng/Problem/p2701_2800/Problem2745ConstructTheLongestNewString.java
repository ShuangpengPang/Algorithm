package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2745ConstructTheLongestNewString（构造最长的新字符串）
 * @date 2023/8/5 7:44 PM
 */
public class Problem2745ConstructTheLongestNewString {

    public int longestString(int x, int y, int z) {
        int a = Math.min(x, y), b = x + y - a;
        return ((a << 1) + Math.min(1, b - a) + z) << 1;
    }
}
