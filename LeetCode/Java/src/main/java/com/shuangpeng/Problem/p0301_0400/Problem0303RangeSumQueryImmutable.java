package com.shuangpeng.Problem.p0301_0400;

public class Problem0303RangeSumQueryImmutable {

    class NumArray {

        private int[] sum;

        public NumArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
}
