package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR173TakeAttendance（点名）
 * @date 2024/5/17 11:35 AM
 */
public class LCR173TakeAttendance {

    public int takeAttendance0(int[] records) {
        int n = records.length;
        for (int i = 0; i < n; i++) {
            int r = records[i];
            while (r != i && r != n) {
                int tmp = records[r];
                records[r] = r;
                r = tmp;
            }
            records[i] = r;
        }
        for (int i = 0; i < n; i++) {
            if (records[i] != i) {
                return i;
            }
        }
        return n;
    }

    public int takeAttendance(int[] records) {
        int left = 0, right = records.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (records[mid] == mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
