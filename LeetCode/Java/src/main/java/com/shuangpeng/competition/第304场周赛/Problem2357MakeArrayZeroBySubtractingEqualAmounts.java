package com.shuangpeng.competition.第304场周赛;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem2357MakeArrayZeroBySubtractingEqualAmounts（使数组中所有元素都等于零）
 * @Date 2022/8/6 5:09 PM
 * @Version 1.0
 */
public class Problem2357MakeArrayZeroBySubtractingEqualAmounts {

    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        return set.size();
    }
}
