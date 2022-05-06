package com.shuangpeng.Problem.p0901_1000;

import java.util.*;

/**
 * @Description: Problem0992SubarraysWithKDifferentIntegers
 * @Date 2022/5/6 4:54 PM
 * @Version 1.0
 */
public class Problem0992SubarraysWithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        int[] indices = new int[n + 1];
        Set<Integer> set = new HashSet<>(k);
        int ans = 0;
        for (int i = 0, left = 0, right = 0; i < n; ++i) {
            int num = nums[i];
            indices[num] = i;
            if (set.size() == k && !set.contains(num)) {
                set.remove(nums[right]);
                left = ++right;
            }
            while (right != indices[nums[right]]) {
                ++right;
            }
            set.add(num);
            if (set.size() == k) {
                ans += right - left + 1;
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0992SubarraysWithKDifferentIntegers a = new Problem0992SubarraysWithKDifferentIntegers();
//        a.subarraysWithKDistinct(new int[]{1, 2}, 1);
//    }
}
