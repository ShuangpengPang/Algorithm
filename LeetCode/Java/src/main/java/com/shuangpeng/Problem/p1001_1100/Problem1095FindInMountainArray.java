package com.shuangpeng.Problem.p1001_1100;

/**
 * @Description: Problem1095FindInMountainArray（山脉数组中查找目标值）
 * @Date 2022/5/18 3:45 PM
 * @Version 1.0
 */
public class Problem1095FindInMountainArray {

    public int findInMountainArray0(int target, MountainArray mountainArr) {
        return findIndex(mountainArr, 0, mountainArr.length() - 1, target);
    }

    private int findIndex(MountainArray mountainArr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return mountainArr.get(left) == target ? left : -1;
        }
        int mid = left + ((right - left) >> 1);
        int num = mountainArr.get(mid);
        if (target < num) {
            int index = findIndex(mountainArr, left, mid - 1, target);
            return index != -1 ? index : findIndex(mountainArr, mid + 1, right, target);
        } else if (target > num) {
            if (num < mountainArr.get(mid + 1)) {
                return findIndex(mountainArr, mid + 1, right, target);
            } else {
                return findIndex(mountainArr, left, mid - 1, target);
            }
        } else {
            int index = findIndex(mountainArr, left, mid - 1, target);
            return index != -1 ? index : mid;
        }
    }

    public int findInMountainArray1(int target, MountainArray mountainArr) {
        int n = mountainArr.length();
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int index = binarySearch(mountainArr, 0, left, target, true);
        return index != -1 ? index : binarySearch(mountainArr, left + 1, n - 1, target, false);
    }

    private int binarySearch(MountainArray mountainArray, int left, int right, int target, boolean ascent) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int num = mountainArray.get(mid);
            if (ascent && target > num || !ascent && target < num) {
                left = mid + 1;
            } else if (ascent && target < num || !ascent && target > num) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

//    public static void main(String[] args) {
//        Problem1095FindInMountainArray a = new Problem1095FindInMountainArray();
//        int[] nums = {1,2,3,4,5,3,1};
//        MountainArray mountainArray = new MountainArray() {
//            @Override
//            public int get(int index) {
//                return nums[index];
//            }
//
//            @Override
//            public int length() {
//                return nums.length;
//            }
//        };
//        int i = a.findInMountainArray(3, mountainArray);
//        int j = 1;
//    }
}

/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 */
 interface MountainArray {
      int get(int index);
      int length();
 }
