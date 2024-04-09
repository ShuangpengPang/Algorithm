package com.shuangpeng.lcp;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCP78RampartDefensiveLine（城墙防线）
 * @date 2024/4/9 5:01 PM
 */
public class LCP78RampartDefensiveLine {

    public int rampartDefensiveLine(int[][] rampart) {
        int n = rampart.length;
        int left = 0, right = rampart[n - 1][0] - rampart[0][1];
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (check(rampart, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    private boolean check(int[][] rampart, int m) {
        for (int n = rampart.length, i = 1, p = rampart[0][1]; i < n; i++) {
            if (p > rampart[i][0]) {
                return false;
            }
            p = Math.max(rampart[i][1], p + m + rampart[i][1] - rampart[i][0]);
        }
        return true;
    }
}
