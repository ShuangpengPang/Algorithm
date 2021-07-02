package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem1833MaximumIceCreamBars {

    public int maxIceCream0(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        int i = 0;
        while (i < costs.length && coins >= costs[i]) {
            coins -= costs[i];
            count++;
            i++;
        }
        return count;
    }

    public int maxIceCream(int[] costs, int coins) {
        int n = costs.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, costs[i]);
            max = Math.max(max, costs[i]);
        }
        int diff = max - min + 1;
        int[] array = new int[diff];
        for (int i = 0; i < n; i++) {
            array[costs[i] - min]++;
        }
        int count = 0;
        for (int i = 0; i < diff; i++) {
            int value = min + i;
            if (coins < value) {
                break;
            }
            int k = Math.min(array[i], coins / value);
            count += k;
            coins -= k * value;
        }
        return count;
    }
}
