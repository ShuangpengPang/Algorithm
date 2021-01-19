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
        int[] left = new int[mid + 1];
        int[] right = new int[length - mid - 1];
        for (int i = 0; i < length; i++) {
            if (i <= mid) {
                left[i] = nums[i];
            } else {
                right[i - mid - 1] = nums[i];
            }
        }
        int m = mid / 2;
        for (int i = 0; i <= m; i++) {
            int temp = left[i];
            left[i] = left[mid - i];
            left[mid - i] = temp;
        }
        m = (length - mid) / 2 - 1;
        for (int i = 0; i <= m; i++) {
            int temp = right[i];
            right[i] = right[length - mid - i - 2];
            right[length - mid - i - 2] = temp;
        }
        int j = 0;
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (j == k) {
                nums[i] = left[j++];
            } else {
                nums[i] = right[k++];
            }
        }
    }
}
