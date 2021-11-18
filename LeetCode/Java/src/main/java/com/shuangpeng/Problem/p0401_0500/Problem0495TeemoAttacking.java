package com.shuangpeng.Problem.p0401_0500;

public class Problem0495TeemoAttacking {

    public int findPoisonedDuration0(int[] timeSeries, int duration) {
        int ans = 0;
        int n = timeSeries.length;
        int left = timeSeries[0], right = timeSeries[0] + duration;
        for (int i = 1; i < n; ++i) {
            if (timeSeries[i] >= right) {
                ans += right - left;
                left = timeSeries[i];
            }
            right = timeSeries[i] + duration;
        }
        ans += right - left;
        return ans;
    }

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0, expired = 0;
        for (int time : timeSeries) {
            if (time >= expired) {
                ans += duration;
            } else {
                ans += time + duration - expired;
            }
            expired = time + duration;
        }
        return ans;
    }
}
