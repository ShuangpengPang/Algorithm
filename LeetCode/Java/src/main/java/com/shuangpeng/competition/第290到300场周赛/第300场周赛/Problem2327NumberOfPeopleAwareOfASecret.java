package com.shuangpeng.competition.第290到300场周赛.第300场周赛;

/**
 * @Description: Problem2327NumberOfPeopleAwareOfASecret（知道秘密的人数）
 * @Date 2022/7/7 10:44 PM
 * @Version 1.0
 */
public class Problem2327NumberOfPeopleAwareOfASecret {

    // 比赛时写法
    public int peopleAwareOfSecret0(int n, int delay, int forget) {
        int M = (int) 1e9 + 7;
        int[] first = new int[n + 1];
        int ans = 1;
        first[1] = 1;
        for (int i = 2; i <= n; i++) {
            int s = i - forget;
            if (s >= 1) {
                ans = (ans - first[s] + M) % M;
            }
            for (int j = s + 1; j <= i - delay; j++) {
                if (j >= 1) {
                    first[i] = (first[i] + first[j]) % M;
                }
            }
            ans = (ans + first[i]) % M;
        }
        return ans;
    }

    public int peopleAwareOfSecret1(int n, int delay, int forget) {
        int M = (int) 1e9 + 7;
        int[] first = new int[n + 1];
        first[1] = 1;
        int ans = 1;
        for (int i = 2; i <= n; i++) {
            int j = i - forget;
            if (j >= 1) {
                ans = (ans - first[j] + M) % M;
            }
            long sum = 0;
            for (int k = Math.max(1, j + 1); k <= i - delay; k++) {
                sum += first[k];
            }
            first[i] = (int) (sum % M);
            ans = (ans + first[i]) % M;
        }
        return ans;
    }

    public int peopleAwareOfSecret2(int n, int delay, int forget) {
        int M = (int) 1e9 + 7;
        int[] dp = new int[n];
        dp[0] = 1;
        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            if (i + forget >= n) {
                cnt = (cnt + dp[i]) % M;
            }
            for (int j = i + delay; j < Math.min(n, i + forget); j++) {
                dp[j] = (dp[j] + dp[i]) % M;
            }
        }
        return (dp[n - 1] + cnt) % M;
    }

    public int peopleAwareOfSecret3(int n, int delay, int forget) {
        int M = (int) 1e9 + 7;
        int[] dp = new int[n];
        dp[0] = 1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i + delay >= n) {
                cnt = (cnt + dp[i]) % M;
            }
            for (int j = i + delay; j < Math.min(n, i + forget); j++) {
                dp[j] = (dp[j] + dp[i]) % M;
            }
        }
        return (dp[n - 1] + cnt) % M;
    }

    public int peopleAwareOfSecret4(int n, int delay, int forget) {
        int M = (int) 1e9 + 7;
        int[] diff = new int[n];
        diff[0] = 1;
        diff[1] = M - 1;
        int f = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            f = (f + diff[i]) % M;
            if (i + delay >= n) {
                cnt = (cnt + f) % M;
            } else {
                diff[i + delay] = (diff[i + delay] + f) % M;
                if (i + forget < n) {
                    diff[i + forget] = (diff[i + forget] - f + M) % M;
                }
            }
        }
        return (f + cnt) % M;
    }

    public int peopleAwareOfSecret5(int n, int delay, int forget) {
        int M = (int) 1e9 + 7;
        int[] sum = new int[n + 1];
        sum[1] = 1;
        for (int i = 2; i <= n; i++) {
            int add = sum[Math.max(0, i - delay)] - sum[Math.max(0, i - forget)];
            sum[i] = (int) (((long) sum[i - 1] + add + M) % M);
        }
        return (sum[n] - sum[Math.max(0, n - forget)] + M) % M;
    }

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int M = (int) 1e9 + 7;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= delay; i++) {
            sum[i] = 1;
        }
        for (int i = delay + 1; i <= forget; i++) {
            sum[i] = (sum[i - 1] + sum[i - delay]) % M;
        }
        for (int i = forget + 1; i <= n; i++) {
            sum[i] = (sum[i - delay] - sum[i - forget] + M) % M;
            sum[i] = (sum[i - 1] + sum[i]) % M;
        }
        return (sum[n] - sum[n - forget] + M) % M;
    }
}
