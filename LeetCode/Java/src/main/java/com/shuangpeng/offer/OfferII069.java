package com.shuangpeng.offer;

public class OfferII069 {

    public int peakIndexInMountainArray0(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (mid == 0) {
                return 1;
            }
            if (arr[mid - 1] < arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    public int peakIndexInMountainArray1(int[] arr) {
        int left = 1, right = arr.length - 2;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid - 1] < arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    public int peakIndexInMountainArray(int[] arr) {
        int left = 1, right = arr.length - 2, ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid - 1] < arr[mid]) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
