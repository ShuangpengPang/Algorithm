package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1503LastMomentBeforeAllAntsFallOutOfAPlank（所有蚂蚁掉下来前的最后一刻）
 * @date 2023/8/29 7:13 PM
 */
public class Problem1503LastMomentBeforeAllAntsFallOutOfAPlank {

    public int getLastMoment(int n, int[] left, int[] right) {
        int lastMoment = 0;
        for (int num : left) {
            lastMoment = Math.max(lastMoment, num);
        }
        for (int num : right) {
            lastMoment = Math.max(lastMoment, n - num);
        }
        return lastMoment;
    }
}
