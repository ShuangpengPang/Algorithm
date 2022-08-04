package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1326MinimumNumberOfTapsToOpenToWaterAGarden（灌溉花园的最少水龙头数目）
 * @Date 2022/8/2 6:13 PM
 * @Version 1.0
 */
public class Problem1326MinimumNumberOfTapsToOpenToWaterAGarden {

    public int minTaps0(int n, int[] ranges) {
        List<Integer> stack = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            int s = i - ranges[i], e = i + ranges[i];
            if (stack.isEmpty()) {
                if (s <= 0) {
                    stack.add(e);
                }
            } else if (e > stack.get(stack.size() - 1) && s <= stack.get(stack.size() - 1)) {
                while ((stack.size() > 1 && stack.get(stack.size() - 2) >= s) || (stack.size() == 1 && s <= 0)) {
                    stack.remove(stack.size() - 1);
                }
                stack.add(e);
            }
        }
        if (stack.isEmpty()) {
            return -1;
        }
        while (stack.size() > 1 && stack.get(stack.size() - 2) >= n) {
            stack.remove(stack.size() - 1);
        }
        return stack.get(stack.size() - 1) >= n ? stack.size() : -1;
    }

    public int minTaps1(int n, int[] ranges) {
        int[] prev = new int[n + 1];
        int INF = Integer.MAX_VALUE >> 1;
        Arrays.fill(prev, INF);
        for (int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]), r = Math.min(n, i + ranges[i]);
            prev[r] = Math.min(prev[r], l);
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = prev[i]; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[n] == INF ? -1 : dp[n];
    }

    public int minTaps2(int n, int[] ranges) {
        int[] prev = new int[n + 1];
        int INF = Integer.MAX_VALUE >> 1;
        Arrays.fill(prev, INF);
        for (int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]), r = Math.min(n, i + ranges[i]);
            prev[r] = Math.min(prev[r], l);
        }
        int l = prev[n], r = n;
        int ans = 1;
        while (l > 0) {
            int left = l;
            for (int i = r - 1; i >= l; i--) {
                left = Math.min(left, prev[i]);
            }
            if (left < l) {
                ans++;
                r = l;
                l = left;
            } else {
                break;
            }
        }
        return l <= 0 ? ans : -1;
    }

    public int minTaps3(int n, int[] ranges) {
        int[] prev = new int[n + 1];
        int INF = Integer.MAX_VALUE >> 1;
        Arrays.fill(prev, INF);
        for (int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]), r = Math.min(n, i +ranges[i]);
            prev[r] = Math.min(prev[r], l);
        }
        int ans = 0, last = n;
        for (int i = n, min = n; i > 0; i--) {
            min = Math.min(min, prev[i]);
            if (i == last) {
                if (min >= i) {
                    return -1;
                }
                ans++;
                last = min;
            }
        }
        return ans;
    }

    public int minTaps(int n, int[] ranges) {
        int[] next = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]), r = Math.min(n, i + ranges[i]);
            next[l] = Math.max(next[l], r);
        }
        int ans = 0, right = 0;
        for (int i = 0, max = 0; i < n; i++) {
            max = Math.max(max, next[i]);
            if (i == right) {
                if (max <= right) {
                    return -1;
                }
                ans++;
                right = max;
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1326MinimumNumberOfTapsToOpenToWaterAGarden a = new Problem1326MinimumNumberOfTapsToOpenToWaterAGarden();
//        int[] ranges = {0, 0, 0, 0};
//        a.minTaps(ranges.length - 1, ranges);
//    }
}

class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] next = new int[n + 1];
        for(int i = 0; i <= n; ++i){
            next[i] = i;
        }
        for(int i = 0; i <= n; ++i){
            int l = Math.max(0, i - ranges[i]);
            int r = Math.min(n, i + ranges[i]);
            next[l] = Math.max(next[l], r);
        }
        int right = 0;
        int max = 0;
        int ans = 0;
        for(int i = 0; i < n; ++i){
            max = Math.max(next[i], max);
            if(i == right) {
                if(i == max) return -1;
                right = max;
                max = 0;
                ans++;
            }
        }
        return ans;
    }
}


