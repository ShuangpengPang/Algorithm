package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0912SortAnArray（排序数组）
 * @date 2023/3/10 11:07 AM
 */
public class Problem0912SortAnArray {

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int s, int e) {
        if (s >= e) {
            return;
        }
        int p = s, num = nums[e];
        for (int i = s; i < e; i++) {
            if (nums[i] <= num) {
                swap(nums, p, i);
                p++;
            }
        }
        swap(nums, p, e);
        quickSort(nums, s, p - 1);
        quickSort(nums, p + 1, e);
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }
}

class Problem0912SortAnArray0 {

    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int s, int e) {
        if (s >= e) {
            return;
        }
        int m = s + e >> 1;
        mergeSort(nums, s, m);
        mergeSort(nums, m + 1, e);
        int n = e - s + 1;
        int[] tmp = new int[n];
        int i = 0, i1 = s, i2 = m + 1;
        while (i1 <= m && i2 <= e) {
            if (nums[i1] <= nums[i2]) {
                tmp[i++] = nums[i1++];
            } else {
                tmp[i++] = nums[i2++];
            }
        }
        while (i1 <= m) {
            tmp[i++] = nums[i1++];
        }
        while (i2 <= e) {
            tmp[i++] = nums[i2++];
        }
        System.arraycopy(tmp, 0, nums, s, n);
    }
}
