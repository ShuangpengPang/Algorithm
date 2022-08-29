package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1470ShuffleTheArray（重新排列数组）
 * @Date 2022/8/29 9:56 AM
 * @Version 1.0
 */
public class Problem1470ShuffleTheArray {

    public int[] shuffle0(int[] nums, int n) {
        int[] copy = nums.clone();
        for (int i = 0; i < n; i++) {
            nums[i << 1] = copy[i];
            nums[(i << 1) + 1] = copy[i + n];
        }
        return nums;
    }

    public int[] shuffle1(int[] nums, int n) {
        for (int i = 0; i < (n << 1); i++) {
            if (i < n) {
                nums[i << 1] |= (nums[i] & 1023) << 10;
            } else {
                nums[((i - n) << 1) + 1] |= (nums[i] & 1023) << 10;
            }
        }
        for (int i = 0; i < (n << 1); i++) {
            nums[i] >>= 10;
        }
        return nums;
    }

    public int[] shuffle(int[] nums, int n) {
        for (int i = 0; i < (n << 1); i++) {
            int num = nums[i];
            int j = i < n ? i << 1 : ((i - n) << 1) + 1;
            while (nums[j] > 0) {
                int tmp = nums[j];
                nums[j] = -num;
                num = tmp;
                j = j < n ? j << 1 : ((j - n) << 1) + 1;
            }
        }
        for (int i = 0; i < (n << 1); i++) {
            nums[i] = -nums[i];
        }
        return nums;
    }
}
