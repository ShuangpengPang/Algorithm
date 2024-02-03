package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1287ElementAppearingMoreThan25PercentInSortedArray（有序数组中出现次数超过25%的元素）
 * @date 2024/2/3 6:32 PM
 */
public class Problem1287ElementAppearingMoreThan25PercentInSortedArray {

    public int findSpecialInteger(int[] arr) {
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
}
