package com.shuangpeng.competition.双周赛.第082场双周赛;

import java.util.Arrays;

/**
 * @Description: Problem2332TheLatestTimeToCatchABus（坐上公交的最晚时间）
 * @Date 2022/7/14 5:48 PM
 * @Version 1.0
 */
public class Problem2332TheLatestTimeToCatchABus {

    // 比赛时写法
    public int latestTimeCatchTheBus0(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int n = buses.length, m = passengers.length;
        int i = 0, j = 0;
        int count = 0;
        while (i < n && j < m) {
            if (i < n - 1) {
                while (j < m && passengers[j] <= buses[i] && count < capacity) {
                    j++;
                    count++;
                }
                i++;
                count = 0;
            } else {
                while (j < m && passengers[j] < buses[i] && count < capacity - 1) {
                    j++;
                    count++;
                }
                if (j == m || passengers[j] > buses[i]) {
                    return buses[i];
                }
                int k = j;
                while (k > 0 && passengers[k] == passengers[k - 1] + 1) {
                    k--;
                }
                return passengers[k] - 1;
            }
        }
        return buses[n - 1];
    }

    public int latestTimeCatchTheBus1(int[] buses, int[] passengers, int capacity) {
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

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int n = passengers.length, c = 0, p = 0;
        for (int t : buses) {
            for (c = capacity; c > 0 && p < n && passengers[p] <= t; c--) {
                p++;
            }
        }
        p--;
        int ans = c > 0 ? buses[buses.length - 1] : passengers[p];
        while (p >= 0 && ans == passengers[p]) {
            p--;
            ans--;
        }
        return ans;
    }
}
