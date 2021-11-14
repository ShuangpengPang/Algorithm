package com.shuangpeng.Problem.p0301_0400;

public class Problem0335SelfCrossing {

    public boolean isSelfCrossing0(int[] distance) {
        int n = distance.length;
        if (n <= 3) {
            return false;
        }
        int N = 5;
        int[] dp = new int[N];
        dp[0] = distance[0];
        dp[1] = distance[1];
        dp[2] = distance[2];
        int state = dp[2] <= dp[0] ? 1 : 2;
        for (int i = 3; i < n; ++i) {
            int dist = distance[i];
            if (state == 1) {
                if (dist >= dp[(i - 2 + N) % N]) {
                    return true;
                }
            } else if (state == 2) {
                if (dist < dp[(i - 2 + N) % N]) {
                    state = 1;
                } else if (dist == dp[(i - 2 + N) % N]) {
                    state = 3;
                } else {
                    state = 4;
                }
            } else if (state == 3) {
                if (dist + dp[(i - 4 + N) % N] >= dp[(i - 2 + N) % N]) {
                    return true;
                } else {
                    state = 1;
                }
            } else if (state == 4) {
                if (dist + dp[(i - 4 + N) % N] < dp[(i - 2 + N) % N]) {
                    state = 1;
                } else if (dist <= dp[(i - 2 + N) % N]) {
                    state = 5;
                }
            } else if (state == 5) {
                if (dist + dp[(i - 4 + N) % N] >= dp[(i - 2 + N) % N]) {
                    return true;
                }
                state = 1;
            }
            dp[i % N] = dist;
        }
        return false;
    }

    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        for (int i = 3; i < n; ++i) {
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {
                return true;
            }
            if (i >= 4 && distance[i - 1] == distance[i - 3]
                    && distance[i - 2] <= distance[i - 4] + distance[i]) {
                return true;
            }
            if (i >= 5 && distance[i - 1] <= distance[i - 3]
                    && distance[i - 5] + distance[i - 1] >= distance[i - 3]
                    && distance[i - 4] < distance[i - 2]
                    && distance[i] + distance[i - 4] >= distance[i - 2]) {
                return true;
            }
        }
        return false;
    }
}
