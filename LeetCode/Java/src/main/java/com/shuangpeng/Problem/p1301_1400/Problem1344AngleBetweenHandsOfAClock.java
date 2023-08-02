package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1344AngleBetweenHandsOfAClock（时钟指针的夹角）
 * @date 2023/8/2 6:22 PM
 */
public class Problem1344AngleBetweenHandsOfAClock {

    public double angleClock0(int hour, int minutes) {
        double ans = Math.abs(minutes * 6 - hour % 12 * 30 - minutes / 2.0);
        return Math.min(ans, 360 - ans);
    }

    public double angleClock(int hour, int minutes) {
        double minuteAngle = 6, hourAngle = 30;
        double angle = Math.abs(minutes * minuteAngle - (hour % 12 + minutes / 60.0) * hourAngle);
        return Math.min(angle, 360 - angle);
    }
}
