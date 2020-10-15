package com.shuangpeng;

public class Problem0001TwoSum {

    public static void quickSort(int[] array, int start, int end) {
        if (array == null || array.length <= 1 || start >= end) {
            return;
        }
        int left = start;
        int right = end;
        while (left < right) {
            int p = partition(array, left, right);
            quickSort(array, left, p - 1);
            left = p + 1;
        }
    }

    public static int partition(int[] array, int start, int end) {
        int random = (int) (start + (end - start) * Math.random());
        swap(array, random, end);
        int pivot = array[end];
        int j = start - 1;
        for (int i = start; i < end; i++) {
            if (array[i] <= pivot) {
                j++;
                swap(array, j, i);
            }
        }
        swap(array, j + 1, end);
        return j + 1;
    }

    public static void swap(int[] array, int i, int j) {
        if (i != j) {
            array[i] = array[j] - array[i];
            array[j] = array[j] - array[i];
            array[i] = array[j] + array[i];
        }
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }
        quickSort(copy, 0, copy.length - 1);
        int j = 0;
        int k = copy.length - 1;
        int m = -1;
        int n = -1;
        while (j < k) {
            int left = copy[j];
            int right = copy[k];
            if ((left + right) == target) {
                for (int s = 0; s < nums.length; s++) {
                    int element = nums[s];
                    if (element == left && m == -1) {
                        m = s;
                    } else if (element == right) {
                        n = s;
                    }
                    if (m != -1 && n != -1) {
                        return new int[]{m, n};
                    }
                }
            } else if ((left + right) < target) {
                j++;
            } else {
                k--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Problem0001TwoSum problem0001TwoSum = new Problem0001TwoSum();
        int[] nums = {3, 3};
        problem0001TwoSum.twoSum(nums, 6);
    }
}
