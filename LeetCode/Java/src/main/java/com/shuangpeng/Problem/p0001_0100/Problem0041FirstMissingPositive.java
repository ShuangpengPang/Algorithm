package com.shuangpeng.Problem.p0001_0100;

public class Problem0041FirstMissingPositive {

    public int firstMissingPositive0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int length = nums.length;
        int i = 0;
        while (i < length) {
            int data = nums[i];
            if (data > 0 && data <= length && data != i + 1 && nums[data - 1] != data) {
                nums[i] = nums[data - 1];
                nums[data - 1] = data;
            } else {
                i++;
            }
        }
        for (i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return length + 1;
    }

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] <= 0) {
                nums[i] = length + 1;
            }
        }
        for (int i = 0; i < length; i++) {
            int data = nums[i];
            data = data < 0 ? -data : data;
            if (data >= 1 && data <= length) {
                nums[data - 1] = nums[data - 1] > 0 ? -nums[data - 1] : nums[data - 1];
            }
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return length + 1;
    }
}
