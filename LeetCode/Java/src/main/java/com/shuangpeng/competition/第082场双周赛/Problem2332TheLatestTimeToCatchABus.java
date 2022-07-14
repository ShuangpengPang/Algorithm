package com.shuangpeng.competition.第082场双周赛;

import java.util.Arrays;

/**
 * @Description: Problem2332TheLatestTimeToCatchABus（坐上公交的最晚时间）
 * @Date 2022/7/14 5:48 PM
 * @Version 1.0
 */
public class Problem2332TheLatestTimeToCatchABus {

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int n1 = buses.length, n2 = passengers.length;
        int p = 0;
        for (int i = 0; i < n1 - 1 && p < n2; i++) {
            int count = 0;
            while (p < n2 && passengers[p] <= buses[i] && count < capacity) {
                p++;
                count++;
            }
        }
        int last = buses[n1 - 1];
        if (p == n2) {
            return last;
        }
        int count = 0;
        while (p < n2 && passengers[p] < last && count < capacity - 1) {
            p++;
            count++;
        }
        if (p == n2 || passengers[p] > last) {
            return last;
        }
        while (p > 0 && passengers[p] == passengers[p - 1] + 1) {
            p--;
        }
        return passengers[p] - 1;
    }
}
