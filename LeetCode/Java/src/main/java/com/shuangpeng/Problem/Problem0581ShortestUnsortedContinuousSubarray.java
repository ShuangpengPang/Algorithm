package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Problem0581ShortestUnsortedContinuousSubarray {

//    public static void main(String[] args) {
////        int[] nums = {1, 2, 3, 4};
//        int[] nums = {2,6,4,8,10,9,15};
//        Problem0581ShortestUnsortedContinuousSubarray a = new Problem0581ShortestUnsortedContinuousSubarray();
//        a.findUnsortedSubarray(nums);
//    }

    public int findUnsortedSubarray0(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        List<Integer> minStack = new ArrayList<>(nums.length);
        List<Integer> maxStack = new ArrayList<>(nums.length);
        minStack.add(nums[0]);
        maxStack.add(nums[nums.length - 1]);
        int leftCount = 1;
        int rightCount = 1;
        for (int i = 1; i < nums.length; i++) {
            while (leftCount > 0 && !minStack.isEmpty() && minStack.get(minStack.size() - 1) > nums[i]) {
                minStack.remove(minStack.size() - 1);
                leftCount--;
            }
            if (leftCount == i) {
                minStack.add(nums[i]);
                leftCount++;
            }
            int j = nums.length - 1 - i;
            while (rightCount > 0 && !maxStack.isEmpty() && maxStack.get(maxStack.size() - 1) < nums[j]) {
                maxStack.remove(maxStack.size() - 1);
                rightCount--;
            }
            if (rightCount == i) {
                maxStack.add(nums[j]);
                rightCount++;
            }
            if ((leftCount == 0 && rightCount == 0) || (leftCount + rightCount) > nums.length + 1) {
                break;
            }
        }
        if (leftCount + rightCount >= nums.length) {
            return 0;
        }
        return nums.length - leftCount - rightCount;
    }

    public int findUnsortedSubarray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int l = 0;
        int r = length - 1;
        for (int i = 0; i < length; i++) {
            for (int j = l - 1; j >= 0; j--) {
                if (nums[j] > nums[i]) {
                    l--;
                } else {
                    break;
                }
            }
            if (l == i) {
                l++;
            }
            for (int j = r + 1; j < length; j++) {
                if (nums[length - 1 - i] > nums[j]) {
                    r++;
                } else {
                    break;
                }
            }
            if (r == length - 1 - i) {
                r--;
            }
        }
        return r - l + 1 >= 0 ? r - l + 1 : 0;
    }

    public int findUnsortedSubarray2(int[] nums) {
        int left = nums.length;
        int right = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    left = Math.min(left, i);
                    right = Math.max(right, j);
                }
            }
        }
        return right - left + 1 < 0 ? 0 : right - left + 1;
    }

    public int findUnsortedSubarray3(int[] nums) {
        int[] copy = nums.clone();
        Arrays.sort(copy);
        int left = 0;
        int right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            int j = nums.length - 1 - i;
            if (left == i && nums[i] == copy[i]) {
                left++;
            }
            if (j == right && nums[j] == copy[j]) {
                right--;
            }
        }
        return right - left + 1 < 0 ? 0 : right - left + 1;
    }

    public int findUnsortedSubarray4(int[] nums) {
        int left = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left = Math.min(left, stack.pop());
            }
            if (i == stack.size()) {
                stack.push(i);
            }
            if (left == 0) {
                break;
            }
        }
        stack.clear();
        int right = -1;
        for (int i = 0; i < nums.length; i++) {
            int j = nums.length - i - 1;
            while (!stack.isEmpty() && nums[j] > nums[stack.peek()]) {
                right = Math.max(right, stack.pop());
            }
            if (i == stack.size()) {
                stack.push(j);
            }
            if (right >= nums.length - 1) {
                break;
            }
        }
        return right - left + 1 < 0 ? 0 : right - left + 1;
    }

    public int findUnsortedSubarray5(int[] nums) {
        int length = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < length; i++) {
            if (nums[i] < nums[i - 1]) {
                min = Math.min(min, nums[i]);
            }
            int j = length - i - 1;
            if (nums[j] > nums[j + 1]) {
                max = Math.max(max, nums[j]);
            }
        }
        int left = 0;
        int right = length - 1;
        for (int i = 0; i < length; i++) {
            if ((left != i && right != length - i - 1) || (right - left < -1)) {
                break;
            }
            if (left == i && nums[i] <= min) {
                left++;
            }
            if (right == length - i - 1 && nums[right] >= max) {
                right--;
            }
        }
        return right - left + 1 < 0 ? 0 : right - left + 1;
    }

    public int findUnsortedSubarray6(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int left = 0;
        for (int i = 1; i < n; i++) {
            if (left == i - 1 && nums[left] <= nums[i]) {
                left++;
                continue;
            }
            while (left >= 0 && nums[i] < nums[left]) {
                left--;
            }
            if (left == -1) {
                break;
            }
        }
        int right = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (i + 1 == right && nums[i] <= nums[right]) {
                right--;
                continue;
            }
            while (right < n && nums[i] > nums[right]) {
                right++;
            }
            if (right == n) {
                break;
            }
        }
        return Math.max(0, right - left - 1);
    }

    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int right = -1, maxn = Integer.MIN_VALUE;
        int left = n, minn = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (nums[n - i - 1] > minn) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return Math.max(0, right - left + 1);
    }
}
