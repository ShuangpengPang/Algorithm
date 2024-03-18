package com.shuangpeng.Problem.p1801_1900;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1863SumOfAllSubsetXORTotals（找出所有子集的异或总和再求和）
 * @date 2024/3/18 3:24 PM
 */
public class Problem1863SumOfAllSubsetXORTotals {

    public int subsetXORSum(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        int sum = 0;
        for (int num : nums) {
            for (int i = list.size() - 1; i >= 0; i--) {
                int xor = num ^ list.get(i);
                sum += xor;
                list.add(xor);
            }
        }
        return sum;
    }
}
