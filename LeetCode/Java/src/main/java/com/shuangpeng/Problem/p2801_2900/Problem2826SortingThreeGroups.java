package com.shuangpeng.Problem.p2801_2900;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2826SortingThreeGroups（将三个组排序）
 * @date 2023/8/21 6:05 PM
 */
public class Problem2826SortingThreeGroups {

    public int minimumOperations(List<Integer> nums) {
        int n = nums.size();
        List<Integer> list = new ArrayList<>(n);
        for (int num : nums) {
            int left = 0, right = list.size() - 1;
            while (left <= right) {
                int mid = left + (right - left >> 1);
                if (list.get(mid) <= num) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (left == list.size()) {
                list.add(num);
            } else {
                list.set(left, num);
            }
        }
        return n - list.size();
    }
}
