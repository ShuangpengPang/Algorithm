package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2739TotalDistanceTraveled（总行驶距离）
 * @date 2024/4/7 3:23 PM
 */
public class Problem2739TotalDistanceTraveled {

    public int distanceTraveled0(int mainTank, int additionalTank) {
        int ans = 0;
        while (additionalTank > 0 && mainTank >= 5) {
            int num = Math.min(additionalTank, mainTank / 5);
            ans += num * 5;
            mainTank -= num << 2;
            additionalTank -= num;
        }
        return (ans + mainTank) * 10;
    }

    public int distanceTraveled(int mainTank, int additionalTank) {
        return (mainTank + Math.min(additionalTank, mainTank - 1 >> 2)) * 10;
    }
}
