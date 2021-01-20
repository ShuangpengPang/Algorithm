package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0324WiggleSortII {

    // 0 1 2, 3, 4

    public void wiggleSort0(int[] nums) {
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

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int length = nums.length;
        int mid = (length - 1) / 2;
        quickSelect(nums, mid, 0, length);
        int[] left = new int[mid + 1];
        int[] right = new int[length - mid - 1];
        for (int i = 0; i < length; i++) {
            if (i <= mid) {
                left[i] = nums[i];
            } else {
                right[i - mid - 1] = nums[i];
            }
        }
        int middle = (left.length - 1) / 2;
        for (int i = 0; i <= middle; i++) {
            int temp = left[i];
            left[i] = left[left.length - i - 1];
            left[left.length - i - 1] = temp;
        }
        middle = (right.length - 1) / 2;
        for (int i = 0; i <= middle; i++) {
            int temp = right[i];
            right[i] = right[right.length - i - 1];
            right[right.length - i - 1] = temp;
        }
        int pLeft = 0;
        int pRight = 0;
        for (int i = 0; i < length; i++) {
            if (pLeft == pRight) {
                nums[i] = left[pLeft++];
            } else {
                nums[i] = right[pRight++];
            }
        }
    }

    private int quickSelect(int[] nums, int k, int start, int end) {
        int rand = start + (int) (Math.random() * (end - start));
        int num = nums[rand];
        int i = start, j = start, m = end - 1;
        while (j <= m) {
            if (nums[j] > num) {
                int temp = nums[j];
                nums[j] = nums[m];
                nums[m] = temp;
                m--;
            } else if (nums[j] < num) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
                j++;
            } else {
                j++;
            }
        }
        if (i <= k && k < j) {
            return num;
        }
        if (k >= j) {
            return quickSelect(nums, k, j, end);
        }
        return quickSelect(nums, k, start, i);
    }
}
