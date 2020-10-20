package com.shuangpeng;

public class Problem0033SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        // 4,5 ,6,7,0,1,2  3

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[right]) {
                if (target < nums[mid]) {
                    right = mid - 1;
                } else if (target > nums[mid]) {
                    left = mid + 1;
                } else {
                    return mid;
                }
            } else if (nums[left] <= nums[mid] && target >= nums[left] && target < nums[mid]) {
                right = mid - 1;
            } else if (nums[left] <= nums[mid] && target != nums[mid]) {
                left = mid + 1;
            } else if (nums[left] > nums[mid] && target <= nums[right] && target > nums[mid]) {
                left = mid + 1;
            } else if (nums[left] > nums[mid] && target != nums[mid]) {
                right = mid - 1;
            } else if (target == nums[mid]) {
                return mid;
            }
        }
        return -1;
    }

    public int search0(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

//    public static void main(String[] args) {
//        System.err.println(numberConvert(6, 6));
//    }

    public static String numberConvert(int n, int k) {
        if (k > 10 || k < 2 || n < 0) {
            return null;
        }
        int[] data = new int[100];
        int count = 0;
        while (n >= k) {
            int left = n % k;
            n = n / k;
            data[count++] = left;
        }
        // n < k;
        StringBuilder stringBuilder = new StringBuilder("" + n);
        for (int i = count - 1; i >= 0; i--) {
            stringBuilder.append(data[i]);
        }
        return stringBuilder.toString();
    }
}
