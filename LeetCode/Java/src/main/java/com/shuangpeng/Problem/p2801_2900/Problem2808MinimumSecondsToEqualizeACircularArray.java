package com.shuangpeng.Problem.p2801_2900;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2808MinimumSecondsToEqualizeACircularArray（使循环数组所有元素相等的最少秒数）
 * @date 2023/8/12 12:09 PM
 */
public class Problem2808MinimumSecondsToEqualizeACircularArray {

    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.size(), ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        }
        for (List<Integer> list : map.values()) {
            int size = list.size(), max = n + list.get(0) - list.get(size - 1);
            for (int i = 1; i < size; i++) {
                max = Math.max(max, list.get(i) - list.get(i - 1));
            }
            ans = Math.min(ans, max >> 1);
        }
        return ans;
    }
}
