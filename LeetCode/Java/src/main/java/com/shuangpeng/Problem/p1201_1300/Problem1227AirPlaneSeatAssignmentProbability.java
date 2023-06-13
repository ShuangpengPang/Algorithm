package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1227AirPlaneSeatAssignmentProbability（飞机座位分配概率）
 * @date 2023/6/13 1:57 PM
 */
public class Problem1227AirPlaneSeatAssignmentProbability {

    public double nthPersonGetsNthSeat0(int n) {
        double p = 0, ans = 1.0 / n;
        for (int i = 1; i < n; i++) {
            p += i == 1 ? 1.0 / n : (1.0 - ans) / (n - i + 1);
            ans = 1.0 - p;
        }
        return ans;
    }

    public double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1.0 : 0.5;
    }
}
