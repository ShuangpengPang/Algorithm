package com.shuangpeng.competition.第297场周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem2305FairDistributionOfCookies（公平分发饼干）
 * @Date 2022/6/25 7:10 PM
 * @Version 1.0
 */
public class Problem2305FairDistributionOfCookies {

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
