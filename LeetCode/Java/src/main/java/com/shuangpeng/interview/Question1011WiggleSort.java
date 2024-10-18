package com.shuangpeng.interview;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question1011WiggleSort（面试题 10.11. 峰与谷）
 * @date 2024/10/18 10:22 AM
 */
public class Question1011WiggleSort {

    public void wiggleSort0(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i += 2) {
            if (i + 1 < n) {
                nums[i] = nums[i] ^ nums[i + 1];
                nums[i + 1] = nums[i] ^ nums[i + 1];
                nums[i] = nums[i] ^ nums[i + 1];
            }
        }
    }

    public void wiggleSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if ((i & 1) == 0 && nums[i] < nums[i + 1] || (i & 1) == 1 && nums[i] > nums[i + 1]) {
                nums[i] = nums[i] ^ nums[i + 1];
                nums[i + 1] = nums[i] ^ nums[i + 1];
                nums[i] = nums[i] ^ nums[i + 1];
            }
        }
    }
}
