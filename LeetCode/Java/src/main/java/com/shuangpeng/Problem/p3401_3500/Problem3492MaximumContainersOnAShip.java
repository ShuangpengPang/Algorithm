package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3492MaximumContainersOnAShip（船上可以装载的最大集装箱数量）
 * @date 2025/4/7 00:22
 */
public class Problem3492MaximumContainersOnAShip {

    public int maxContainers(int n, int w, int maxWeight) {
        return Math.min(maxWeight / w, n * n);
    }
}
