package com.shuangpeng.Problem.p0601_0700;

/**
 * @Description: Problem0668KthSmallestNumberInMultiplicationTable（乘法表中第k小的数）
 * @Date 2022/5/18 10:04 AM
 * @Version 1.0
 */
public class Problem0668KthSmallestNumberInMultiplicationTable {

    public int findKthNumber0(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (getCount(m, n, mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int getCount(int m, int n, int num) {
        int count = 0;
        for (int i = 1; i <= m; ++i) {
            int left = 1, right = n;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (i * mid > num) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            count += left - 1;
        }
        return count;
    }

    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int count = mid / n * n;
            for (int i = mid / n + 1; i <= m; ++i) {
                count += mid / i;
            }
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
