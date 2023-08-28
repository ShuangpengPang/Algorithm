package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0849MaximizeDistanceToClosestPerson（到最近的人的最大距离）
 * @date 2023/8/28 11:32 AM
 */
public class Problem0849MaximizeDistanceToClosestPerson {

    public int maxDistToClosest(int[] seats) {
        int n = seats.length, first = -1, last = -1, max = 0;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                if (last == -1) {
                    first = i;
                }
                max = Math.max(max, i - last);
                last = i;
            }
        }
        return Math.max(first, Math.max(max >> 1, n - last - 1));
    }
}
