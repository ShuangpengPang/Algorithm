package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0932BeautifulArray（漂亮数组）
 * @date 2023/3/10 3:19 PM
 */
public class Problem0932BeautifulArray {

    int[] tmp;

    public int[] beautifulArray(int n) {
        int[] nums = new int[n];
        tmp = new int[n];
        Arrays.setAll(nums, i -> i + 1);
        divideAndConquer(nums, 0, n - 1);
        return nums;
    }

    private void divideAndConquer(int[] nums, int s, int e) {
        if (s >= e) {
            return;
        }
        int n = e - s + 1, m = s + ((n + 1) >> 1);
        for (int i = s, j = s, k = m; i <= e; i++) {
            if (((i - s) & 1) == 0) {
                tmp[j++] = nums[i];
            } else {
                tmp[k++] = nums[i];
            }
        }
        System.arraycopy(tmp, s, nums, s, n);
        divideAndConquer(nums, s, m - 1);
        divideAndConquer(nums, m, e);
    }
}
