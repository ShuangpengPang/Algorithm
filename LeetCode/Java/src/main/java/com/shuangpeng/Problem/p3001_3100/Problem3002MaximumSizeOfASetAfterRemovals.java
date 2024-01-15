package com.shuangpeng.Problem.p3001_3100;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3002MaximumSizeOfASetAfterRemovals（移除后集合的最多元素数）
 * @date 2024/1/15 3:15 PM
 */
public class Problem3002MaximumSizeOfASetAfterRemovals {

    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>(), set = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        for (int num : set1) {
            if (set2.contains(num)) {
                set.add(num);
            }
        }
        int n = nums1.length >> 1, size = set.size();
        int n1 = Math.min(n, set1.size() - size), n2 = Math.min(n, set2.size() - size);
        int s1 = Math.min(set.size(), n - n1);
        int s2 = Math.min(set.size(), n - n2);
        return n1 + n2 + Math.min(size, s1 + s2);
    }
}
