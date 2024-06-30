package com.shuangpeng.lcr.p001_100;

import java.util.Random;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR076FindKthLargest（数组中的第 K 个最大元素）
 * @date 2024/6/30 6:15 PM
 */
public class LCR076FindKthLargest {

    private static final Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while (left < k) {
            int idx = left + random.nextInt(right - left + 1);
            int pivot = nums[idx];
            swap(nums, idx, right);
            int p = left;
            for (int i = left; i < right; i++) {
                if (nums[i] > pivot) {
                    swap(nums, p++, i);
                }
            }
            swap(nums, p, right);
            if (p < k) {
                left = p + 1;
            } else {
                right = p - 1;
            }
        }
        return nums[k - 1];
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }
}
