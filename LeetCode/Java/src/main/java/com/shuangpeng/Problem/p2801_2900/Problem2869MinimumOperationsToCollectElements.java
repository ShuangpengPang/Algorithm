package com.shuangpeng.Problem.p2801_2900;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2869MinimumOperationsToCollectElements（收集元素的最少操作次数）
 * @date 2024/4/10 5:03 PM
 */
public class Problem2869MinimumOperationsToCollectElements {

    public int minOperations(List<Integer> nums, int k) {
        int n = nums.size(), minIndex = n;
        boolean[] set = new boolean[k + 1];
        for (int i = n - 1; i >= 0; i--) {
            int num = nums.get(i);
            if (num <= k && !set[num]) {
                minIndex = i;
                set[num] = true;
            }
        }
        return n - minIndex;
    }
}
