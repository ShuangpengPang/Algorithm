package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0324WiggleSortII {

    // 0 1 2, 3, 4

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        Arrays.sort(nums);
        int length = nums.length;
        int mid = (length - 1) / 2;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            if ((i & 1) == 0) {
                temp[i] = nums[i / 2];
            } else {
                temp[i] = nums[mid + 1 + i / 2];
            }
        }
        for (int i = 0; i < length; i++) {
            nums[i] = temp[i];
        }
    }
}
