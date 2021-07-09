package com.shuangpeng.interview;

public class Question1710MajorElement {

    public int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (count == 0) {
                candidate = nums[i];
                count++;
            } else if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        if (count == 0) {
            return -1;
        }
        count = 0;
        for (int i = 0; i < n; i++) {
            if (candidate == nums[i]) {
                count++;
            }
        }
        return count > n / 2 ? candidate : -1;
    }
}
