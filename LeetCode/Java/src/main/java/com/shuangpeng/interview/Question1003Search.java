package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question1003Search（面试题 10.03. 搜索旋转数组）
 * @date 2024/10/11 11:28 AM
 */
public class Question1003Search {

    public int search(int[] arr, int target) {
        return dfs(arr, 0, arr.length - 1, target);
    }

    private int dfs(int[] arr, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int first = arr[start], last = arr[end];
        int left = start, right = end;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (arr[mid] > first) {
                left = mid + 1;
            } else if (arr[mid] < first) {
                right = mid - 1;
            } else if (first != last) {
                left = mid + 1;
            } else {
                int index = dfs(arr, start, mid - 1, target);
                if (index != -1) {
                    return index;
                }
                if (arr[mid] == target) {
                    return mid;
                }
                return dfs(arr, mid + 1, end, target);
            }
        }
        int index = find(arr, start, left - 1, target);
        if (index != -1) {
            return index;
        }
        return find(arr, left, end, target);
    }

    private int find(int[] arr, int start, int end, int target) {
        int left = start, right = end;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left <= end &&  arr[left] == target ? left : -1;
    }
}

//class Question1003Search0 {
//
//    public int search(int[] arr, int target) {
//        int n = arr.length, left = 0, right = n - 1;
//    }
//}
