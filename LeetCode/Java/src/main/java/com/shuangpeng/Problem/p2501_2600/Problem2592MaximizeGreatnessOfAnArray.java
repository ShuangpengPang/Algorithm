package com.shuangpeng.Problem.p2501_2600;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2592MaximizeGreatnessOfAnArray（最大化数组的伟大值）
 * @date 2023/12/5 11:05 PM
 */
public class Problem2592MaximizeGreatnessOfAnArray {

    public int maximizeGreatness(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        for (int num : nums) {
            maxCount = Math.max(maxCount, map.merge(num, 1, Integer::sum));
        }
        return nums.length - maxCount;
    }
}
