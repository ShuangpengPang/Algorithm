package com.shuangpeng.Problem.p0301_0400;

import java.util.Arrays;
import java.util.Random;

public class Problem0384ShuffleAnArray {

    class Solution {

        private Random random = new Random();
        private int[] nums;
        private int[] copy;

        public Solution(int[] nums) {
            this.nums = nums;
            copy = Arrays.copyOf(nums, nums.length);
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return nums;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            for (int i = 0; i < copy.length; i++) {
                int j = i + random.nextInt(copy.length - i);
                int temp = copy[i];
                copy[i] = copy[j];
                copy[j] = temp;
            }
            return copy;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
}
