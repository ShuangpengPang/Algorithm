package com.shuangpeng.Problem.p2701_2800;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2708MaximumStrengthOfAGroup（一个小组的最大实力值）
 * @date 2023/12/11 7:08 PM
 */
public class Problem2708MaximumStrengthOfAGroup {

    public long maxStrength(int[] nums) {
        if (nums.length == 1 && nums[0] < 0) {
            return nums[0];
        }
        long positive = Long.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (num > 0) {
                positive = Math.max(1, positive) * num;
            } else if (num < 0) {
                list.add(num);
            }
        }
        list.sort(Comparator.comparingInt(a -> a));
        int n = (list.size() & 1) == 1 ? list.size() - 1 : list.size();
        long negative = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            negative = Math.max(1, negative) * Math.abs(list.get(i));
        }
        if (positive < 0 && negative < 0) {
            return 0;
        }
        return Math.max(positive, 1) * Math.max(negative, 1);
    }
}
