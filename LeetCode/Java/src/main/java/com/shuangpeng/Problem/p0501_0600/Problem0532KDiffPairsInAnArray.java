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

    public int findPairs1(int[] nums, int k) {
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

    public int findPairs2(int[] nums, int k) {
        Set<Integer> visited = new HashSet<Integer>();
        Set<Integer> res = new HashSet<Integer>();
        for (int num : nums) {
            if (visited.contains(num - k)) {
                res.add(num - k);
            }
            if (visited.contains(num + k)) {
                res.add(num);
            }
            visited.add(num);
        }
        return res.size();
    }

    public int findPairs3(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, y = 0, res = 0;
        for (int x = 0; x < n; x++) {
            if (x == 0 || nums[x] != nums[x - 1]) {
                while (y < n && (nums[y] < nums[x] + k || y <= x)) {
                    y++;
                }
                if (y < n && nums[y] == nums[x] + k) {
                    res++;
                }
            }
        }
        return res;
    }

    public int findPairs4(int[] nums, int k) {
        Set<Integer> set = new HashSet<>(), ans = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num - k)) {
                ans.add(num - k);
            }
            if (set.contains(num + k)) {
                ans.add(num);
            }
            set.add(num);
        }
        return ans.size();
    }

    public int findPairs5(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0, last = Integer.MIN_VALUE;
        for (int i = 0, j = 0; j < n; ++j) {
            while (nums[j] - nums[i] > k) {
                ++i;
            }
            if (i != j && nums[j] - nums[i] == k && nums[i] != last) {
                last = nums[i];
                ++ans;
            }
        }
        return ans;
    }

    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0, j = 0; j < n && i < n; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                while (j <= i || (j < n && nums[j] < nums[i] + k)) {
                    ++j;
                }
                if (j < n && nums[j] == nums[i] + k) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
