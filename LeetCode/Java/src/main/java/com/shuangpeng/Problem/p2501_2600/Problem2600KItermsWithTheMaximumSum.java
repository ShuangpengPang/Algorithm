package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2600KItermsWithTheMaximumSum（K件物品的最大和）
 * @date 2023/7/5 10:23 AM
 */
public class Problem2600KItermsWithTheMaximumSum {

    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int cnt1 = Math.min(numOnes, k);
        k -= cnt1;
        k -= Math.min(numZeros, k);
        return cnt1 - k;
    }
}
