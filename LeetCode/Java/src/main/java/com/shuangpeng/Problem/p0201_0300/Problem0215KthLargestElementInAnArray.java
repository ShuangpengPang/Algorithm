package com.shuangpeng.Problem.p0201_0300;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Problem0215KthLargestElementInAnArray {

//    [3,2,1,5,6,4]
//            2

//    public static void main(String[] args) {
//        int[] nums = {3,2,1,5,6,4};
//        Problem0215KthLargestElementInAnArray a = new Problem0215KthLargestElementInAnArray();
//        a.findKthLargest(nums, 2);
//    }

    public int findKthLargest0(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }
        int target = nums.length - k;
        int index = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start != end) {
            index = partition(nums, start, end);
            if (index < target) {
                start = index + 1;
            } else if (index > target) {
                end = index - 1;
            } else {
                return nums[index];
            }
        }
        return nums[start];
    }

    public int partition(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        }
        int rand = ThreadLocalRandom.current().nextInt(start, end + 1);
        swap(nums, rand, end);
        int pivot = nums[end];
        int p = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                p++;
                swap(nums, p, i);
            }
        }
        swap(nums, p + 1, end);
        return p + 1;
    }

    public void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
