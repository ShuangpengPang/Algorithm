package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0508DrawLine（面试题 05.08. 绘制直线）
 * @date 2024/8/26 6:40 PM
 */
public class Question0508DrawLine {

    public int[] drawLine0(int length, int w, int x1, int x2, int y) {
        int[] ans = new int[length];
        int count = w / 32, N = count * (y + 1);
        for (int i = count * y, x = 0; i < length && i < N; i++, x += 32) {
            int left = Math.max(x, x1), right = Math.min(x + 31, x2);
            ans[i] = left <= right ? getValue(31 - left % 32, 31 - right % 32) : 0;
        }
        return ans;
    }

    private int getValue(int high, int low) {
        int ans = 0;
        for (int i = low; i <= high; i++) {
            ans += 1 << i;
        }
        return ans;
    }

    public int[] drawLine1(int length, int w, int x1, int x2, int y) {
        int[] ans = new int[length];
        for (int i = x1, s = w / 32 * y; i <= x2; i++) {
            ans[s + i / 32] |= 1 << (31 - i % 32);
        }
        return ans;
    }

    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] ans = new int[length];
        int low = (w * y + x1) / 32, high = (w * y + x2) / 32;
        for (int i = low; i <= high; i++) {
            ans[i] = -1;
        }
        ans[low] = -1 >>> (x1 % 32);
        ans[high] &= Integer.MIN_VALUE >> (x2 % 32);
        return ans;
    }
}
