package com.shuangpeng.Problem.p2101_2200;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2164SortEvenAndOddIndicesIndependently（对奇偶下标分别排序）
 * @date 2024/3/30 2:59 PM
 */
public class Problem2164SortEvenAndOddIndicesIndependently {

    public int[] sortEvenOdd0(int[] nums) {
        int n = nums.length, m2 = n >> 1, m1 = n - m2;
        Integer[] cnt1 = new Integer[m1], cnt2 = new Integer[m2];
        for (int i = 0, j = 0, k = 0; i < n; i++) {
            if ((i & 1) == 0) {
                cnt1[j++] = nums[i];
            } else {
                cnt2[k++] = nums[i];
            }
        }
        Arrays.sort(cnt1);
        Arrays.sort(cnt2, Comparator.reverseOrder());
        int[] ans = new int[n];
        for (int i = 0, j = 0, k = 0; i < n; i++) {
            if ((i & 1) == 0) {
                ans[i] = cnt1[j++];
            } else {
                ans[i] = cnt2[k++];
            }
        }
        return ans;
    }

    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length, evenCount = n + 1 >> 1, oddCount = n - evenCount;
        int[] even = new int[evenCount], odd = new int[oddCount];
        for (int i = 0, j = 0, k = 0; i < n; i++) {
            if ((i & 1) == 0) {
                even[j++] = nums[i];
            } else {
                odd[k++] = nums[i];
            }
        }
        Arrays.sort(even);
        Arrays.sort(odd);
        int[] ans = new int[n];
        for (int i = 0; i < evenCount; i++) {
            ans[i << 1] = even[i];
        }
        for (int i = 0; i < oddCount; i++) {
            ans[(i << 1) + 1] = odd[oddCount - i - 1];
        }
        return ans;
    }
}
