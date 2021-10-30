package com.shuangpeng.Problem.p0101_0200;

public class Problem0152MaximumProductSubarray {

//    public static void main(String[] args) {
//        Problem0152MaximumProductSubarray a = new Problem0152MaximumProductSubarray();
//        int[] nums = {2,3,-2,4};
//        int i = a.maxProduct(nums);
//    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int dmin = nums[0];
        int dmax = nums[0];
        int max = dmax;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                dmin = Math.min(1, dmin) * nums[i];
                dmax = Math.max(1, dmax) * nums[i];
            } else {
                int min = Math.max(1, dmax) * nums[i];
                dmax = Math.min(1, dmin) * nums[i];
                dmin = min;
            }
            max = Math.max(max, dmax);
        }
        return max;
    }
}
