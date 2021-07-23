package com.shuangpeng.Problem;

public class Problem0552StudentAttendanceRecords {

    public int checkRecord0(int n) {
        // _P : 0, PL : 1, LL : 2, _A : 3, AP : 4, AL : 5, ALL : 6
        int MOD = (int) 1e9 + 7;
        if (n <= 0) {
            return 0;
        }
        int[] dp = {1, 1, 0, 1, 0, 0, 0};
        for (int i = 2; i <= n; i++) {
            int[] temp = new int[7];
            temp[0] = ((dp[0] + dp[1]) % MOD + dp[2]) % MOD;
            temp[1] = dp[0];
            temp[2] = dp[1];
            temp[3] = ((dp[0] + dp[1]) % MOD + dp[2]) % MOD;
            temp[4] = ((dp[3] + dp[4]) % MOD + (dp[5] + dp[6]) % MOD) % MOD;
            temp[5] = (dp[3] + dp[4]) % MOD;
            temp[6] = dp[5];
            dp = temp;
        }
        int answer = 0;
        for (int i = 0; i < dp.length; i++) {
            answer = (answer + dp[i]) % MOD;
        }
        return answer;
    }

    public int checkRecord1(int n) {
        if (n <= 0) {
            return 0;
        }
        final int MOD = (int) 1e9 + 7;
        int[] dp = {1, 1, 0, 1, 0, 0};
        for (int i = 2; i <= n; i++) {
            int[] temp = new int[6];
            temp[0] = ((dp[0] + dp[1]) % MOD + dp[2]) % MOD;
            temp[1] = dp[0];
            temp[2] = dp[1];
            temp[3] = (((dp[0] + dp[1]) % MOD + (dp[2] + dp[3]) % MOD) % MOD + (dp[4] + dp[5]) % MOD) % MOD;
            temp[4] = dp[3];
            temp[5] = dp[4];
            dp = temp;
        }
        int answer = 0;
        for (int i = 0; i < dp.length; i++) {
            answer = (answer + dp[i]) % MOD;
        }
        return answer;
    }
}
