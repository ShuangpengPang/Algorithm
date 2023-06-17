package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2481MinimumCutsToDivideACircle（分割圆的最少切割次数）
 * @date 2023/6/17 2:03 PM
 */
public class Problem2481MinimumCutsToDivideACircle {

    public int numberOfCuts(int n) {
        return n > 1 && (n & 1) == 1 ? n : n >> 1;
    }
}
