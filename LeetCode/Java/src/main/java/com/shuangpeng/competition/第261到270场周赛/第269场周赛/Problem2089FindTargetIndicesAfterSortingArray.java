package com.shuangpeng.competition.第261到270场周赛.第269场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem2089FindTargetIndicesAfterSortingArray {

    public List<Integer> targetIndices0(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    public List<Integer> targetIndices1(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n && nums[i] <= target; ++i) {
            if (nums[i] == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    public List<Integer> targetIndices(int[] nums, int target) {
        int cnt1 = 0, cnt2 = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < target) {
                ++cnt1;
            } else if (nums[i] == target) {
                ++cnt2;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = cnt1; i < cnt1 + cnt2; ++i) {
            ans.add(i);
        }
        return ans;
    }
}
