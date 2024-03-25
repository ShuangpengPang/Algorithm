package com.shuangpeng.Problem.P2201_2300;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2215FindTheDifferenceOfTwoArrays（找出两数组的不同）
 * @date 2024/3/25 5:39 PM
 */
public class Problem2215FindTheDifferenceOfTwoArrays {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            if (set2.add(num) && !set1.contains(num)) {
                list2.add(num);
            }
        }
        for (int num : set1) {
            if (!set2.contains(num)) {
                list1.add(num);
            }
        }
        return new ArrayList<List<Integer>>() {{
            add(list1);
            add(list2);
        }};
    }
}
