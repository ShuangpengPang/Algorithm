package com.shuangpeng.Problem.P2201_2300;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2248IntersectionOfMultipleArrays（多个数组求交集）
 * @date 2024/3/28 11:37 AM
 */
public class Problem2248IntersectionOfMultipleArrays {

    public List<Integer> intersection(int[][] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for (int[] arr : nums) {
            for (int num : arr) {
                if (map.merge(num, 1, Integer::sum) == nums.length) {
                    ans.add(num);
                }
            }
        }
        ans.sort(Comparator.comparingInt(e -> e));
        return ans;
    }
}
