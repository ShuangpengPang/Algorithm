package com.shuangpeng;

public class Problem0287FindTheDuplicateNumber {

    // 二分查找
    public int findDuplicate0(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int findDuplicate(int[] nums) {
        int bitMax = 31;
        int n = nums.length;
        while ((n - 1) >> bitMax == 0) {
            bitMax--;
        }
        int result = 0;
        for (int bit = 0; bit <= bitMax; bit++) {
            int x = 0;
            int y = 0;
            for (int i = 0; i < n; i++) {
                if (((i >> bit) & 1) == 1) {
                    x++;
                }
                if (((nums[i] >> bit) & 1) == 1) {
                    y++;
                }
            }
            if (y > x) {
                result |= (1 << bit);
            }
        }
        return result;
    }
}
