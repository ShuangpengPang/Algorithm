package com.shuangpeng.Problem.p0001_0100;

import java.util.Arrays;

public class Problem0016ThreeSumClosest {

//[-1,0,1,1,55]
//        3


//    public static void main(String[] args) {
//        int[] nums = {0, 2, 1, -3};
//        Problem0016ThreeSumClosest a = new Problem0016ThreeSumClosest();
//        a.threeSumClosest(nums, 1);
//    }

    public int threeSumClosest(int[] nums, int target) {
        int length = nums.length;
        if (length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
                if (sum > target) {
                    while (right > 0 && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;
                } else if (sum < target) {
                    if (left < length - 1 && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                } else {
                    return target;
                }
            }
        }
        return closest;
    }
}
