package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR173TakeAttendance（点名）
 * @date 2024/5/17 11:35 AM
 */
public class LCR173TakeAttendance {

    public int takeAttendance(int[] records) {
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
}
