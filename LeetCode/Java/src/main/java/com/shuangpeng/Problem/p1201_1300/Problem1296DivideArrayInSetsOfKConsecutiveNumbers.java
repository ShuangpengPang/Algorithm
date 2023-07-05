package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1296DivideArrayInSetsOfKConsecutiveNumbers（划分数组为连续数字的集合）
 * @date 2023/7/5 4:01 PM
 */
public class Problem1296DivideArrayInSetsOfKConsecutiveNumbers {

    public boolean isPossibleDivide0(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        int m = n / k;
        for (int i = 0; i < m; i++) {
            int first = map.firstKey();
            for (int j = 0, key = first; j < k; j++, key++) {
                int count = map.getOrDefault(key, 0) - 1;
                if (count < 0) {
                    return false;
                }
                if (count == 0) {
                    map.remove(key);
                } else {
                    map.put(key, count);
                }
            }
        }
        return true;
    }

    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                for (int j = nums[i]; j < nums[i] + k; j++) {
                    int count = map.getOrDefault(j, 0) - 1;
                    if (count < 0) {
                        return false;
                    } else if (count == 0) {
                        map.remove(j);
                    } else {
                        map.put(j, count);
                    }
                }
            }
        }
        return true;
    }
}
