package com.shuangpeng.Problem.p2101_2200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2147NumberOfWaysToDivideLongCorridor（分割长廊的方案数）
 * @date 2022/11/19 10:48 AM
 */
public class Problem2147NumberOfWaysToDivideLongCorridor {

    public int numberOfWays(String corridor) {
        int n = corridor.length(), M = (int) 1e9 + 7;
        int ans = 0, pre = 0, plants = 0, seats = 0;
        for (int i = 0; i < n; i++) {
            if (corridor.charAt(i) == 'S') {
                seats++;
                if ((seats & 1) == 0) {
                    ans = seats == 2 ? 1 : (int) ((long) pre * (plants + 1) % M);
                    pre = ans;
                    plants = 0;
                } else {
                    ans = 0;
                }
            } else if ((seats & 1) == 0) {
                plants++;
            }
        }
        return ans;
    }
}
