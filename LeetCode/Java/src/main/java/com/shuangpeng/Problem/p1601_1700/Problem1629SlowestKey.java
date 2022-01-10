package com.shuangpeng.Problem.p1601_1700;

public class Problem1629SlowestKey {

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char ans = keysPressed.charAt(0);
        int duration = releaseTimes[0];
        int n = keysPressed.length();
        for (int i = 1; i < n; ++i) {
            int d = releaseTimes[i] - releaseTimes[i - 1];
            char c = keysPressed.charAt(i);
            if (d > duration) {
                duration = d;
                ans = c;
            } else if (d == duration) {
                if (c > ans) {
                    ans = c;
                }
            }
        }
        return ans;
    }
}
