package com.shuangpeng.offer;

public class Offer42 {

    public int maxSubArray0(int[] nums) {
        int answer = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }

    public int maxSubArray1(int[] nums) {
        int maxSum = nums[0], sum = 0;
        for (int num : nums) {
            sum = Math.max(sum + num, num);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public int maxSubArray2(int[] nums) {
        return divideAndConquer0(nums, 0, nums.length - 1);
    }

    private int divideAndConquer0(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int mid = start + ((end - start) >> 1);
        int maxLeft = divideAndConquer0(nums, start, mid);
        int maxRight = divideAndConquer0(nums, mid + 1, end);
        int maxMid = nums[mid];
        int sum1 = 0;
        for (int i = mid; i >= start; i--) {
            sum1 += nums[i];
            maxMid = Math.max(maxMid, sum1);
        }
        int sum2 = 0;
        int origin = maxMid;
        for (int i = mid + 1; i <= end; i++) {
            sum2 += nums[i];
            maxMid = Math.max(maxMid, origin + sum2);
        }
        return Math.max(Math.max(maxLeft, maxRight), maxMid);
    }

    public int maxSubArray(int[] nums) {
        return divideAndConquer(nums, 0, nums.length - 1)[2];
    }

    private int[] divideAndConquer(int[] nums, int start, int end) {
        if (start == end) {
            int num = nums[start];
            return new int[]{num, num, num, num};
        }
        int mid = start + ((end - start) >> 1);
        int[] left = divideAndConquer(nums, start, mid);
        int[] right = divideAndConquer(nums, mid + 1, end);
        int lSum = Math.max(left[0], left[3] + right[0]);
        int rSum = Math.max(right[1], right[3] + left[1]);
        int iSum = Math.max(Math.max(left[2], right[2]), left[1] + right[0]);
        int sum = left[3] + right[3];
        return new int[]{lSum, rSum, iSum, sum};
    }
}
