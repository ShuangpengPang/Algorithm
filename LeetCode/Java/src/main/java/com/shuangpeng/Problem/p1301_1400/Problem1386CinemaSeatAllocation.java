package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1386CinemaSeatAllocation（安排电影院座位）
 * @date 2023/8/14 10:51 AM
 */
public class Problem1386CinemaSeatAllocation {

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Arrays.sort(reservedSeats, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int ans = n << 1;
        int i = 0, j = 0, m = reservedSeats.length;
        while (i < m) {
            boolean[] set = new boolean[10];
            while (i < m && reservedSeats[j][0] == reservedSeats[i][0]) {
                set[reservedSeats[i][1] - 1] = true;
                i++;
            }
            if (!check(set, 1, 8)) {
                if (check(set, 1, 4) || check(set, 5, 8) || check(set, 3, 6)) {
                    ans--;
                } else {
                    ans -= 2;
                }
            }
            j = i;
        }
        return ans;
    }

    private boolean check(boolean[] set, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (set[i]) {
                return false;
            }
        }
        return true;
    }
}
