package com.shuangpeng;

public class Problem0283MoveZeroes {

    public void moveZeroes(int[] nums) {
//        0,1,0,3,12
        if (nums == null || nums.length <= 1) {
            return;
        }
        int j = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            } else {
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            nums[nums.length - 1 - i] = 0;
        }
    }
}
