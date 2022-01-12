package com.shuangpeng.Problem.p0301_0400;

public class Problem0334IncreasingTripletSubsequence {

    public boolean increasingTriplet0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= min) {
                min = nums[i];
            } else if (nums[i] <= mid) {
                mid = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean increasingTriplet1(int[] nums) {
        Integer first = null, second = null;
        for (int num : nums) {
            if (first == null) {
                first = num;
            } else if (num <= first) {
                first = num;
            } else if (second == null) {
                second = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            minValue = Math.min(minValue, nums[i]);
            left[i] = minValue;
        }
        int maxValue = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; --i) {
            maxValue = Math.max(maxValue, nums[i]);
            if (left[i] < nums[i] && nums[i] < maxValue) {
                return true;
            }
        }
        return false;
    }
}
