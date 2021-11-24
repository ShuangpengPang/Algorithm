package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem0018FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, 4, 0, nums.length, target);
    }

    private List<List<Integer>> kSum(int[] nums, int k, int s, int e, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (e - s < k) {
            return ans;
        }
        if (k == 2) {
            int i = s, j = e - 1;
            while (i < j && (nums[i] < 0 || nums[i] <= target)) {
                if (i > s && nums[i] == nums[i - 1]) {
                    ++i;
                } else if (j < e - 1 && nums[j] == nums[j + 1]) {
                    --j;
                } else if (nums[i] + nums[j] < target) {
                    ++i;
                } else if (nums[i] + nums[j] > target) {
                    --j;
                } else if (nums[i] + nums[j] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    ans.add(list);
                    ++i;
                }
            }
        } else {
            for (int i = s; i <= e - k && (nums[i] < 0 || nums[i] <= target); ++i) {
                if (i == s || nums[i] != nums[i - 1]) {
                    for (List<Integer> list : kSum(nums, k - 1, i + 1, e, target - nums[i])) {
                        list.add(nums[i]);
                        ans.add(list);
                    }
                }
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0018FourSum a = new Problem0018FourSum();
//        int[] nums = {-1, 0, -5, -2, -2, -4, 0, 1, -2};
//        a.fourSum(nums, -9);
//    }
}
