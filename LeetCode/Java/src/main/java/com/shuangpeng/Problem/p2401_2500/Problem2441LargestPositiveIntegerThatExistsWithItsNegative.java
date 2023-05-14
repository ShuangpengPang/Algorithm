package com.shuangpeng.Problem.p2401_2500;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2441LargestPositiveIntegerThatExistsWithItsNegative（与对应负数同时存在的最大正整数）
 * @date 2023/5/14 8:18 PM
 */
public class Problem2441LargestPositiveIntegerThatExistsWithItsNegative {

    public int findMaxK(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ans = -1;
        for (int num : nums) {
            if (set.contains(-num)) {
                ans = Math.max(ans, Math.abs(num));
            }
            set.add(num);
        }
        return ans;
    }
}
