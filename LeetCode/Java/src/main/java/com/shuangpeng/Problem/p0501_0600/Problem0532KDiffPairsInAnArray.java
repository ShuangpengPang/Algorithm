package com.shuangpeng.Problem.p0501_0600;

import java.util.*;

/**
 * @Description: Problem0532KDiffPairsInAnArray（数组中的k-diff数对）
 * @Date 2022/6/16 11:37 AM
 * @Version 1.0
 */
public class Problem0532KDiffPairsInAnArray {

    public int findPairs0(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (set.contains(num - k)) {
                map.put(num - k, num);
            }
            if (set.contains(num + k)) {
                map.put(num, num + k);
            }
            set.add(num);
        }
        return map.size();
    }

    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        if (k == 0) {
            for (int i = 0, j = 0; i < n; i = j) {
                while (j < n && nums[j] == nums[i]) {
                    ++j;
                }
                if (j - i > 1) {
                    ++ans;
                }
            }
            return ans;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                if (set.contains(nums[i] - k)) {
                    ++ans;
                }
                set.add(nums[i]);
            }
        }
        return ans;
    }
}
