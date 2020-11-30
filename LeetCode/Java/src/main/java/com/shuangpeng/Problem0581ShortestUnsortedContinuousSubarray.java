package com.shuangpeng;

import java.util.ArrayList;
import java.util.List;

public class Problem0581ShortestUnsortedContinuousSubarray {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4};
        int[] nums = {2,6,4,8,10,9,15};
        Problem0581ShortestUnsortedContinuousSubarray a = new Problem0581ShortestUnsortedContinuousSubarray();
        a.findUnsortedSubarray(nums);
    }

    public int findUnsortedSubarray(int[] nums) {
        List<Integer> stack = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int data = nums[i];
            while (!stack.isEmpty() && nums[stack.get(stack.size() - 1)] > data) {
                stack.remove(stack.size() - 1);
            }
            stack.add(i);
        }
        int left = 0;
        int right = stack.size() - 1;
        int i = 0;
        boolean leftStop = false;
        boolean rightStop = false;
        int count = 0;
        while (left <= right && (!leftStop || !rightStop)) {
            if (!leftStop && stack.get(left) == i) {
                left++;
                count++;
            } else {
                leftStop = true;
            }
            if (!rightStop && stack.get(right) == nums.length - i - 1) {
                right--;
                count++;
            } else {
                rightStop = true;
            }
            i++;
        }
        if (left > right) {
            return 0;
        }
        return nums.length - count;
    }
}
