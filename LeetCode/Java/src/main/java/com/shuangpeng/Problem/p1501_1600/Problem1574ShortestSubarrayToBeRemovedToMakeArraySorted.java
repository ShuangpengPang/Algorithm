package com.shuangpeng.Problem.p1501_1600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1574ShortestSubarrayToBeRemovedToMakeArraySorted（删除最短的子数组使剩余数组有序）
 * @date 2023/3/25 3:20 PM
 */
public class Problem1574ShortestSubarrayToBeRemovedToMakeArraySorted {

    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
        for (int i = 0; i < n && (i == 0 || arr[i] >= arr[i - 1]); i++) {
            left.add(arr[i]);
        }
        for (int i = n - 1; i >= 0 && (i == n - 1 || arr[i] <= arr[i + 1]); i--) {
            right.add(arr[i]);
        }
        int n1 = left.size(), n2 = right.size();
        if (n1 + n2 > n) {
            return 0;
        }
        int maxLength = Math.max(n1, n2);
        for (int i = 0; i < n2; i++) {
            int num = right.get(i);
            int low = 0, high = n1 - 1;
            while (low <= high) {
                int mid = low + high >> 1;
                if (left.get(mid) <= num) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            maxLength = Math.max(maxLength, low + i + 1);
        }
        return n - maxLength;
    }
}
