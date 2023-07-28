package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
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

    public boolean isPossibleDivide1(int[] nums, int k) {
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

    public boolean isPossibleDivide2(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>(n);
        for (int num : nums) {
            q.offer(num);
        }
        while (!q.isEmpty()) {
            int num = q.peek(), max = num + k;
            for (int i = num; i < max; i++) {
                if (!q.remove(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPossibleDivide3(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        for (int i = 0; i < n; i++) {
            if (map.get(nums[i]) > 0) {
                for (int num = nums[i]; num < nums[i] + k; num++) {
                    if (map.merge(num, -1, Integer::sum) < 0) {
                        return false;
                    }
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
        int[] pos = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (nums[i] != -1) {
                pos[1] = Math.max(pos[1], i + 1);
                for (int j = 1; j < k; j++) {
                    int target = nums[i] + j, p = pos[j];
                    while (p < n && nums[p] != target) {
                        if (nums[p] > target) {
                            return false;
                        }
                        p++;
                    }
                    if (p == n) {
                        return false;
                    }
                    nums[p] = -1;
                    pos[j] = p + 1;
                    pos[j + 1] = Math.max(pos[j + 1], pos[j]);
                }
            }
        }
        return true;
    }














}
