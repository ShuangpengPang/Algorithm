package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1287ElementAppearingMoreThan25PercentInSortedArray（有序数组中出现次数超过25%的元素）
 * @date 2024/2/3 6:32 PM
 */
public class Problem1287ElementAppearingMoreThan25PercentInSortedArray {

    public int findSpecialInteger0(int[] arr) {
        int n = arr.length,  m = n >> 2;
        for (int i = 0, cnt = 0; i < n; i++) {
            if (++cnt > m) {
                return arr[i];
            }
            if (i < n - 1 && arr[i] != arr[i + 1]) {
                cnt = 0;
            }
        }
        return 0;
    }

    public int findSpecialInteger(int[] arr) {
        int n = arr.length, span = (n >> 2) + 1;
        for (int i = 0; i < n; i += span) {
            if (getCount(arr, arr[i]) >= span) {
                return arr[i];
            }
        }
        return -1;
    }

    private int getCount(int[] arr, int x) {
        int n = arr.length, left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int tmp = left;
        right = n - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (arr[mid] == x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - tmp;
    }
}
