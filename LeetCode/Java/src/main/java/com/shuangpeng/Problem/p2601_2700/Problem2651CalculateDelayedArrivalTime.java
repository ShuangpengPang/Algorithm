package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2651CalculateDelayedArrivalTime（计算列车到站时间）
 * @date 2023/9/8 10:16 AM
 */
public class Problem2651CalculateDelayedArrivalTime {

    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
}
