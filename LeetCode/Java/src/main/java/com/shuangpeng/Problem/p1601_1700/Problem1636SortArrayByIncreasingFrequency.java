package com.shuangpeng.Problem.p1601_1700;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Problem1636SortArrayByIncreasingFrequency（按照频率将数组升序排序）
 * @Date 2022/9/19 10:14 AM
 * @Version 1.0
 */
public class Problem1636SortArrayByIncreasingFrequency {

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<int[]> list = new ArrayList<>(map.size());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new int[]{entry.getKey(), entry.getValue()});
        }
        list.sort((a, b) -> a[1] != b[1] ? a[1] - b[1] : b[0] - a[0]);
        int[] ans = new int[nums.length];
        int idx = 0;
        for (int[] arr : list) {
            for (int i = 0; i < arr[1]; i++) {
                ans[idx++] = arr[0];
            }
        }
        return ans;
    }
}
