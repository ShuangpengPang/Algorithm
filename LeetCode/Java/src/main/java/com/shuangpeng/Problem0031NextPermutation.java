package com.shuangpeng;

public class Problem0031NextPermutation {

    public void nextPermutation(int[] nums) {
        // 10, 1, 3, 4, 3, 8, 4, 3, 2, 1
        // 4, 8, 3, 3, 2, 1
        // 4, 1, 2, 3, 3, 8

        int length = nums.length;
        if (length <= 1) {
            return;
        }

        int i = length - 1;
        while (i > 0) {
            if (nums[i - 1] < nums[i]) {
                break;
            }
            i--;
        }
        if (i > 0) {
            int data = nums[i - 1];
            int j = i;
            while (j < length) {
                if (nums[j] <= data) {
                    break;
                }
                j++;
            }
            // nums[j - 1] > data
            swap(nums, i - 1, j - 1);
        }
        sort(nums, i);
    }

    public void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[j] ^ nums[i];
            nums[j] = nums[j] ^ nums[i];
            nums[i] = nums[j] ^ nums[i];
        }
    }

    public void sort(int[] nums, int i) {
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
