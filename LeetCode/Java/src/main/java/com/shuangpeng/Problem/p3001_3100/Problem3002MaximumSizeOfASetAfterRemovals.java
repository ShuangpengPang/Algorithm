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

    public int maximumSetSize0(int[] nums1, int[] nums2) {
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

    public int maximumSetSize1(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        int common = 0;
        for (int num : set1) {
            if (set2.contains(num)) {
                common++;
            }
        }
        int n = nums1.length, h = n >> 1, s1 = set1.size(), s2 = set2.size();
        int c1 = Math.min(h, s1 - common), c2 = Math.min(h, s2 - common);
        return c1 + c2 + Math.min(common, n - c1 - c2);
    }

    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        int all = 0;
        for (int num : nums1) {
            if (set1.add(num)) {
                all++;
            }
        }
        for (int num : nums2) {
            if (set2.add(num) && !set1.contains(num)) {
                all++;
            }
        }
        int h = nums1.length >> 1;
        return Math.min(all, Math.min(h, set1.size()) + Math.min(h, set2.size()));
    }
}
