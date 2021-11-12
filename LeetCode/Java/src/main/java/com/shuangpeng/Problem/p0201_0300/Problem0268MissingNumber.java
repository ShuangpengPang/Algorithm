package com.shuangpeng.Problem.p0201_0300;

public class Problem0268MissingNumber {

    public int missingNumber0(int[] nums) {
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

    public int missingNumber1(int[] nums) {
        int n = nums.length;
        int sum = (n + 1) * n >> 1;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] != i && nums[i] != n) {
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }
}
