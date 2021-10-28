package com.shuangpeng.Problem.p0001_0100;

public class Problem0053MaxSubArray {

    // 动态规划
    public int maxSubArray0(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
//        int start = 0;
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum <= 0) {
//                start = i;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

//    public int maxSubArray(int[] nums) {
//        if (nums.length == 1) {
//            return nums[0];
//        }
//        return partition0(nums, 0, nums.length - 1)[0];
//    }

    // 分治
//    public int[] partition0(int[] nums, int left, int right) {
////        if (left > right) {
////            return 0;
////        }
//        if (left == right) {
//            return new int[]{nums[left], left, left};
//        }
//        int mid = left + (right - left) / 2;
//        int[] resultLeft = partition0(nums, left, mid);
//        int[] resultRight = partition0(nums, mid + 1, right);
//        int sumLeft = resultLeft[0];
//        int sumRight = resultRight[0];
//        int max = Math.max(Math.max(sumLeft, sumRight), sumLeft + sumRight);
//        if (resultLeft[2] == mid && resultRight[1] == mid + 1 && max == sumLeft + sumRight) {
//            return new int[]{max, resultLeft[1], resultRight[2]};
//        } else {
//            int sum = 0;
//            int leftIndex = mid;
//            int maxLeft = nums[leftIndex];
//            for (int i = mid; i >= left; i--) {
//                sum += nums[i];
//                if (sum >= maxLeft) {
//                    maxLeft = sum;
//                    leftIndex = i;
//                }
//            }
//            sum = 0;
//            int rightIndex = mid + 1;
//            int maxRight = nums[rightIndex];
//            for (int i = mid + 1; i <= right; i++) {
//                sum += nums[i];
//                if (sum >= maxRight) {
//                    maxRight = sum;
//                    rightIndex = i;
//                }
//            }
//            int maxSum = maxLeft + maxRight;
//            if (maxSum >= Math.max(sumLeft, sumRight)) {
//                return new int[]{maxSum, leftIndex, rightIndex};
//            } else if (sumLeft >= sumRight) {
//                return new int[]{sumLeft, resultLeft[1], resultLeft[2]};
//            } else {
//                return new int[]{sumRight, resultRight[1], resultRight[2]};
//            }
//        }
//    }

    public int maxSubArray(int[] nums) {
        return partition(nums, 0, nums.length - 1).mSum;
    }

    class Status {
        int iSum = 0;
        int lSum = 0;
        int rSum = 0;
        int mSum = 0;

        public Status() {

        }

        public Status(int iSum, int lSum, int rSum, int mSum) {
            this.iSum = iSum;
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
        }
    }

    public Status partition(int[] nums, int left, int right) {
        if (left == right) {
            int data = nums[left];
            return new Status(data, data, data, data);
        }
        int mid = left + (right - left) / 2;
        Status leftStatus = partition(nums, left, mid);
        Status rightStatus = partition(nums, mid + 1, right);
        Status status = new Status();
        status.iSum = leftStatus.iSum + rightStatus.iSum;
        status.lSum = Math.max(leftStatus.lSum, leftStatus.iSum + rightStatus.lSum);
        status.rSum = Math.max(rightStatus.rSum, rightStatus.iSum + leftStatus.rSum);
        status.mSum = Math.max(Math.max(leftStatus.mSum, rightStatus.mSum), leftStatus.rSum + rightStatus.lSum);
        return status;
    }
}
