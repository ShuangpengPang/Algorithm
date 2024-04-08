package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3100WaterBottlesII（换水问题II）
 * @date 2024/4/8 3:28 PM
 */
public class Problem3100WaterBottlesII {

    public int maxBottlesDrunk0(int numBottles, int numExchange) {
        int ans = 0, empty = 0;
        while (numBottles + empty >= numExchange) {
            ans += numBottles;
            empty += numBottles - numExchange;
            numBottles = 1;
            numExchange++;
        }
        return ans + numBottles;
    }

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            ans++;
            numBottles += 1 - numExchange++;
        }
        return ans;
    }
}
