package com.shuangpeng.Problem;

public class Problem0268MissingNumber {

    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] < n && nums[i] != i) {
                int j = nums[i];
                nums[i] = nums[j];
                nums[j] = j;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }
}
