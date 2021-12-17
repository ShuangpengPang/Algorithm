package com.shuangpeng.Problem.p1501_1600;

public class Problem1518WaterBottles {

    public int numWaterBottles0(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            int count = numBottles / numExchange;
            numBottles = count + numBottles % numExchange;
            ans += count;
        }
        return ans;
    }

    public int numWaterBottles(int numBottles, int numExchange) {
        return numBottles + (numBottles - 1) / (numExchange - 1);
    }
}
