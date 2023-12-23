package com.shuangpeng.Problem.p0801_0900;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0888FairCandySwap（公平的糖果交换）
 * @date 2023/12/23 7:07 PM
 */
public class Problem0888FairCandySwap {

    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumA = 0, sumB = 0;
        for (int a : aliceSizes) {
            sumA += a;
        }
        Set<Integer> set = new HashSet<>();
        for (int b : bobSizes) {
            set.add(b);
            sumB += b;
        }
        int d = sumA - sumB >> 1;
        for (int a : aliceSizes) {
            if (set.contains(a - d)) {
                return new int[]{a, a - d};
            }
        }
        return new int[2];
    }
}
