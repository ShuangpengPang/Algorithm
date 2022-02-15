package com.shuangpeng.Problem.p0501_0600;

/**
 * @Description:
 * @Date 2022/2/14 7:40 PM
 * @Version 1.0
 */
public class Problem0540SingleElementInASortedArray {

    public int singleNonDuplicate0(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            boolean isOdd = (mid & 1) == 1;
            if (isOdd && nums[mid] == nums[mid - 1]) {
                left = mid + 1;
            } else if (isOdd && mid + 1 < n && nums[mid] == nums[mid + 1]) {
                right = mid - 1;
            } else if (!isOdd && mid + 1 < n && nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else if (!isOdd && mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                right = mid - 2;
            } else {
                return nums[mid];
            }
        }
        return nums[left];
    }
}
