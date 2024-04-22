package com.shuangpeng.Problem.p3001_3100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3069DistributeElementsIntoTwoArraysI（将元素分配到两个数组中I）
 * @date 2024/4/22 8:18 PM
 */
public class Problem3069DistributeElementsIntoTwoArraysI {

    public int[] resultArray(int[] nums) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        int n = nums.length;
        list1.add(nums[0]);
        list2.add(nums[1]);
        for (int i = 2; i < n; i++) {
            if (list1.get(list1.size() - 1) > list2.get(list2.size() - 1)) {
                list1.add(nums[i]);
            } else {
                list2.add(nums[i]);
            }
        }
        int[] ans = new int[n];
        int j = 0;
        for (int i = 0; i < list1.size(); i++) {
            ans[j++] = list1.get(i);
        }
        for (int i = 0; i < list2.size(); i++) {
            ans[j++] = list2.get(i);
        }
        return ans;
    }
}
