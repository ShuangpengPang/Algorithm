package com.shuangpeng.competition.第297场周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem2305FairDistributionOfCookies（公平分发饼干）
 * @Date 2022/6/25 7:10 PM
 * @Version 1.0
 */

// 比赛时写法
public class Problem2305FairDistributionOfCookies {

    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        int[] map = new int[1 << (2 * n)];
        return dfs(cookies, k, (k << n) | ((1 << n) - 1), map);
    }

    private int dfs(int[] cookies, int k, int state, int[] map) {
        if (map[state] != 0) {
            return map[state];
        }
        int n = cookies.length;
        if (k == 1) {
            int sum = 0;
            for (int i = 0; i < n; ++i) {
                if ((state & (1 << i)) != 0) {
                    sum += cookies[i];
                }
            }
            map[state] = sum;
            return sum;
        }
        int c = state & ((1 << n) - 1);
        if (c == 0) {
            return Integer.MAX_VALUE;
        }
        int M = 1 << n;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < M; ++i) {
            int s = state & i;
            if (s == 0) {
                continue;
            }
            int next = state;
            int sum = 0;
            while (s > 0) {
                int j = Integer.numberOfTrailingZeros(s);
                sum += cookies[j];
                next ^= (1 << j);
                s ^= (1 << j);
            }
            // for (int j = 0; j < n; ++j) {
            //     if ((s & (1 << j)) != 0) {
            //         sum += cookies[j];
            //         next ^= (1 << j);
            //     }
            // }
            next = ((k - 1) << n) | (next & (M - 1));
            ans = Math.min(ans, Math.max(sum, dfs(cookies, k - 1, next, map)));
        }
        map[state] = ans;
        return ans;
    }
}

class Problem2305FairDistributionOfCookies0 {

    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        return dfs(cookies, k, (1 << n) - 1, new HashMap<>());
    }

    private int dfs(int[] cookies, int k, int state, Map<Integer, Integer> memo) {
        if (memo.containsKey(state)) {
            return memo.get(state);
        }
        int n = cookies.length;
        int M = 1 << n;
        int s = state & (M - 1);
        if (k == 0) {
            return s == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (Integer.bitCount(s) < k) {
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < M; i++) {
            int m = s & i;
            int sum = 0;
            while (m > 0) {
                sum += cookies[Integer.numberOfTrailingZeros(m)];
                m &= m - 1;
            }
            if (sum < ans) {
                int nextState = ((k - 1) << n) | (s ^ (s & i));
                ans = Math.min(ans, Math.max(sum, dfs(cookies, k - 1, nextState, memo)));
            }
        }
        memo.put(state, ans);
        return ans;
    }
}

class Problem2305FairDistributionOfCookies1 {

    int[] sumMap;

    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        int M = 1 << n;
        sumMap = new int[M];
        for (int i = 1; i < M; i++) {
            int j = i - 1;
            int sum = sumMap[j];
            int idx = 0;
            while ((j & 1) == 1) {
                sum -= cookies[idx++];
                j >>= 1;
            }
            sumMap[i] = sum + cookies[idx];
        }
        return dfs(cookies, k, M - 1, new HashMap<>());
    }

    private int dfs(int[] cookies, int k, int state, Map<Integer, Integer> memo) {
        if (memo.containsKey(state)) {
            return memo.get(state);
        }
        int n = cookies.length;
        int M = 1 << n;
        int s = state & (M - 1);
        if (k == 0) {
            return s == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (Integer.bitCount(s) < k) {
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < M; i++) {
            int m = s & i;
            int sum = sumMap[m];
            if (sum < ans) {
                int nextState = ((k - 1) << n) | (s ^ (s & i));
                ans = Math.min(ans, Math.max(sum, dfs(cookies, k - 1, nextState, memo)));
            }
        }
        memo.put(state, ans);
        return ans;
    }
}

class Problem2305FairDistributionOfCookies2 {

    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        int M = 1 << n;
        int[] sum = new int[M];
        for (int i = 0; i < n; i++) {
            int h = 1 << i;
            for (int j = 0; j < h; j++) {
                sum[h | j] = sum[j] + cookies[i];
            }
        }
        int[] dp = sum.clone();
        for (int i = 1; i < k; i++) {
            for (int j = M - 1; j > 0; j--) {
                for (int s = j; s > 0; s = j & (s - 1)) {
                    dp[j] = Math.min(dp[j], Math.max(sum[s], dp[j ^ s]));
                }
            }
        }
        return dp[M - 1];
    }
}

class Problem2305FairDistributionOfCookies3 {
    int res = Integer.MAX_VALUE;
    public int distributeCookies(int[] cookies, int k) {
        int[] buckets = new int[k];
        dfs(cookies, buckets, 0);
        return res;
    }

    public void dfs(int[] cookies, int[] buckets, int index){
        if(index == cookies.length){
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < buckets.length; i++){
                max = Math.max(max, buckets[i]);
            }
            res = max;
            return;
        }

        for(int i = 0; i < buckets.length; i++){
            if(i > 0 && buckets[i] == buckets[i-1]){
                continue;
            }
            if(i > 0 && index == 0){
                break;
            }
            buckets[i] += cookies[index];
            if(buckets[i] < res){
                dfs(cookies, buckets, index+1);
            }
            buckets[i] -= cookies[index];
        }
    }
}
