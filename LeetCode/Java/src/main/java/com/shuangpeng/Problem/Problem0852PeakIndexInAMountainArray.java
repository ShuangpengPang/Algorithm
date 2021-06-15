package com.shuangpeng.Problem;

public class Problem0852PeakIndexInAMountainArray {

    public int peakIndexInMountainArray0(int[] arr) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            int a = arr[mid - 1];
            int b = arr[mid];
            int c = arr[mid + 1];
            if (a < b && b < c) {
                left = mid + 1;
            } else if (a > b && b > c) {
                right = mid;
            } else {
                return mid;
            }
        }
        return left;
    }

    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
