package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR163FindKthNumber（LCR 163. 找到第 k 位数字）
 * @date 2024/8/31 3:07 PM
 */
public class LCR163FindKthNumber {

    private static final long[] preSum = new long[11];

    static {
        long count = 9;
        for (int i = 1; i <= 10; i++) {
            preSum[i] = preSum[i - 1] + i * count;
            count *= 10;
        }
    }

    public int findKthNumber(int k) {
        if (k < 10) {
            return k;
        }
        int left = 0, right = 10;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (preSum[mid] < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        k -= preSum[left - 1];
        int num = (int) Math.pow(10, left - 1) + (k + left - 1) / left - 1;
        int m = (k - 1) % left;
        return num / (int) Math.pow(10, left - m - 1) % 10;
    }
}
