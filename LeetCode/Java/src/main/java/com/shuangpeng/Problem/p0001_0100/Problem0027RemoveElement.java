package com.shuangpeng.Problem.p0001_0100;

public class Problem0027RemoveElement {

    public int removeElement0(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    public int removeElement(int[] nums, int val) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (nums[i] == val) {
                nums[i] = nums[j];
                j--;
            } else {
                i++;
            }
        }
        return i;
    }
}
